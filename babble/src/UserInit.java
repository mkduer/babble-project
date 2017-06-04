/*
 * Created by michelle on 5/6/17.
 */

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

// initialize socket channels
public class UserInit extends ChannelInitializer<SocketChannel> {

    @Override
    // Overrides function in ChannelInitializer (netty class)
    protected void initChannel(SocketChannel arg0) throws Exception {
        // organize communication with netty pipelines
        ChannelPipeline pipeline = arg0.pipeline();

        /* specify the type expected (frames of 8192 size),
        then decode bytes into strings that are readable
        and encode strings into bytes for sending to the server
        */
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // handle all of the decoded strings coming in from the server
        pipeline.addLast("handler", new UserHandler());
    }

}