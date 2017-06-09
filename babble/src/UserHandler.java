/*
 * Created by michelle on 5/6/17.
 */
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

// Handles the messages that are sent from client
public class UserHandler extends ChannelInboundHandlerAdapter {

    public Chat room;

    UserHandler() {
        channelRoom();
    }

    @Override
    // Overrides method in ChannelInboundHandler (netty class)
    // print any received messages to the console
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        sendAlert(msg);
    }

    @Override
    // Overrides method in ChannelInboundHandler (netty class)
    // Close the connection when an exception is raised
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    // login/logout alerts
    private void sendAlert(Object msg) {
        room.chat.append((String) msg + "\n");
    }

    // Overrides method in ChannelInboundHandler (netty class)
    // print any received messages to the console
    private void channelRoom() {
        room = new Chat(Window.username);
        try {
            room.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
