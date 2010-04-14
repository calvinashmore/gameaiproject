/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.objects;

import testworld.behaviors.ConversationWeatherProactive;
import testworld.behaviors.ConversationWeatherReactive;
import testworld.behaviors.GroupChat;
import testworld.behaviors.LatentExclamativeBehavior;
import testworld.behaviors.RequestAndServeBehavior;

/**
 *
 * @author hartsoka
 */
public class GuestPersonRole extends PersonRole {

    public GuestPersonRole()
    {
        super();

        addBehaviorTemplate(new LatentExclamativeBehavior());

        addBehaviorTemplate(new ConversationWeatherProactive());
        addBehaviorTemplate(new ConversationWeatherReactive());

        addBehaviorTemplate(GroupChat.makeProactive());
        addBehaviorTemplate(GroupChat.makeReactive());

        addBehaviorTemplate(RequestAndServeBehavior.makeRequestBehavior());
    }

}
