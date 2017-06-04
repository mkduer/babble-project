/**
 * Created by michelle on 6/4/17.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

/*
* The chat interface relies on the javax.swing.JFrame library
* to build out buttons, textfields, labels and more.
 */
public class Register extends Window {

    // Constructor for Register initializes all of the components
    public Register() {
        super();
        initComponents(); // initialize gui components
    }

    public void createRegistration() throws Exception {
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

        // Set Login's value so that it is visible rather than hidden
        setVisible(true);
    }

    public void initComponents() {
        backgroundOrange = new javax.swing.JPanel();
        passLabel = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        registerButton = new javax.swing.JButton();
        passField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        registerButton.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        registerButton.setForeground(new java.awt.Color(235, 121, 22));
        registerButton.setText("Register");
        registerButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(214, 99, 25), null, null));
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        passField.setFont(new java.awt.Font("Ubuntu", 3, 36)); // NOI18N
        passField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundOrangeLayout = new javax.swing.GroupLayout(backgroundOrange);
        backgroundOrange.setLayout(backgroundOrangeLayout);
        backgroundOrangeLayout.setHorizontalGroup(
                backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundOrangeLayout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(passLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(826, Short.MAX_VALUE))
        );
        backgroundOrangeLayout.setVerticalGroup(
                backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(76, 76, 76)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(68, 68, 68)
                                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(262, Short.MAX_VALUE))
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
        String user = null;

        if (userAccess(userFieldActionPerformed(evt),passFieldActionPerformed(evt)) > 0) {
            System.out.println("Inserting User!\n"); // TODO: delete
            // TODO: allow user to enter chat room
        } else {
            JLabel label = new JLabel("    Invalid username or password. Please try again.    ");
            label.setFont(new java.awt.Font("Arial", Font.PLAIN,23));
            JOptionPane.showMessageDialog(null, label, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int userAccess(String name, String key) {
        int valid = -1;
        Connection connect = getConnected();

        // TODO: Test to see if values are being added correctly
        try {
            Statement st = connect.createStatement();
            String sql = "INSERT INTO users (username,pass) VALUES ('" + name + "','" + key + "');";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return valid;
    }


}