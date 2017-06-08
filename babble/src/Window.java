import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by michelle on 6/4/17.
 */
public abstract class Window extends JFrame {

    protected JPanel backgroundOrange;
    protected JScrollPane jScrollPane1;
    protected JButton loginButton;
    protected JLabel passLabel;
    protected JLabel rabbitImage;
    protected JTextArea blurb;
    protected JButton registerButton;
    protected JTextField userField;
    protected JTextField passField;
    protected JLabel userLabel;
    protected JTextArea chat;
    protected JTextArea friends;
    protected JScrollPane jScrollPane2;
    protected JScrollPane jScrollPane3;
    protected JTextArea pendingMessage;
    protected JButton sendButton;
    protected JButton logoutButton;
    static volatile boolean verified;
    static volatile boolean run_server;
    static Chat room;


    Window() {
        verified = false;
        run_server = false;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // Main method for getting everything started
    public static void main(String args[]) throws Exception {
        Login login = new Login();
        login.create();
    }

    public void create() throws Exception {
        try {
            // Uses Nimbus look and feel: https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.setVisible(true);
    }

    // Generates connection to database or outputs an error message
    Connection getConnected() {
        Connection connect = null;

        String url = "jdbc:mysql://localhost:3306/bchat?useSSL=false";
        String user = "babble";
        String pass = "babble";

        try {
            connect = DriverManager.getConnection(url, user, pass);
            return connect;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JLabel label = new JLabel("    Failed to Connect to DB!    ");
            label.setFont(new java.awt.Font("Arial", Font.PLAIN, 23));
            JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public abstract void initComponents();

    String userFieldActionPerformed(java.awt.event.ActionEvent evt) {
        return userField.getText();
    }

    String passFieldActionPerformed(ActionEvent evt) {
        return passField.getText();
    }
}
