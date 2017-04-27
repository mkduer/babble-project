/**
 * Created by michelle on 4/26/17.
 */
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChatClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    // print any received messages to the console
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // echo the received data
        ctx.write(msg);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised
        System.out.println("Err: exception caught client-side\n");
        cause.printStackTrace();
        ctx.close();
    }
}
