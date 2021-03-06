/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors;

import java.util.LinkedList;
import java.util.List;
import proto.behavior.ABehaviorTemplate;
import proto.behavior.BehaviorQueue;
import proto.behavior.IBehaviorQueue;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import proto.world.BasicObject;
import proto.world.World;
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;
import testworld.objects.annotated.AAnnotatedItem;
import testworld.tasks.UseAnnotatedItem;
import utils.math.RandomManager;

/**
 *
 * @author hartsoka
 */
public class UseRandomAnnotatedItem
        extends ABehaviorTemplate
        implements IProactiveBehavior
{
    public static final int MAX_RANDOM_IMPORTANCE = 10;

    protected Class type = null;

    public UseRandomAnnotatedItem() {
        this(AAnnotatedItem.class);
    }

    public UseRandomAnnotatedItem(Class type) {
        super(InitiationType.proactive, CollaborationType.independent);
        this.type = type;
    }

    public String getId() {
        return "UseAnnotatedItem";
    }

    public IBehaviorQueue instantiate(IWorldState ws)
    {
        if (type == null)
        {
            return this.instantiateImpl(ws, AAnnotatedItem.class);
        }
        return this.instantiateImpl(ws, type);
    }

    protected IBehaviorQueue instantiateImpl(IWorldState ws, Class cl)
    {
        Person me = ((PersonDispatcher)this.getDispatcher()).getPerson();

        List<BasicObject> items = World.getInstance().getAllObjectsOfType(cl);
        List<AAnnotatedItem> legalItems = new LinkedList<AAnnotatedItem>();

        for (BasicObject bo : items)
        {
            AAnnotatedItem item = (AAnnotatedItem)bo;
            if (item.isAllowedToUse(me))
            {
                legalItems.add(item);
            }
        }

        AAnnotatedItem selection = legalItems.get(RandomManager.get().nextInt(legalItems.size()));

        IBehaviorQueue bq = new BehaviorQueue(this);
        bq.queueTask(new UseAnnotatedItem(selection));
        return bq;
    }

    public int getImportance(IWorldState ws) {
        if (RandomManager.get().nextDouble() < .2)
            return RandomManager.get().nextInt(MAX_RANDOM_IMPORTANCE);
        return 0;
    }
}
