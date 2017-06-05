/**
 * Created by michelle on 5/6/17.
 */
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/* This class creates a Server at the specified port
shutting down gracefully at the end
*/
public class Server {

    private final int port;

    public Server(int port) throws Exception {
        this.port = validPort(port);
    }

    public static void main(String[] args) throws Exception {
        try {
            new Server(8080).run();
        } catch (Exception ex) {
            System.out.println("Server error\n");
        }
    }

    // Run the Server
    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // first use boss group followed by worker group
        try {
            ServerBootstrap b = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInit());

            System.out.println("server running...\n");
            b.bind(port).sync().channel().closeFuture().sync();

            // clean-up before closing server
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    // Make sure that the port is valid
    public int validPort(int port) throws Exception {
        int min = 1024;
        int max = 49151;

        if (port <= min && max <= port) {
            port = 8080; // ensure valid port is used
        }
        return port;
    }
}