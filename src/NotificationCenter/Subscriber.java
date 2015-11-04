package NotificationCenter;

/**
 * Created by why on 11/3/15.
 */
public class Subscriber implements Observer {
    private Subject topic;

    @Override
    public void update() {
        String msg = (String) topic.getUpdate(this);
        if (msg != null) {
            System.out.println(msg);
        }
    }

    @Override
    public void setSubject(Subject subject) {
        this.topic = subject;
    }
}
