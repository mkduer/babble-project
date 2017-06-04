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

    Window type = null;
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

    Window() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public abstract void initComponents();

    // Main method for getting everything started
    public static void main(String args[]) throws Exception {
        int port = 8000;
        Window type = new Login();

        try {
            // Uses Nimbus look and feel: https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Set Login's value so that it is visible rather than hidden
        type.setVisible(true);

        // Start a server session on defined port
        // and start listening for incoming sessions
        new Server(port).run();
    }

    // Generates connection to database or outputs an error message
    Connection getConnected() {
        Connection connect = null;

        String url = "jdbc:mysql://localhost:3306/bchat";
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

    String userFieldActionPerformed(java.awt.event.ActionEvent evt) {
        return userField.getText();
    }

    String passFieldActionPerformed(ActionEvent evt) {
        return passField.getText();
    }
}
