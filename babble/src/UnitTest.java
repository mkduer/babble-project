import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.*;


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

    @Test // registration test
    public void validateRegistration() throws Exception {
        Register test = new Register();
        int id = -1;
        Statement st = connect.createStatement();
        String name = "h";

        // double-check that the following name does not already exist
        String sql = "SELECT id FROM users WHERE username='" + name + "';";
        ResultSet res = st.executeQuery(sql);
        while (res.next()) {
            id = res.getInt("id");
        }
        assertEquals(-1,id);

        // test that registering is successful
        id = test.userAccess(name,"b");
        assertNotEquals(-1,id);

        // remove register test name // TODO:
        sql = "DELETE FROM users WHERE id='" + id + "';";
        st.executeUpdate(sql);

        // double-check that the name was removed
        id = -1;
        sql = "SELECT id FROM users WHERE username='"+ name + "';";
        res = st.executeQuery(sql);
        while (res.next()) {
            id = res.getInt("id");
        }
        assertEquals(-1,id);
    }

    public void closeConnection() throws Exception {
        connect.close();
    }
}