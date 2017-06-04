/**
 * Created by michelle on 5/29/17.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

/*
The chat interface relies on the javax.swing.JFrame library
to build out buttons, textfields, labels and more.
*/
public class Login extends Window {

    // Constructor for Login initializes all of the components
    // and connects to database
    public Login() {
        super();
        initComponents(); // initialize gui components
    }

    public void initComponents() {

        backgroundOrange = new javax.swing.JPanel();
        passLabel = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        registerButton = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();
        passField = new javax.swing.JPasswordField();
        rabbitImage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        blurb = new javax.swing.JTextArea();

        backgroundOrange.setBackground(new java.awt.Color(235, 150, 55));

        passLabel.setFont(new java.awt.Font("Ubuntu", 3, 36)); // NOI18N
        passLabel.setText("Password");
        passLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        userLabel.setFont(new java.awt.Font("Ubuntu", 3, 36)); // NOI18N
        userLabel.setText("Username");
        userLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        userField.setFont(new java.awt.Font("Ubuntu", 3, 36)); // NOI18N
        userField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userFieldActionPerformed(evt);
            }
        });

        passField.setFont(new java.awt.Font("Ubuntu", 3, 36)); // NOI18N
        passField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passFieldActionPerformed(evt);
            }
        });

        registerButton.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        registerButton.setForeground(new java.awt.Color(235, 121, 22));
        registerButton.setText("Register");
        registerButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(214, 99, 25), null, null));
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        loginButton.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        loginButton.setForeground(new java.awt.Color(235, 121, 22));
        loginButton.setText("Login");
        loginButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(214, 99, 25), null, null));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        /* This is image is used under a CreativeCommons license: https://creativecommons.org/licenses/by/2.0/
        under which it can be shared and adapted with attribution and no further restrictions allowed.
        The source of this image is found here: https://www.flickr.com/photos/17207222@N02/6644178563/
        "You sneaky rabbit!" by Sarah Buckley on Flickr
        */
        rabbitImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("you_sneaky_rabbit.jpg")));
        rabbitImage.setText("rabbit");

        blurb.setBackground(new java.awt.Color(235, 150, 55));
        blurb.setColumns(20);
        blurb.setFont(new java.awt.Font("Ubuntu", 2, 24)); // NOI18N
        blurb.setRows(5);
        blurb.setText("        Not a part of Babble Chat? \n       Think ginger rabbits are cute?\n\n              If you answered yes, \n                Jooooooooin us! ");
        blurb.setBorder(null);
        blurb.setCaretColor(new java.awt.Color(235, 150, 55));
        jScrollPane1.setViewportView(blurb);

        javax.swing.GroupLayout backgroundOrangeLayout = new javax.swing.GroupLayout(backgroundOrange);
        backgroundOrange.setLayout(backgroundOrangeLayout);
        backgroundOrangeLayout.setHorizontalGroup(
                backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundOrangeLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(passLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                                .addGap(99, 99, 99)
                                                .addComponent(rabbitImage, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(95, Short.MAX_VALUE))
        );
        backgroundOrangeLayout.setVerticalGroup(
                backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(rabbitImage, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(76, 76, 76)
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(passLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(68, 68, 68)
                                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(32, 32, 32)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundOrange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(backgroundOrange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null); // center
    }

    public void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            this.dispose();
            Register client = new Register();
            client.createRegistration();
        } catch (Exception ex) {
            System.out.println("Failed to open registration window");
        }
    }

    public void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String user = null;

        if (userAccess(userFieldActionPerformed(evt),passFieldActionPerformed(evt)) > 0) {
            System.out.println("ACCESS GRANTED!\n"); // TODO: delete
            // TODO: open chat window here
        } else {
            JLabel label = new JLabel("    Invalid username or password. Please try again.    ");
            label.setFont(new java.awt.Font("Arial", Font.PLAIN,23));
            JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int userAccess(String name, String key) {
        int valid = -1;
        Connection connect = getConnected();

        try {
            Statement st = connect.createStatement();
            String sql = "SELECT id FROM users WHERE username='"+ name + "' AND pass='" + key + "';";
            ResultSet res = st.executeQuery(sql);
            while (res.next()) {
                valid = res.getInt("id");
            }
            connect.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return valid;
    }
}