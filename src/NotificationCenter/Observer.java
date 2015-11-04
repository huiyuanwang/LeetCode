package NotificationCenter;

import javax.security.auth.*;

/**
 * Created by why on 11/3/15.
 */
public interface Observer {
    void update();
    void setSubject(Subject subject);
}
