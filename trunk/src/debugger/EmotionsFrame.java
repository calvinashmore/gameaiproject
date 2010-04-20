/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package debugger;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import proto.world.BasicObject;
import proto.world.World;
import testworld.objects.Person;

/**
 *
 * @author hartsoka
 */
public class EmotionsFrame extends JFrame {

    private JPanel agentView;
    private List<EmotionsPanel> viewPanels = new ArrayList<EmotionsPanel>();

    public EmotionsFrame() {
        super("EmotionsDebugger");
        getContentPane().setLayout(new BorderLayout());

        //getContentPane().add(new ControlPanel(), BorderLayout.NORTH);

        agentView = new JPanel();
        agentView.setLayout(new BoxLayout(agentView, BoxLayout.Y_AXIS));
        getContentPane().add(new JScrollPane(agentView), BorderLayout.CENTER);

        for (BasicObject basicObject : World.getInstance().getAllObjects()) {
            if (basicObject instanceof Person) {
                Person person = (Person) basicObject;
                EmotionsPanel emotionsPanel = new EmotionsPanel(person);
                viewPanels.add(emotionsPanel);
                agentView.add(emotionsPanel);
            }
        }
    }

    public void update() {
        for (EmotionsPanel emotionsPanel : viewPanels) {
            emotionsPanel.update();
        }
    }

}
