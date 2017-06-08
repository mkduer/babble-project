import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.*;

/**
 * Created by michelle on 6/4/17.
 */
public class Chat extends Window {

    // Constructor for Chat initializes all of the components
    public Chat() {
        super();
        initComponents(); // initialize gui components
    }

    public void initComponents() {

        backgroundOrange = new JPanel();
        sendButton = new JButton();
        jScrollPane1 = new JScrollPane();
        pendingMsg = new JTextArea();
        jScrollPane2 = new JScrollPane();
        chat = new JTextArea();
        jScrollPane3 = new JScrollPane();
        friends = new JTextArea();
        logoutButton = new JButton();

        backgroundOrange.setBackground(new Color(235, 150, 55));

        sendButton.setFont(new Font("Ubuntu", 1, 36)); // NOI18N
        sendButton.setForeground(new Color(47, 167, 137));
        sendButton.setText("Send");
        sendButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, null, new Color(214, 99, 25), null, null));
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        pendingMsg.setColumns(20);
        pendingMsg.setRows(5);
        pendingMsg.setText("Enter your message here");
        jScrollPane1.setViewportView(pendingMsg);

        chat.setColumns(20);
        chat.setRows(5);
        jScrollPane2.setViewportView(chat);

        friends.setColumns(20);
        friends.setRows(5);
        jScrollPane3.setViewportView(friends);

        logoutButton.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(235, 121, 22));
        logoutButton.setText("Logout");
        logoutButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, null, new java.awt.Color(214, 99, 25), null, null));
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        GroupLayout backgroundOrangeLayout = new GroupLayout(backgroundOrange);
        backgroundOrange.setLayout(backgroundOrangeLayout);
        backgroundOrangeLayout.setHorizontalGroup(
                backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundOrangeLayout.createSequentialGroup()
                                .addContainerGap(203, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 732, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 732, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(logoutButton, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59))
        );
        backgroundOrangeLayout.setVerticalGroup(
                backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundOrangeLayout.createSequentialGroup()
                                .addContainerGap(62, Short.MAX_VALUE)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                                                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                                                .addComponent(logoutButton, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundOrangeLayout.createSequentialGroup()
                                                                .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(31, 31, 31))))
                                        .addComponent(jScrollPane3))
                                .addGap(48, 48, 48))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundOrange, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundOrange, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null); // center
    }

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
        chat.append(pendingMsg.getText() + "\n");
        pendingMsg.setText("");
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
            label.setFont(new java.awt.Font("Arial", Font.PLAIN, 23));
            JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
}

