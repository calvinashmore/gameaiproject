/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package debugger;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import proto.world.BasicObject;
import proto.world.Entity;
import proto.world.World;

/**
 *
 * @author Calvin Ashmore
 */
public class DebuggerFrame extends JFrame {

    private JPanel agentView;
    private List<DispatcherPanel> viewPanels = new ArrayList<DispatcherPanel>();

    public DebuggerFrame() {
        super("Debugger");
        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(new ControlPanel(), BorderLayout.NORTH);

        agentView = new JPanel();
        agentView.setLayout(new BoxLayout(agentView, BoxLayout.Y_AXIS));
        getContentPane().add(new JScrollPane(agentView), BorderLayout.CENTER);

        for (BasicObject basicObject : World.getInstance().getAllObjects()) {
            if (basicObject instanceof Entity) {
                Entity entity = (Entity) basicObject;
                DispatcherPanel dispatcherPanel = new DispatcherPanel(entity.getDispatcher());
                viewPanels.add(dispatcherPanel);
                agentView.add(dispatcherPanel);
            }
        }

        this.setPreferredSize(new Dimension(320,640));
    }

    public void update() {
        for (DispatcherPanel dispatcherPanel : viewPanels) {
            dispatcherPanel.update();
        }
    }
}
