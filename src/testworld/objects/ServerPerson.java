/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.objects;

import testworld.representations.PersonAppearance.Clothes;

/**
 *
 * @author Calvin Ashmore
 */
public class ServerPerson extends Person {

    public ServerPerson(String name) {
        super(name, new PersonDispatcher(new ServerPersonRole()));

        this.getAppearance().clothes = Clothes.tuxedo;
        this.getAppearance().clothesColor1 = 0xffffffff;
        this.getAppearance().clothesColor2 = 0x00000000;
        this.getAppearance().clothesColors.add(0x00000000); // tie
        this.getAppearance().clothesColors.add(0xffffffff); // shirt
    }

}
