/**
 * Created by michelle on 5/6/17.
 */

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/* This class initializes the netty pipeline so that it can be
used to send and receive chat messages through sockets
on either end of the pipeline

great image of this at: https://netty.io/4.1/api/io/netty/channel/ChannelPipeline.html
handlers from the image are in teh class "ServerHandler"
*/
public class xServerInit extends ChannelInitializer<SocketChannel> {

    @Override
    /*
    Overrides function in ChannelInitializer (netty class)
    This allows the bytes being set across the socket to be
    decoded and written into a string that is actually readable
    */
    protected void initChannel(SocketChannel arg0) throws Exception {
        ChannelPipeline pipeline = arg0.pipeline();

        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // handles any incoming messages from client
        pipeline.addLast("handler", new xServerHandler());
    }
}