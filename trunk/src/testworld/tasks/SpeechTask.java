/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

/**
 *
 * @author Calvin Ashmore
 */
public class SpeechTask extends PersonTask {

    boolean started = false;
    private String line;

    public SpeechTask(String line) {
        this.line = line;
    }

    public void resume() {
    }

    public void run() {

        if (!started) {
            getPerson().pushSpeech(line);
            started = true;
        }

        // NOTE: This uses equality on Strings.
        // this is intentional. We check to see if the person is speaking this line, 
        // if the person is no longer speaking this line, then mark the task as done.
        if (getPerson().peekSpeech() != line) {
            finished();
        }
    }
}
