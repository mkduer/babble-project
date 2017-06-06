/*
 * Created by michelle on 5/6/17.
 */
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

// Handles the messages that are sent from client
public class xUserHandler extends ChannelInboundHandlerAdapter {

    @Override
    // Overrides method in ChannelInboundHandler (netty class)
    // print any received messages to the console
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(msg);
    }

    @Override
    // Overrides method in ChannelInboundHandler (netty class)
    // Close the connection when an exception is raised
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}