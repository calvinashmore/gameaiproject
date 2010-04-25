/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package debugger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import testworld.objects.Person;
import testworld.social.AttributeInfo;
import testworld.social.AttributeMap;
import testworld.social.SocialState;

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
        JScrollPane scroll = new JScrollPane(moodBars);

        this.add(scroll, BorderLayout.CENTER);
        this.add(new JLabel("     "), BorderLayout.EAST); // hack for visible scrollbars

        setPreferredSize(new Dimension(300, 150));
        scroll.setPreferredSize(new Dimension(240, 150));
    }

    void update() {
        moodBars.update();
    }

    private class MoodBars extends JPanel
    {
        private Person person;
        private Map<String, JProgressBar> progressBars;
        private GridBagConstraints gbc;

        private void addAttributeSection()
        {
            gbc.gridy++;

            gbc.gridx = 0;
            gbc.weightx = 0.3;
            JLabel label = new JLabel("---------------");
            label.setSize(50, 20);
            label.setBackground(Color.yellow);
            super.add(label, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.7;
            label = new JLabel("---------------");
            label.setSize(50, 20);
            label.setBackground(Color.yellow);
            super.add(label, gbc);
        }

        private void addAttributeBar(String attribute)
        {
            gbc.gridy++;

            gbc.gridx = 0;
            gbc.weightx = 0.3;
            JLabel label = new JLabel(attribute);
            label.setSize(50, 20);
            super.add(label, gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.7;
            JProgressBar bar = new JProgressBar();
            bar.setForeground(Color.green);
            super.add(bar, gbc);
            
            progressBars.put(attribute, bar);
        }

        public MoodBars(Person person) {
            this.person = person;
            this.progressBars = new TreeMap<String, JProgressBar>();

            gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            this.setLayout(new GridBagLayout());
            //this.setLayout(new GridLayout(rows, 2, 2, 2));

            for (AttributeMap map : person.getSocialState().getPermanentMaps())
            {
                this.addAttributeSection();
                for (Entry<String,Double> entry : map.getValues())
                {
                    if (entry.getKey().endsWith("Rate")) continue;
                    this.addAttributeBar(entry.getKey());
                }
            }
        }

        public void update()
        {
            AttributeInfo info = AttributeInfo.getInstance();

            SocialState e = person.getSocialState();

            for (Map.Entry<String,JProgressBar> bar : progressBars.entrySet())
            {
                String key = bar.getKey();
                int value = (int)(e.getAttribute(key) - info.minimums.get(key));
                bar.getValue().setMaximum((int)(info.maximums.get(key) - info.minimums.get(key)));
                bar.getValue().setValue(value);
            }
        }
    }

}
