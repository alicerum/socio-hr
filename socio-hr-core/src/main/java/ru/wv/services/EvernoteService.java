package ru.wv.services;

import com.evernote.auth.EvernoteAuth;
import com.evernote.clients.ClientFactory;
import com.evernote.clients.NoteStoreClient;
import com.evernote.edam.notestore.NoteFilter;
import com.evernote.edam.notestore.NoteList;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.NoteSortOrder;
import com.evernote.edam.type.Notebook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.wv.persistence.entities.Article;
import ru.wv.persistence.services.ArticleService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Singleton
//@Startup
public class EvernoteService {

    private static final String API_KEY = "S=s1:U=9123d:E=155df9328c9:C=14e87e1fb78:P=1cd:A=en-devtoken:V=2:H=b5943c5403d85baa01436b5bba00d6ec";
    private ClientFactory clientFactory;

    private final Logger log = LoggerFactory.getLogger(EvernoteService.class);

    @EJB
    private ArticleService articleService;

    @PostConstruct
    public void init() {
        this.clientFactory = new ClientFactory(new EvernoteAuth(com.evernote.auth.EvernoteService.SANDBOX, API_KEY));
    }

//    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void schedule() {
        try {
            NoteStoreClient storeClient = clientFactory.createNoteStoreClient();
            List<Notebook> notebooks = storeClient.listNotebooks();
            notebooks.stream()
                    .filter(n -> n.getName().equals("sociohr"))
                    .forEach(notebook -> {
                        NoteFilter filter = new NoteFilter();
                        filter.setNotebookGuid(notebook.getGuid());
                        filter.setOrder(NoteSortOrder.CREATED.getValue());
                        filter.setAscending(true);

                        try {
                            NoteList noteList = storeClient.findNotes(filter, 0, 100);
                            for (Note note : noteList.getNotes()) {
                                String hash = Base64.getEncoder().encodeToString(note.getContentHash());
                                String guid = note.getGuid();

                                Optional<Article> article = articleService.get(guid);
                                if (article.isPresent()) {
                                    Article oldArticle = article.get();
                                    if (!oldArticle.getHash().equals(hash)) {
                                        String content = storeClient.getNoteContent(guid);
                                        oldArticle.setText(content);
                                        oldArticle.setHash(hash);
                                        articleService.persist(oldArticle);
                                    }
                                } else {
                                    String content = storeClient.getNoteContent(guid);

                                    Article newArticle = new Article();
                                    newArticle.setEvernoteGuid(guid);
                                    newArticle.setHash(hash);
                                    newArticle.setText(content);
                                    newArticle.setTitle(note.getTitle());

                                    articleService.persist(newArticle);
                                }
                            }
                        } catch (Exception e) {
                            log.error("Some error happened while fetching notes", e);
                        }
                    });
        } catch (Exception e) {
            log.error("Some error happened while working with evernote", e);
        }
    }
}
