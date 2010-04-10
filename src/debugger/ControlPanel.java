/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package debugger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import proto.world.World;

/**
 *
 * @author Calvin Ashmore
 */
public class ControlPanel extends JPanel {

    JButton playPause;

    public ControlPanel() {
        playPause = new JButton("Pause");
        playPause.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                onPlayPause();
            }
        });
        add(playPause);
    }

    private void onPlayPause() {
        if (World.getInstance().isPaused()) {
            // play
            playPause.setText("Pause");
            World.getInstance().setPaused(false);
        } else {
            // pause
            playPause.setText("Play");
            World.getInstance().setPaused(true);
        }
    }
}
