/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import java.util.LinkedList;
import java.util.List;
import proto.behavior.CollaborationHandshake;
import proto.behavior.Dispatcher;

/**
 *
 * @author hartsoka
 */
public class DummyDispatcherManager {

    private static List<Dispatcher> dispatchers;

    public static void register(Dispatcher d)
    {
        if (dispatchers == null)
        {
            dispatchers = new LinkedList<Dispatcher>();
        }

        dispatchers.add(d);
    }

    public static void tryCollaborate(CollaborationHandshake handshake)
    {
        for (Dispatcher d : dispatchers)
        {
            Dispatcher initiator = handshake.getInitiator();
            if (d != initiator)
            {
                d.offerCollaboration(handshake);
            }
        }
    }
}
