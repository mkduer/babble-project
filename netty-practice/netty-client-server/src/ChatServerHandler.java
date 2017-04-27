import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by michelle on 4/26/17.
 */
public class ChatServerHandler extends ChannelInboundHandlerAdapter {

    private static final ChannelGroup channels = new DefaultChannelGroup("new-channel", GlobalEventExecutor.INSTANCE);

    @Override
    // let other clients know that a new client has joined
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.write("[SERVER] - " + incoming.remoteAddress() + " has joined!\n");
        }
        channels.add(ctx.channel());
    }

    @Override
    // let other clients know when another client leaves
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.write("[SERVER] - " + incoming.remoteAddress() + " has joined!\n");
        }
        channels.remove(ctx.channel());
    }

    @Override
    // print any received messages to the console
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            if (channel != incoming) {
                channel.write("[" + incoming.remoteAddress() + "]" + msg + "\n");
            }
        }
        // echo the received data TODO: redundant?
        /*
        ctx.write(msg);
        ctx.flush();
        */
    }

    @Override
    // Close the connection when an exception is raised
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("Err: exception caught server-side\n");
        cause.printStackTrace();
        ctx.close();
    }
}
