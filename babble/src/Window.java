import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.*;


/**
 * Created by michelle on 6/4/17.
 */
public abstract class Window extends JFrame {

    protected boolean verified;
    static public volatile boolean run_server;
    static public volatile String username;
    protected JPanel backgroundOrange;
    protected JButton loginButton;
    protected JButton registerButton;
    protected JLabel passLabel;
    protected JTextField passField;
    protected JLabel userLabel;
    protected JTextField userField;
    protected JScrollPane jScrollPane1;
    protected JScrollPane jScrollPane2;
    protected JScrollPane jScrollPane3;

    Window() {
        verified = false;
        run_server = false;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // Main method for getting everything started
    public static void main(String args[]) throws Exception {
        System.out.println("This object shouldn't run on its own\n");
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
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            JLabel label = new JLabel("    Failed to Connect to DB!    ");
            label.setFont(new Font("Arial", Font.PLAIN, 23));
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
