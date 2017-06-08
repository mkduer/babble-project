/**
 * Created by michelle on 6/7/17.
 */
public class Message {
   protected String message;

    Message() {
        this.message = null;
   }

    public Object getMsg(Object msg) {
                                           return msg;
   }

    public void readMsg(Object msg) {
        this.message = (String) msg;
   }

    public void display(Object msg) {
        System.out.println(msg);
    }

}
