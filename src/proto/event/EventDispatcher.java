/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package proto.event;

/**
 *
 * @author Calvin Ashmore
 */
public class EventDispatcher {

    private static EventDispatcher instance;

    public static EventDispatcher getInstance() {
        if (instance == null) {
            instance = new EventDispatcher();
        }
        return instance;
    }

    public static void dispatch(Event event) {
        getInstance().dispatchImpl(event);
    }

    private void dispatchImpl(Event event) {

    }
}
