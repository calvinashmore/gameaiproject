/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.objects;

import testworld.representations.PersonAppearance.Clothes;
import testworld.social.Needs;

/**
 *
 * @author Calvin Ashmore
 */
public class ServerPerson extends Person {

    public ServerPerson(String name) {
        super(name, new PersonDispatcher(new ServerPersonRole()));

        this.getAppearance().clothes = Clothes.bowtie;
        this.getAppearance().clothesColor1 = 0xffffffff;
        this.getAppearance().clothesColor2 = 0x00000000;
        this.getAppearance().clothesColors.add(0x00000000); // tie
        this.getAppearance().clothesColors.add(0xffffffff); // shirt

        this.getSocialState().setAttribute(Needs.BEVERAGE_RATE, 0.0);
        this.getSocialState().setAttribute(Needs.FOOD_RATE, 0.0);
        this.getSocialState().setAttribute(Needs.GOSSIP_RATE, 0.0);
    }

}
