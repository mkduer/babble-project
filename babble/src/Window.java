import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by michelle on 6/4/17.
 */
public abstract class Window extends javax.swing.JFrame  {

    protected javax.swing.JPanel backgroundOrange;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JButton loginButton;
    protected javax.swing.JLabel passLabel;
    protected javax.swing.JLabel rabbitImage;
    protected javax.swing.JTextArea blurb;
    protected javax.swing.JButton registerButton;
    protected javax.swing.JTextField userField;
    protected javax.swing.JTextField passField;
    protected javax.swing.JLabel userLabel;
    protected javax.swing.JTextArea chat;
    protected javax.swing.JTextArea friends;
    protected javax.swing.JScrollPane jScrollPane2;
    protected javax.swing.JScrollPane jScrollPane3;
    protected javax.swing.JTextArea pendingMessage;
    protected javax.swing.JButton sendButton;
    protected javax.swing.JButton logoutButton;
    static volatile boolean verified;
    static volatile boolean run_server;
    static Window babble;


    Window() {
        verified = false;
        run_server = false;
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    // Main method for getting everything started
    public static void main(String args[]) throws Exception {
        Login type = new Login();
        type.create();
    }

    public void create() throws Exception {
        try {
            // Uses Nimbus look and feel: https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
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
