/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proto.event;

/**
 *
 * @author Calvin Ashmore
 */
public interface EventListener<EventType extends Event> {

    public void onEvent(EventType event);
}
