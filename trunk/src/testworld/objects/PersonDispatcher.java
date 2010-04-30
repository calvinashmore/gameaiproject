/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld.objects;

import proto.behavior.CollaborationHandshake;
import proto.behavior.Dispatcher;
import testworld.social.AttributeInfo;
import testworld.social.Needs;
import testworld.social.SocialState;

/**
 *
 * @author Calvin Ashmore
 */
public class PersonDispatcher extends Dispatcher {

    private Person person;

    public Person getPerson() {
        return person;
    }

    void setPerson(Person person) {
        this.person = person;
    }

    public PersonDispatcher() {
        super(new PersonRole());
    }

    public PersonDispatcher(PersonRole role) {
        super(role);
    }

    @Override
    public String toString() {
        return person.getName();
    }

    @Override
    public void handleTimer() {
        this.getPerson().getSocialState().update();
        super.handleTimer();
    }

    @Override
    public void offerCollaboration(CollaborationHandshake handshake)
    {
        // not a cutscene, so don't interrupt this character if they have a very
        //  high need of some kind which they need to attend to
        if (handshake.getBlackboard().get("CUTSCENE") == null)
        {
            SocialState s = this.getPerson().getSocialState();
            if (s.getAttribute(Needs.TOILET) > 80) return;
            //if (s.getAttribute(Needs.ALCOHOL) > 80) return;
            if (s.getAttribute(Needs.COCAINE) > 80) return;
            if (s.getAttribute(Needs.BEVERAGE) > 80) return;
            if (s.getAttribute(Needs.FOOD) > 80) return;
            if (s.getAttribute(Needs.SLEEP) > 80) return;
        }
        super.offerCollaboration(handshake);
    }
}
