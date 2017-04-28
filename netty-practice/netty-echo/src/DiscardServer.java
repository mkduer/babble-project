/*
 * Created by michelle on 4/25/17.
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
//import io.netty.util.ReferenceCountUtil;

// Discards any incoming data
public final class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new DiscardServer(port).run();
    }

    //@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("i'm in here\n");
        try {
            // original code in tutorial
            while (in.isReadable()) {
                System.out.println("i'm in here2\n");
                System.out.print((char) in.readByte());
                System.out.flush();
            }
            // alternative to above while loop
            // System.out.println(in.toString(CharsetUtil.US_ASCII));
        } finally {
            // first version:
            // ReferenceCountUtil.release(msg);
            in.release();
        }
    }

    public void run() throws Exception {
        // A multithreaded event loop that handles I/O
        // boss accepts incoming connection
        // worker handles traffic of accepted connection
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // Bootstrap helper netty class that sets up a server
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    // instantiates new channel for incoming conections
                    .channel(NioServerSocketChannel.class)
                    // handler helps configure new channel
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    // TCP/IP server specific options (refer to apidocs of
                    // ChannelOption and ChannelConfig for more supported options
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start accepting incoming connections
            ChannelFuture f = b.bind(port).sync();

            // Wait until server socket closes and gracefully shut down server
            // note: this doesn't happen in this particular example, but this
            // is the usual approach
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}