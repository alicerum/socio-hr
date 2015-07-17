package ru.wv;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import ru.wv.persistence.entities.Role;
import ru.wv.persistence.entities.User;
import ru.wv.persistence.entities.User_;
import ru.wv.persistence.services.UserService;

import javax.ejb.EJB;

@RunWith(Arquillian.class)
public class UserServiceTest {

    @EJB
    private UserService userService;

    @Deployment
    public static JavaArchive deploy() {
        return ShrinkWrap.create(JavaArchive.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addClasses(User.class, User_.class,
                        UserService.class,
                        Role.class,
                        BCrypt.class);
    }

    @Test
    public void createUser() {
        User user = new User();
        user.setEmail("elijahrum@gmail.com");
        user.setName("wyvie");
        user.setPwdHash(BCrypt.hashpw("wyvie1", BCrypt.gensalt()));
        user.setRole(Role.ADMIN);

        userService.persist(user);
    }
}
