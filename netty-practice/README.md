# Netty

### great resources:
* [Netty Download](http://netty.io/downloads.html)
* [Netty API Overview](http://netty.io/4.0/api/overview-summary.html): all the available netty APIs. Good for learning more about specific classes, methods and arguments, implementation examples, and more.
* [Netty Wiki](http://netty.io/wiki/): lots of netty coding examples -- I used this to have an overview on how netty is implemented (e.g. netty-secure above). This is how I realized I needed 3 classes for each server and client.
* [In-Depth Netty Explainer](http://seeallhearall.blogspot.com/2012/05/netty-tutorial-part-1-introduction-to.html): amazingly thorough breakdown of netty and all of its bits and pieces -- very geeky, helpful, and broken down as much into layman's terms as possible

### netty-echo
Runs through the netty.io tutorial available at [http://netty.io/wiki/user-guide-for-4.x.html](http://netty.io/wiki/user-guide-for-4.x.html) to set-up a Discard Server. As the Discard Server literally discards any messages that are sent to it, this was edited to become an Echo Server. To get the server running:


(1) Run the Discard Server (I ran it on my Intellij IDEA IDE)

(2) Connect to the port in the terminal using the following command:
```
telnet localhost 8080
```

(3) If the connection is successful, try typing anything and it should be echoed back -- this means success connecting to the server and that it is echoing back what you typed, yay!

### netty-secure
This attempts to implement the secure connection that is provided on the [netty website](http://netty.io/wiki/). I didn't have success compiling this and am thinking it may be related to needing an actual security certificate to get this implemented -- a bit beyond the scope of my understanding, but it was a good exercise in practicing the overall netty *template* needed to get client-servers set-up.

### netty-client-server
This sets-up the client-server to get communication going (this is basically the chat application foundation!). I also used this [tutorial](https://www.youtube.com/watch?v=tsz-assb1X8) to get it set-up. Some differences:
* I didn't use Maven to organize the project
* The program in the video uses an older version with deprecated classes and correlating methods, so I had to find other netty classes to get the program working.
* I didn't have any success using the video's implementation of outputting the clients' messages. This took quite a bit of research, but replacing the write function with writeAndFlush resolved this problem. I'm not entirely sure whether the flush is equivalent to, say, C++'s cin.ignore() function, that is what it seems to be doing by flushing the buffer. However, I'm not sure that this is the most efficient way to deal with the output based on some documentation I read saying that frequent flushing is actually inefficient and there is a better way to go about the process.
