/**
 * Created by michelle on 5/6/17.
 */
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/* This class creates a Server at the specified port
*  shutting down gracefully at the end
 */
public class Server {

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    // start a chat server at port (e.g. 8000)
    // and listen for incoming connections
    public static void main(String[] args) throws Exception {
        new Server(8000).run();
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

            b.bind(port).sync().channel().closeFuture().sync();

            // clean-up before closing server
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}