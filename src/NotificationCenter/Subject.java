package NotificationCenter;

import java.util.*;

/**
 * Created by why on 11/3/15.
 */
public interface Subject {
    void register(Observer obj);
    void unregister(Observer obj);

    void notifyObservers();

    Object getUpdate(Observer obj);
}
