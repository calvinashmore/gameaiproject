/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package debugger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import testworld.objects.Person;
import testworld.social.AttributeMap;
import testworld.social.Emotions;
import testworld.social.Personality;
import testworld.social.Stimuli;

/**
 *
 * @author hartsoka
 */
public class EmotionsPanel extends JPanel {

    private MoodBars moodBars;

    public EmotionsPanel(Person person)
    {
        setLayout(new BorderLayout());
        add(new JLabel(person.getName()), BorderLayout.NORTH);

        moodBars = new MoodBars(person);

        add(moodBars, BorderLayout.CENTER);
        setPreferredSize(new Dimension(300, 150));
    }

    void update() {
        moodBars.update();
    }

    private class MoodBars extends JPanel
    {
        private Person person;
        private Map<String, JProgressBar> progressBars;

        private void addAttributeBar(String attribute)
        {
            super.add(new JLabel(attribute));
            JProgressBar bar = new JProgressBar();
            super.add(bar);
            bar.setForeground(Color.green);
            progressBars.put(attribute, bar);
        }

        public MoodBars(Person person) {
            this.person = person;
            this.progressBars = new TreeMap<String, JProgressBar>();

            int rows = Personality.Trait.values().length +
                        Stimuli.Effect.values().length +
                        Stimuli.Need.values().length;
            this.setLayout(new GridLayout(rows, 2, 2, 2));

            for (Personality.Trait t : Personality.Trait.values()) {
                this.addAttributeBar(t.toString());
            }
            for (Stimuli.Effect t : Stimuli.Effect.values()) {
                this.addAttributeBar(t.toString());
            }
            for (Stimuli.Need t : Stimuli.Need.values()) {
                this.addAttributeBar(t.toString());
            }
        }

        public void update()
        {
            Emotions e = person.getEmotions();
            List<AttributeMap> maps = e.getDefaultAttributeMaps();

            for (Map.Entry<String,JProgressBar> bar : progressBars.entrySet())
            {
                int value = (int)e.debugGetAttribute(maps, bar.getKey());
                bar.getValue().setMaximum(100);
                bar.getValue().setValue(value);
            }
        }
    }

}
