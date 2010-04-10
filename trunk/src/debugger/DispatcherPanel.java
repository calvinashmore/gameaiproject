/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package debugger;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import proto.behavior.Dispatcher;
import proto.behavior.IBehaviorQueue;
import proto.behavior.MultiQueue;

/**
 *
 * @author Calvin Ashmore
 */
public class DispatcherPanel extends JPanel {

    private Dispatcher dispatcher;
    private JTextArea textArea;

    DispatcherPanel(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        setLayout(new BorderLayout());
        add(new JLabel(dispatcher.toString()), BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(textArea, BorderLayout.CENTER);
        setPreferredSize(new Dimension(300, 150));
    }

    void update() {
        MultiQueue mq = dispatcher.getMultiQueue();
        StringBuilder debug = new StringBuilder();
        if (mq.getProactiveBehaviorQueue() != null) {
            IBehaviorQueue bq = mq.getProactiveBehaviorQueue();
            debug.append(bq.getBehaviorTemplate().getClass().getSimpleName());
            debug.append(":");
            if (bq.isActive()) {
                debug.append("A");
            } else if (bq.isSuspended()) {
                debug.append("S");
            } else if (bq.isCancelled()) {
                debug.append("C");
            }
            debug.append(bq.getPriority());
            debug.append("\n");
        }
        for (IBehaviorQueue bq : mq.getCollaborativeBehaviorQueueSet()) {
            debug.append(bq.getBehaviorTemplate().getClass().getSimpleName());
            debug.append(":");
            if (bq.isActive()) {
                debug.append("A");
            } else if (bq.isSuspended()) {
                debug.append("S");
            } else if (bq.isCancelled()) {
                debug.append("C");
            }
            debug.append(bq.getPriority());
            debug.append("\n");
        }
        for (IBehaviorQueue bq : mq.getLatentBehaviorQueueSet()) {
            debug.append(bq.getBehaviorTemplate().getClass().getSimpleName());
            debug.append(":");
            if (bq.isActive()) {
                debug.append("A");
            } else if (bq.isSuspended()) {
                debug.append("S");
            } else if (bq.isCancelled()) {
                debug.append("C");
            }
            debug.append(bq.getPriority());
            debug.append("\n");
        }
        //g.text(debug.toString(),0,30);
        textArea.setText(debug.toString());
    }
}
