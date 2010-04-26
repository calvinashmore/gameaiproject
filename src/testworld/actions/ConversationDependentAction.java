/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.actions;

import proto.world.*;
import testworld.game.*;
import proto.game.PlayerAction;
import testworld.actions.BasicConversationAction;
import testworld.behaviors.conversations.ConversationContent;
import testworld.objects.Person;

/**
 * This is a player action that is dependent on a token. The action can only be performed if the
 * player clicks on the correct person, and has the appropriate token.
 * @author Calvin Ashmore
 */
public class ConversationDependentAction extends DependentAction{

    private Token myToken;
    private ConversationContent conversation;

    public ConversationDependentAction(Token myToken, ConversationContent conversation, String name) {
        super(name);
        this.myToken = myToken;
        this.conversation = conversation;
    }

    public boolean canActivate() {
        return myToken == null || myToken.isFound();
    }

    public PlayerAction createAction(Entity other) {
        return new BasicConversationAction((Person) other, name, name, conversation);
    }
}
