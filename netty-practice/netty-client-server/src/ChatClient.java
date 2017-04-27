/* note: no package since this is a small application
* refer to bottom of official documentation on why it is not
* necessary for this application:
* http://docs.oracle.com/javase/tutorial/java/package/createpkgs.html
*/

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChatClient {

    private final String host;
    private final int port;

    // paramterized constructor with host and port to connect to
    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // start a chat client on specified host and port
    public static void main(String[] args) throws Exception {
        new ChatClient("localhost", 8000).run();
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
                .handler(new ChatClientInitializer());

            // connect to server using specified host and port
            Channel channel = b.connect(host, port).sync().channel();

            // grap user's input from console
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while(true) {
                channel.write(in.readLine() + "\r\n");
            }

        // clean-up before closing
        } finally {
            group.shutdownGracefully();
        }
    }
}


