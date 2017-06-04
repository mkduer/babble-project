import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by michelle on 6/3/17.
 */
public class UnitTest {
    Connection connect;
    Server session;

    @Before
    public void setUpConnect() throws SQLException {
        connect = null;

        String url = "jdbc:mysql://localhost:3306/bchat";
        String user = "babble";
        String pass = "babble";

        connect = DriverManager.getConnection(url,user,pass);
    }

    @Test // database connection
    public void checkConnection() throws Exception {
        assertNotNull(connect);
    }

    @Before // port in valid TCP/UDP range
    public void setUpSession() throws Exception {
        int port = 0;
        session = new Server(port);
    }

    @Test // server session
    public void serverSession() throws Exception {
        assertNotNull(session);
    }


    @Test // various valid/invalid parameters for validation
    public void validateLogin() throws Exception {
        Login test = new Login();
        int id = 0;

        // test valid values
        id = test.userAccess("testuser","testpass");
        assertEquals(3,id);

        // test invalid user
        id = test.userAccess("invalid","testpass");
        assertEquals(-1,id);

        // test invalid pass
        id = test.userAccess("testuser","invalid");
        assertEquals(-1,id);

        // test empty name
        id = test.userAccess("","testpass");
        assertEquals(-1,id);

        // test empty pass
        id = test.userAccess("testuser","");
        assertEquals(-1,id);
    }

}