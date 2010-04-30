/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.actions;

import proto.world.*;
import testworld.game.*;
import proto.game.PlayerAction;
import testworld.behaviors.conversations.ConversationContent;
import testworld.objects.Person;

/**
 * This is a player action that is dependent on a token. The action can only be performed if the
 * player clicks on the correct person, and has the appropriate token.
 * @author Calvin Ashmore
 */
public class ConversationDependentAction extends DependentAction {

    private Token[] myTokens;
    private Token expireToken;
    private ConversationContent conversation;

    public ConversationDependentAction(ConversationContent conversation, String name, Token... myTokens) {
        this(null, conversation, name, myTokens);
    }

    public ConversationDependentAction(Token expireToken, ConversationContent conversation, String name, Token... myTokens) {
        super(name);
        this.myTokens = myTokens;
        this.expireToken = expireToken;
        this.conversation = conversation;
    }

    public boolean canActivate() {
        boolean okay = true;
        if (myTokens != null) {
            for (Token token : myTokens) {
                okay &= token == null || token.isFound();
            }
        }

        return (okay) && (expireToken == null || !expireToken.isFound());
    }

    public PlayerAction createAction(Entity other) {
        return new BasicConversationAction((Person) other, name, name, conversation);
    }
}
