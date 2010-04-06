/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package proto.event;

/**
 *
 * @author Calvin Ashmore
 */
public interface EventListener<EventType extends Event> {

    public void onEvent(EventType event);
}
