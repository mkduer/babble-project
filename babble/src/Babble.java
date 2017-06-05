/*
Created by michelle on 5/6/17.

note: no package since this is a small application
refer to bottom of official documentation on why it is not
necessary for this application:
http://docs.oracle.com/javase/tutorial/java/package/createpkgs.html
*/
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Babble extends Window {
    private final String host;
    private final int port;
    // private String message; // TODO: delete

    // paramterized constructor with host and port to connect to
    public Babble(String host, int port) {
        super();
        this.host = host;
        this.port = port;
        initComponents(); // initialize gui components
    }

    // client running behavior
    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // setup a channel using Bootstrap and the
            // new IO sockets (e.g. Nio)
            Bootstrap b = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new UserInit());

            // connect to server using specified host and port
            Channel channel = b.connect(host, port).sync().channel();

            // grap user's input from console
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while(true) {
                /* types message to channels
                IMPORTANT: This will only work with flush
                whether it is write and flush or writeandflush methods
                may not be the most effective approach, but works for now!
                */
                channel.writeAndFlush("  " + in.readLine() + "\r\n");
            }

            // clean-up before closing
        } finally {
            group.shutdownGracefully();
        }
    }
    public void createRoom() throws Exception {
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
        // this.run(); TODO
    }

    public void initComponents() {

        backgroundOrange = new javax.swing.JPanel();
        sendButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pendingMessage = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        chat = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        friends = new javax.swing.JTextArea();
        logoutButton = new javax.swing.JButton();

        backgroundOrange.setBackground(new java.awt.Color(235, 150, 55));

        sendButton.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        sendButton.setForeground(new java.awt.Color(47, 167, 137));
        sendButton.setText("Send");
        sendButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(214, 99, 25), null, null));
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        pendingMessage.setColumns(20);
        pendingMessage.setRows(5);
        pendingMessage.setText("Enter your message here");
        jScrollPane1.setViewportView(pendingMessage);

        chat.setColumns(20);
        chat.setRows(5);
        jScrollPane2.setViewportView(chat);

        friends.setColumns(20);
        friends.setRows(5);
        jScrollPane3.setViewportView(friends);

        logoutButton.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(235, 121, 22));
        logoutButton.setText("Logout");
        logoutButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(214, 99, 25), null, null));
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundOrangeLayout = new javax.swing.GroupLayout(backgroundOrange);
        backgroundOrange.setLayout(backgroundOrangeLayout);
        backgroundOrangeLayout.setHorizontalGroup(
                backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundOrangeLayout.createSequentialGroup()
                                .addContainerGap(203, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59))
        );
        backgroundOrangeLayout.setVerticalGroup(
                backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundOrangeLayout.createSequentialGroup()
                                .addContainerGap(62, Short.MAX_VALUE)
                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                                                        .addGroup(backgroundOrangeLayout.createSequentialGroup()
                                                                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(backgroundOrangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundOrangeLayout.createSequentialGroup()
                                                                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(31, 31, 31))))
                                        .addComponent(jScrollPane3))
                                .addGap(48, 48, 48))
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

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO initiate user sesion
        System.out.println("Send button pressed");
        /* TODO: cleanup
        System.out.println("message = " + message + "\n");
        chat.setText(message);
        */

    }

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
}
