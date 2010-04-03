/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import proto.behavior.ATask;
import proto.behavior.Dispatcher;
import proto.world.World;
import testworld.Testworld;
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;

/**
 * Helpful task that includes some utility methods that are common to tasks
 * performable by instances of Person.
 * @author Calvin Ashmore
 */
abstract public class PersonTask extends ATask {

    public PersonTask(Dispatcher d) {
        super(d);
    }

    public Person getPerson() {
        return ((PersonDispatcher) dispatcher).getPerson();
    }

    public Testworld getWorld() {
        return (Testworld) World.getInstance();
    }

    public void finished() {
        dispatcher.handleTaskDone(bq);
    }
}
