/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.objects;

import testworld.behaviors.ApproachBehavior;
import testworld.behaviors.GroupChat;
import testworld.behaviors.RequestAndServeBehavior;
import testworld.behaviors.TakeSip;
import testworld.behaviors.UseODevours;
import testworld.behaviors.UseRandomAnnotatedItem;
import testworld.behaviors.conversations.ApproachConversationBehavior;
import testworld.behaviors.conversations.ConversationBehavior;
import testworld.objects.annotated.DanceFloor;

/**
 *
 * @author hartsoka
 */
public class GuestPersonRole extends PersonRole {

    public GuestPersonRole() {
        super();

        addBehaviorTemplate(ConversationBehavior.makeProactive());
        addBehaviorTemplate(ConversationBehavior.makeReactive());

        addBehaviorTemplate(ApproachBehavior.makeProactive());
        addBehaviorTemplate(ApproachBehavior.makeReactive());

        addBehaviorTemplate(GroupChat.makeProactive());
        addBehaviorTemplate(GroupChat.makeReactive());

        addBehaviorTemplate(RequestAndServeBehavior.makeRequestBehavior());

        addBehaviorTemplate(new UseRandomAnnotatedItem(DanceFloor.class));
        addBehaviorTemplate(new UseODevours());
        addBehaviorTemplate(new TakeSip());
    }
}
