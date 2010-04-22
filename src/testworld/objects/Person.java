/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld.objects;

import testworld.social.Emotions;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import proto.behavior.BehaviorQueue;
import proto.behavior.IProactiveBehavior;
import proto.behavior.MultiQueue.QueueSet;
import proto.navigation.BoundingSphere;
import proto.world.Entity;
import proto.world.World;
import testworld.game.BasicConversations;
import testworld.behaviors.conversations.ConversationContent;
import testworld.game.DependentAction;
import testworld.representations.PersonAppearance;
import testworld.representations.PersonRepresentation;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class Person extends Entity implements Comparable {

    private String name;
    private List<String> speech = new ArrayList<String>();
    private Vector2d lookAt = new Vector2d();
    private PersonAppearance appearance = new PersonAppearance();
    private PersonExpression expression = PersonExpression.happySmallSmile;
    private Emotions emotions = new Emotions();
    private List<ConversationContent> conversations = BasicConversations.conversations;
    private List<DependentAction> dependentActions = new ArrayList<DependentAction>();
    private Gender gender;

    public int compareTo(Object o) {
        return this.name.compareTo(((Person)o).name);
    }

    public enum Gender {

        male, female
    };

    public Person(String name) {
        this(name, new PersonDispatcher());
    }

    public Person(String name, PersonDispatcher dispatcher) {
        super(dispatcher);
        ((PersonDispatcher) getDispatcher()).setPerson(this);
        this.name = name;
        setRepresentation(new PersonRepresentation(this));
        setCollisionVolume(new BoundingSphere(20));
    }

    public PersonRole getRole() {
        return (PersonRole) getDispatcher().getRole();
    }

    @Override
    public PersonDispatcher getDispatcher() {
        return (PersonDispatcher) super.getDispatcher();
    }

    public String getName() {
        return name;
    }

    public Emotions getEmotions() {
        return emotions;
    }

    public void instantiateNewProactiveBehavior(IProactiveBehavior behavior) {
        behavior.setOwningRole(getRole());
        BehaviorQueue bq = (BehaviorQueue) behavior.instantiate(World.getInstance());
        if (bq != null) {
            bq.setPriority(3);
            getDispatcher().handleNewBehavior(bq, QueueSet.PROACTIVE_INDEPENDENT);
        }
    }

    /**
     * Removes the current line from the person's speech.
     */
    public void popSpeech() {
        if (speech.size() > 0) {
            speech.remove(0);
        }
    }

    /**
     * Returns the string that the person is currently speaking.
     * Returns null if nothing is being said.
     * @return
     */
    public String peekSpeech() {
        if (speech.size() == 0) {
            return null;
        } else {
            return speech.get(0);
        }
    }

    /**
     * Adds the given line to speech for the character to say.
     * @param line
     */
    public void pushSpeech(String line) {
        speech.add(line);
    }

    public List<String> getSpeechQueue() {
        return this.speech;
    }

    public Vector2d getLookAt() {
        return lookAt;
    }

    public void setLookAt(Vector2d lookAt) {
        this.lookAt = lookAt;
    }

    public PersonAppearance getAppearance() {
        return appearance;
    }

    public PersonExpression getExpression() {
        return expression;
    }

    public void setExpression(PersonExpression expression) {
        this.expression = expression;
    }

    public ConversationContent makeConversation(Person reactor) {
        int index = new Random().nextInt(conversations.size());
        return conversations.get(index);
    }

    public void addDependentAction(DependentAction action) {
        dependentActions.add(action);
    }

    public List<DependentAction> getDependentActions() {
        return dependentActions;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
