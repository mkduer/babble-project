/**
 * Created by michelle on 5/29/17.
 */
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.*;

/*
The chat interface relies on the JFrame library
to build out buttons, textfields, labels and more.
*/
public class Login extends Window {

    protected JLabel rabbitImage;
    protected JTextArea blurb;

    // Constructor for Login initializes all of the components
    public Login() {
        super();
        verified = false;
        initComponents(); // initialize gui components
    }

    public void initComponents() {

        backgroundOrange = new JPanel();
        passLabel = new JLabel();
        userLabel = new JLabel();
        userField = new JTextField();
        registerButton = new JButton();
        loginButton = new JButton();
        passField = new JPasswordField();
        rabbitImage = new JLabel();
        jScrollPane1 = new JScrollPane();
        blurb = new JTextArea(5,20);

        backgroundOrange.setBackground(new Color(235, 150, 55));

        passLabel.setFont(new Font("Ubuntu", 3, 36));
        passLabel.setText("Password");
        passLabel.setHorizontalTextPosition(SwingConstants.LEFT);

        userLabel.setFont(new Font("Ubuntu", 3, 36));
        userLabel.setText("Username");
        userLabel.setHorizontalTextPosition(SwingConstants.LEFT);

        userField.setFont(new Font("Ubuntu", 3, 36));
        userField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userFieldActionPerformed(evt);
            }
        });

        passField.setFont(new Font("Ubuntu", 3, 36));
        passField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passFieldActionPerformed(evt);
            }
        });

        registerButton.setFont(new Font("Ubuntu", Font.BOLD, 36));
        registerButton.setForeground(new Color(235, 121, 22));
        registerButton.setText("Register");
        registerButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, null, new Color(214, 99, 25), null, null));
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        loginButton.setFont(new Font("Ubuntu", Font.BOLD, 36));
        loginButton.setForeground(new Color(235, 121, 22));
        loginButton.setText("Login");
        loginButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, null, new Color(214, 99, 25), null, null));
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
        rabbitImage.setIcon(new ImageIcon(getClass().getResource("you_sneaky_rabbit.jpg")));
        rabbitImage.setText("rabbit");

        blurb.setBackground(new Color(235, 150, 55));
        blurb.setFont(new Font("Ubuntu", 2, 24));
        blurb.setText("        Not a part of Babble Chat? \n       Think ginger rabbits are cute?\n\n              If you answered yes, \n                Jooooooooin us! ");
        blurb.setBorder(null);
        blurb.setCaretColor(new Color(235, 150, 55));
        jScrollPane1.setViewportView(blurb);

        GroupLayout backgroundOrangeLayout = new GroupLayout(backgroundOrange);
        backgroundOrange.setLayout(backgroundOrangeLayout);
        backgroundOrangeLayout.setHorizontalGroup(
                backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, backgroundOrangeLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(passLabel, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(passField, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(userField, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE))
                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                                .addGap(99, 99, 99)
                                                .addComponent(rabbitImage, GroupLayout.PREFERRED_SIZE, 638, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(95, Short.MAX_VALUE))
        );
        backgroundOrangeLayout.setVerticalGroup(
                backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(rabbitImage, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(userField, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
                                                .addGap(76, 76, 76)
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(passField, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(passLabel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
                                                .addGap(68, 68, 68)
                                                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
                                .addGap(32, 32, 32)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(registerButton, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(63, Short.MAX_VALUE))
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
        getRootPane().setDefaultButton(loginButton);
        setLocationRelativeTo(null); // center
    }

    public void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            this.dispose();
            Register client = new Register();
            client.create();
        } catch (Exception ex) {
            System.out.println("Failed to open registration window");
        }
    }

    public void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int id = 0;
        String name = userFieldActionPerformed(evt);

        id = userAccess(name,passFieldActionPerformed(evt));

        if (id > 0) {
            username = name;
            try {
                userField.setText("");
                passField.setText("");
                this.dispose();
                verified = true;
            } catch (Exception ex) {
                System.out.println("Chatroom error. Let's see what's going on in this flat dimension\n");
            }
        } else {
            JLabel label = new JLabel("    Invalid username or password. Please try again.    ");
            label.setFont(new Font("Arial", Font.PLAIN,23));
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