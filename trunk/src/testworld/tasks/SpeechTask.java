/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import testworld.objects.Person;

/**
 *
 * @author Calvin Ashmore
 */
public class SpeechTask extends PersonTask {

    boolean started = false;
    private String line;
    private Person talkingTo;

    public SpeechTask(String line) {
        this.line = line;
    }

    public SpeechTask(String line, Person talkingTo) {
        this.line = line;
        this.talkingTo = talkingTo;
    }

    public void resume() {
    }

    public void run() {

        if (!started) {
            getPerson().pushSpeech(line);
            started = true;
        }

        // Check whether speech has been completed.
        // NOTE: This uses equality on Strings.
        // this is intentional. We check to see if the person is speaking this line, 
        // if the person is no longer speaking this line, then mark the task as done.
        boolean found = false;
        for (String s : getPerson().getSpeechQueue())
        {
            // we can't use .contains() because we want reference equality,
            //  not object equality
            if (s == line) found = true;
        }
        if (!found)
        {
            finished();
        }

        // Make sure we have eye contact
        if (talkingTo != null) {
            getPerson().setLookAt(talkingTo.getLocation().getPosition());
        }
    }
}
