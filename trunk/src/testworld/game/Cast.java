/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.game;

import testworld.PlayerImplementation;
import testworld.objects.GuestPerson;
import testworld.objects.Person;
import testworld.objects.PersonExpression;
import testworld.objects.ServerPerson;
import testworld.representations.PersonAppearance.Clothes;
import testworld.representations.PersonAppearance.FaceAccessory;
import testworld.representations.PersonAppearance.Hair;

/**
 *
 * @author Calvin Ashmore
 */
public class Cast {

    public static final Person player;
    public static final Person frank;
    public static final Person hilda;
    public static final Person gayle;
    public static final Person fred;

    static {
        Person person;

        person = new PlayerImplementation("Player");
        person.getLocation().getPosition().x = 220;
        person.getLocation().getPosition().y = 320;
        person.setExpression(PersonExpression.happySmallSmile);
        person.getAppearance().hair = Hair.simpleFullFlat;
        person.getAppearance().clothes = Clothes.tuxedo;
        person.getAppearance().clothesColor1 = 0x00000000;
        person.getAppearance().clothesColor2 = 0x00000000;
        person.getAppearance().clothesColors.add(0x00000000);
        person.getAppearance().clothesColors.add(0xffffffff);
        player = person;

        person = new GuestPerson("Frank");
        person.getLocation().getPosition().x = 100;
        person.getLocation().getPosition().y = 300;
        person.setExpression(PersonExpression.annoyed);
        person.getAppearance().hair = Hair.blob;
        person.getAppearance().clothes = Clothes.tuxedo;
        person.getAppearance().clothesColor1 = 0x00000000;
        person.getAppearance().clothesColor2 = 0x00000000;
        person.getAppearance().clothesColors.add(0x00000000);
        person.getAppearance().clothesColors.add(0xffffffff);
        person.getAppearance().faceAccessory = FaceAccessory.monocle;
        frank = person;

        person = new GuestPerson("Hilda");
        person.getLocation().getPosition().x = 300;
        person.getLocation().getPosition().y = 400;
        person.setExpression(PersonExpression.happyExcited);
        person.getAppearance().hair = Hair.longCurls;
        person.getAppearance().width = 20;
        hilda = person;

        person = new GuestPerson("Gayle");
        person.getLocation().getPosition().x = 400;
        person.getLocation().getPosition().y = 200;
        person.setExpression(PersonExpression.malicious);
        person.getAppearance().hair = Hair.straightMid;
        gayle = person;

        person = new ServerPerson("Fred");
        person.getLocation().getPosition().x = 10;
        person.getLocation().getPosition().y = 10;
        person.setExpression(PersonExpression.feignedInterest);
        person.getAppearance().hair = Hair.simpleFullFlat;
        fred = person;
    }
}
