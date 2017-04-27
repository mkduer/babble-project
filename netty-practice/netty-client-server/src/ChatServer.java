/**
 * Created by michelle on 4/26/17.
 */
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ChatServer {

    private final int port;

    public ChatServer(int port) {
        this.port = port;
    }

    // start a chat server at port (e.g. 8000)
    // and listen for incoming connections
    public static void main(String[] args) throws Exception {
        new ChatServer(8000).run();
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // first use boss group followed by worker group
        try {
            ServerBootstrap b = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChatServerInitializer());

            b.bind(port).sync().channel().closeFuture().sync();

        // clean-up before closing server
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
