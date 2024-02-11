import com.gleb.ratingmovies.dao.entity.User;
import com.gleb.ratingmovies.dao.entity.UserStatus;
import com.gleb.ratingmovies.exception.ServiceException;
import com.gleb.ratingmovies.service.UserService;
import com.gleb.ratingmovies.util.LineHasher;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {
    private static final String TEST_USER_LOGIN = "pavelkilbas";
    private static final String TEST_USER_PASSWORD = "pavelkilbas";
    private static final int TEST_USER_ID = 1;

    UserService userService = UserService.getInstance();

    @Test
    public void testCheckUserByLoginAndPassword() {
        LineHasher lineHasher = new LineHasher();
        String hashPass = lineHasher.hashingLine(TEST_USER_PASSWORD);
        Optional<User> user = Optional.empty();
        try {

            user = userService.findUserByLoginAndPassword(TEST_USER_LOGIN, hashPass);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        assertTrue(user.isPresent());
    }

    @Test
    public void testBlockById() {
        User user = null;
        boolean actual = false;
        try {
            userService.blockedById(TEST_USER_ID);
            user = userService.findUserByLogin(TEST_USER_LOGIN);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (user != null && user.getUserStatus().equals(UserStatus.BANNED)) {
            actual = true;
        }
        assertTrue(actual);
    }


    @Test
    public void testFindUserByEmail() {
        User user = null;
        boolean actual = false;
        try {
            user = userService.findUserById(TEST_USER_ID);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (user != null && user.getId() == TEST_USER_ID) {
            actual = true;
        }
        assertTrue(actual);
    }


    @Test
    public void testFindUserByLogin() {
        User user = null;
        boolean actual = false;
        try {
            user = userService.findUserByLogin(TEST_USER_LOGIN);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (user != null && user.getId() == TEST_USER_ID) {
            actual = true;
        }
        assertTrue(actual);
    }


}
