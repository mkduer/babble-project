/**
 * Created by michelle on 4/26/17.
 */

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends ChannelInboundHandlerAdapter {

    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    // adds channels and alerts other clients that a new client has arrived
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            // print to server
            System.out.println("[SERVER] - " + incoming.remoteAddress() + " has joined!\n");
            // print to channel
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " has joined!\n");
        }
        channels.add(incoming);
    }

    @Override
    // let other clients know when another client leaves
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            // print to server
            System.out.println("[SERVER] - " + incoming.remoteAddress() + " has left!\n");
            // print to channel
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " has left!\n");
        }
        channels.remove(incoming);
    }

    @Override
    // print any received messages to the console
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            if (channel != incoming) {
                // print message to server
                System.out.println("[" + incoming.remoteAddress() + "]" + msg + "\n");
                // print message to channel
                channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + msg + "\n");
            }
        }
    }

    @Override
    // Close the connection when an exception is raised
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
