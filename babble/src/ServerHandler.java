/*
 * Created by michelle on 5/6/17.
 */

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/* Handles the messages that are sent from client to server
Depending on how print message is formatted:
System.out.print.ln will print to the server window
channel.writeAndFlush will write to the client
*/
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    // Overrides method in ChannelHandler (netty class)
    // adds channels and alerts other clients that a new client has arrived
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            // print to server
            System.out.println("[SERVER0] - " + incoming.remoteAddress() + " has joined!\n");
            // print to channel
            channel.writeAndFlush("HELLO~~~ " + incoming.remoteAddress() + " has joined!\n");
        }
        channels.add(incoming);
    }

    @Override
    // Overrides method in ChannelHandler (netty class)
    // let other clients know when another client leaves
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            // print to server
            String message = "[SERVER3] - " + incoming.remoteAddress() + " has left!\n";
            // print to channel
            channel.writeAndFlush("BYE BYE~~~ " + incoming.remoteAddress() + " has left!\n");
        }
        channels.remove(incoming);
    }

    @Override
    // Overrides method in ChannelInboundHandler (netty class)
    // print any received messages to the console
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            if (channel != incoming) {
                // print message to server
                String message = "[" + incoming.remoteAddress() + "]" + msg + "\n";
                // print message to channel
                channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + msg + "\n");
            }
        }
    }

    @Override
    // Overrides method in ChannelInboundHandler (netty class)
    // Close the connection when an exception is raised
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
