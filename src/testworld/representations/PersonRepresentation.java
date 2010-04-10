/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld.representations;

import processing.core.PConstants;
import processing.core.PGraphics;
import proto.behavior.Dispatcher;
import proto.behavior.IBehaviorQueue;
import proto.behavior.MultiQueue;
import proto.representation.Representation;
import testworld.objects.Person;

/**
 *
 * @author Calvin Ashmore
 */
public class PersonRepresentation extends Representation<Person> {

    private WordBubble myWordBubble;

    public PersonRepresentation(Person target) {
        super(target);
    }

    @Override
    public void render(PGraphics g) {
        g.pushMatrix();
        g.fill(255f);
        g.translate((float) getTarget().getLocation().getPosition().x, (float) getTarget().getLocation().getPosition().y);

        g.ellipse(0, -20, 20, 20);
        g.arc(0, 0, 20, 20, (float) Math.PI, 2 * (float) Math.PI);

        g.fill(0);
        g.textAlign(PConstants.CENTER);
        g.textSize(14);
        g.text(getTarget().getName(), 0, 15);

//        Dispatcher d = super.getTarget().getRole().getOwningDispatcher();
//        MultiQueue mq = d.getMultiQueue();
//        StringBuilder debug = new StringBuilder();
//        if (mq.getProactiveBehaviorQueue() != null)
//        {
//            IBehaviorQueue bq = mq.getProactiveBehaviorQueue();
//            debug.append(bq.getBehaviorTemplate().getClass().getSimpleName());
//            debug.append(":");
//            if (bq.isActive())
//                debug.append("A");
//            else if (bq.isSuspended())
//                debug.append("S");
//            else if (bq.isCancelled())
//                debug.append("C");
//            debug.append(bq.getPriority());
//            debug.append("\n");
//        }
//        for (IBehaviorQueue bq : mq.getCollaborativeBehaviorQueueSet())
//        {
//            debug.append(bq.getBehaviorTemplate().getClass().getSimpleName());
//            debug.append(":");
//            if (bq.isActive())
//                debug.append("A");
//            else if (bq.isSuspended())
//                debug.append("S");
//            else if (bq.isCancelled())
//                debug.append("C");
//            debug.append(bq.getPriority());
//            debug.append("\n");
//        }
//        for (IBehaviorQueue bq : mq.getLatentBehaviorQueueSet())
//        {
//            debug.append(bq.getBehaviorTemplate().getClass().getSimpleName());
//            debug.append(":");
//            if (bq.isActive())
//                debug.append("A");
//            else if (bq.isSuspended())
//                debug.append("S");
//            else if (bq.isCancelled())
//                debug.append("C");
//            debug.append(bq.getPriority());
//            debug.append("\n");
//        }
//        g.text(debug.toString(),0,30);
        //mq.get

        checkSpeech(g);

        // a dot to represent the zero point for this character.
        g.ellipse(0, 0, 0, 0);

        g.popMatrix();
    }

    /**
     * Returns true if the given coordinates overlap the representation.
     * @param x
     * @param y
     * @return
     */
    public boolean inRange(float x, float y) {
        x -= getTarget().getLocation().getPosition().x;
        y -= getTarget().getLocation().getPosition().y;

        return x >= -10 && x <= 10 && y >= -30 && y <= 0;
    }

    private void checkSpeech(PGraphics g) {
        if(myWordBubble != null && myWordBubble.isExpired()) {
            myWordBubble = null;
            getTarget().popSpeech();
        }

        if(myWordBubble == null && getTarget().peekSpeech() != null) {
            myWordBubble = new WordBubble(getTarget().peekSpeech());
        }

        if(myWordBubble != null) {
            myWordBubble.render(g);
        }
    }
}
