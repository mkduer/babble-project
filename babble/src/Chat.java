import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.sql.*;
import java.util.logging.*;

/**
 * Created by michelle on 6/4/17.
 */
public class Chat extends Window {

    String name;
    protected JTextArea chat;
    protected JTextArea friends;
    protected JTextArea pendingMsg;
    protected JButton sendButton;
    protected JButton logoutButton;

    // Constructor for Chat initializes all of the components
    public Chat() {
        super();
        if (Window.username == null) {
            name = null;
        } else {
            name = Window.username;
        }
        initComponents(); // initialize gui components
    }


    // Constructor for Chat initializes all of the components
    public Chat(String name) {
        super();
        this.name = name;
        initComponents(); // initialize gui components
    }

    public void initComponents() {

        backgroundOrange = new JPanel();
        sendButton = new JButton("Send");
        jScrollPane1 = new JScrollPane();
        pendingMsg = new JTextArea(5,20);
        jScrollPane2 = new JScrollPane();
        chat = new JTextArea("Welcome " + this.name + "!\n\n",5,20);
        jScrollPane3 = new JScrollPane();
        friends = new JTextArea(this.name + "'s friends:\n",5, 20);
        logoutButton = new JButton("Logout");

        backgroundOrange.setBackground(new Color(235, 150, 55));

        sendButton.setFont(new Font("Ubuntu", Font.BOLD, 36));
        sendButton.setForeground(new Color(47, 167, 137));
        sendButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, null, new Color(214, 99, 25), null, null));
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        pendingMsg.setFont(new Font("Arial",Font.PLAIN, 17));
        jScrollPane1.setViewportView(pendingMsg);

        chat.setEditable(false);
        chat.setFont(new Font("Arial", Font.PLAIN, 19));
        jScrollPane2.setViewportView(chat);

        friends.setEditable(false);
        friends.setFont(new Font("Arial", Font.PLAIN, 19));
        jScrollPane3.setViewportView(friends);
        jScrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        logoutButton.setFont(new java.awt.Font("Ubuntu", Font.BOLD, 36));
        logoutButton.setForeground(new java.awt.Color(235, 121, 22));
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

