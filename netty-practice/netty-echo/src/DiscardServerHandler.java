/*
 * Created by michelle on 4/25/17.
 * Directly from the netty.io source on how to learn to use netty
 * http://netty.io/wiki/user-guide-for-4.x.html
 *
 * Server echo can be tested by typing
 * telnet localhost 8080
 * in the terminal
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


// NIO version of 'Hello World". It discards any received data without response
// Altered for testing so that it doesn't discard the received data
// The parent class is provided in netty to handle various event handler methods
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    // This is called with the msg object as an argument
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Discard the received data silently
        //((ByteBuf) msg).release();

        // echo the received data
        ctx.write(msg);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised
        cause.printStackTrace();
        ctx.close();
    }
}