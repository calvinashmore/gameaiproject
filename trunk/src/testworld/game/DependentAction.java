/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.game;

import proto.game.PlayerAction;
import testworld.actions.BasicConversationAction;
import testworld.behaviors.conversations.ConversationContent;
import testworld.objects.Person;

/**
 * This is a player action that is dependent on a token. The action can only be performed if the
 * player clicks on the correct person, and has the appropriate token.
 * @author Calvin Ashmore
 */
public class DependentAction {

    private Token myToken;
    private ConversationContent conversation;
    private String name;
    private String description;

    public DependentAction(Person other, Token myToken, ConversationContent conversation, String name, String description) {
        this.myToken = myToken;
        this.conversation = conversation;
        this.name = name;
        this.description = description;
    }

    public boolean canActivate() {
        return myToken == null || myToken.isFound();
    }

    public PlayerAction createAction(Person other) {
        return new BasicConversationAction(other, name, description, conversation);
    }
}