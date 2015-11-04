package NotificationCenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by why on 11/3/15.
 */
public class MyTopic implements Subject {
    List<Observer> objs;
    String message;
    boolean changed;
    private final Object MUTEX = new Object();

    MyTopic() {
        objs = new ArrayList<>();
    }

    @Override
    public void register(Observer obj) {
        if (obj == null) throw new NullPointerException();
        synchronized (MUTEX) {
            if (! objs.contains(obj)) objs.add(obj);
        }
    }

    @Override
    public void unregister(Observer obj) {
        synchronized (MUTEX) {
            objs.remove(obj);
        }
    }

    @Override
    public void notifyObservers() {
        List<Observer> objList;
        synchronized (MUTEX) {
            if (! changed) return;
            objList = new ArrayList<>(objs);
            this.changed = false;
        }
        for (Observer obj: objList) {
            obj.update();
        }
    }

    public void postMsg(String msg) {
        this.message = msg;
        this.changed = true;
        notifyObservers();
    }

    @Override
    public Object getUpdate(Observer obj) {
        return message;
    }
}
