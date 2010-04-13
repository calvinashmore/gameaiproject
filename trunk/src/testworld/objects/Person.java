/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld.objects;

import java.util.ArrayList;
import java.util.List;
import proto.behavior.BehaviorQueue;
import proto.behavior.IProactiveBehavior;
import proto.behavior.MultiQueue.QueueSet;
import proto.navigation.BoundingSphere;
import proto.world.Entity;
import proto.world.World;
import testworld.representations.PersonAppearance;
import testworld.representations.PersonRepresentation;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class Person extends Entity {

    private String name;
    private List<String> speech = new ArrayList<String>();
    private Vector2d lookAt = new Vector2d();
    private PersonAppearance appearance = new PersonAppearance();
    private PersonExpression expression = PersonExpression.happySmallSmile;

    public Person(String name) {
        super(new PersonDispatcher());
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

    public void instantiateNewProactiveBehavior(IProactiveBehavior behavior) {
        BehaviorQueue bq = (BehaviorQueue) behavior.instantiate(World.getInstance());
        bq.setPriority(3);
        getDispatcher().handleNewBehavior(bq, QueueSet.pro);
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

    public List<String> getSpeechQueue()
    {
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
}
