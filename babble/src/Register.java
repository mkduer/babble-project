/**
 * Created by michelle on 6/4/17.
 */
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

/*
* The chat interface relies on the JFrame library
* to build out buttons, textfields, labels and more.
 */
public class Register extends Window {

    // Constructor for Register initializes all of the components
    public Register() {
        super();
        initComponents(); // initialize gui components
    }

    public void initComponents() {
        backgroundOrange = new JPanel();
        passLabel = new JLabel();
        userLabel = new JLabel();
        userField = new JTextField();
        registerButton = new JButton();
        passField = new JPasswordField();

        backgroundOrange.setBackground(new Color(235, 150, 55));

        passLabel.setFont(new Font("Ubuntu", 3, 36)); // NOI18N
        passLabel.setText("Password");
        passLabel.setHorizontalTextPosition(SwingConstants.LEFT);

        userLabel.setFont(new Font("Ubuntu", 3, 36)); // NOI18N
        userLabel.setText("Username");
        userLabel.setHorizontalTextPosition(SwingConstants.LEFT);

        userField.setFont(new Font("Ubuntu", 3, 36)); // NOI18N
        userField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userFieldActionPerformed(evt);
            }
        });

        registerButton.setFont(new Font("Ubuntu", 1, 36)); // NOI18N
        registerButton.setForeground(new Color(235, 121, 22));
        registerButton.setText("Register");
        registerButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, null, new Color(214, 99, 25), null, null));
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    registerButtonActionPerformed(evt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        passField.setFont(new Font("Ubuntu", 3, 36)); // NOI18N
        passField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passFieldActionPerformed(evt);
            }
        });

        GroupLayout backgroundOrangeLayout = new GroupLayout(backgroundOrange);
        backgroundOrange.setLayout(backgroundOrangeLayout);
        backgroundOrangeLayout.setHorizontalGroup(
                backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundOrangeLayout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(passLabel, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(passField, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(userField, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(826, Short.MAX_VALUE))
        );
        backgroundOrangeLayout.setVerticalGroup(
                backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(userField, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
                                .addGap(76, 76, 76)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passField, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passLabel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
                                .addGap(68, 68, 68)
                                .addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(262, Short.MAX_VALUE))
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

    public void registerButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        try {
            if (userAccess(userFieldActionPerformed(evt), passFieldActionPerformed(evt)) > 0) {
                try {
                    this.dispose();
                    run_server = true;
                    verified = true;
                } catch (Exception ex) {
                    System.out.println("Chatroom failed to materialize. Contact us so we can find what dimension it vanished to!\n");
                }
            }
        } catch (Exception ex) {
            JLabel label = new JLabel("    Sorry, we're running into registration issues, please contact us!    ");
            label.setFont(new Font("Arial", Font.PLAIN, 23));
            JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int userAccess(String name, String key) {
        int valid = -1;
        int id = -1;
        Connection connect = getConnected();

        try {
            Statement st = connect.createStatement();

            // check that username hasn't already been taken
            String sql = "SELECT id FROM users WHERE username='"+ name + "';";
            ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                id = res.getInt("id");
            }

            if (id < 0) {
                // register user
                sql = "INSERT INTO users (username,pass) VALUES ('" + name + "','" + key + "')";
                st.executeUpdate(sql);

                // confirm that account was created
                sql = "SELECT id FROM users WHERE username='" + name + "' AND pass='" + key + "';";
                res = st.executeQuery(sql);
                while (res.next()) {
                    valid = res.getInt("id");
                }
            } else {
                userField.setText("");
                passField.setText("");
                JLabel label = new JLabel("    That username is already taken. Please choose another.    ");
                label.setFont(new Font("Arial", Font.PLAIN,23));
                JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            connect.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return valid;
    }
}