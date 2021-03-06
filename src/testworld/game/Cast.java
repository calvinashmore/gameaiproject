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
import testworld.social.AttributeMap.Operation;
import testworld.social.Feelings;
import testworld.social.Needs;
import testworld.social.Personality;
import testworld.social.Relationship;

/**
 *
 * @author Calvin Ashmore
 */
public class Cast {

    public static final Person player;
    public static final Person frank;
    public static final Person harriet;
    public static final Person hilda;
    public static final Person gayle;
    public static final Person victim;
    public static final Person hughes;
    public static final Person fred;
    public static final Person rose;

    static {
        Person person;

        person = new PlayerImplementation("Player");
        person.setGender(Person.Gender.male);
        person.getLocation().getPosition().x = 220;
        person.getLocation().getPosition().y = 320;
        person.setExpression(PersonExpression.happySmallSmile);
        person.getAppearance().hair = Hair.shortParted;
        person.getAppearance().hairColor1 = 0xff000000;
        person.getAppearance().hairColor2 = 0xff000000;
        person.getAppearance().clothes = Clothes.bowtie;
        person.getAppearance().clothesColor1 = 0x00000000;
        person.getAppearance().clothesColor2 = 0x00000000;
        person.getAppearance().clothesColors.add(0x00000000);
        person.getAppearance().clothesColors.add(0xffffffff);

        person.getSocialState().setAttribute(Needs.TOILET_RATE, -100);
        person.getSocialState().setAttribute(Needs.BEVERAGE_RATE, -100);
        person.getSocialState().setAttribute(Needs.FOOD_RATE, -100);
        person.getSocialState().setAttribute(Needs.SEXDRIVE_RATE, -100);
        player = person;

        person = new GuestPerson("Frank");
        person.setGender(Person.Gender.male);
        person.getLocation().getPosition().x = 100;
        person.getLocation().getPosition().y = 300;
        person.setExpression(PersonExpression.annoyed);
        person.getAppearance().hair = Hair.blob;
        person.getAppearance().clothes = Clothes.officer;
        person.getAppearance().clothesColor1 = 0x00000000;
        person.getAppearance().clothesColor2 = 0x00000000;
        person.getAppearance().clothesColors.add(0xffff0000);
        person.getAppearance().clothesColors.add(0xffffffff);
        person.getAppearance().faceAccessory = FaceAccessory.monocle;
        person.getAppearance().eyeColor = 0xff443300;

        person.getSocialState().setAttribute(Personality.OUTLOOK, -60);
        frank = person;

        person = new GuestPerson("Harriet");
        person.setGender(Person.Gender.female);
        person.getLocation().getPosition().x = 150;
        person.getLocation().getPosition().y = 300;
        person.setExpression(PersonExpression.annoyed);
        person.getAppearance().hair = Hair.shortTopCurls;
        person.getAppearance().clothes = Clothes.plain;
        person.getAppearance().clothesColor1 = 0xff000000;
        person.getAppearance().clothesColor2 = 0xff000000;
        person.getAppearance().clothesColors.add(0xffff0000);
        person.getAppearance().clothesColors.add(0xffffffff);
        person.getAppearance().faceAccessory = FaceAccessory.earrings;
        person.getAppearance().eyeColor = 0xff663300;

        person.getSocialState().setAttribute(Personality.OUTLOOK, -30);
        person.getSocialState().getRelationship(frank).changeAttribute(Relationship.AFFECTION, -80, Operation.Set);
        harriet = person;

        person = new GuestPerson("Hilda");
        person.setGender(Person.Gender.female);
        person.getLocation().getPosition().x = 300;
        person.getLocation().getPosition().y = 400;
        person.setExpression(PersonExpression.happyExcited);
        person.getAppearance().hair = Hair.longCurls;
        person.getAppearance().hairColor1 = 0xffddaa00;
        person.getAppearance().hairColor2 = 0xffaa8800;
        person.getAppearance().width = 20;
        person.getAppearance().faceAccessory = FaceAccessory.glasses;
        person.getAppearance().clothesColor1 = 0xffaa4400;
        person.getAppearance().clothesColor2 = 0xffff0000;
        person.getAppearance().eyeColor = 0xff443344;

        person.getSocialState().changeAttribute(Needs.ALCOHOL_RATE, 1.0, Operation.Set);
        person.getSocialState().changeAttribute(Feelings.DEPRESSANT_RATE, -0.5, Operation.Set);
        person.getSocialState().changeAttribute(Needs.CIGARETTE_RATE, 1.5, Operation.Set);

        person.getSocialState().setAttribute(Personality.OUTLOOK, 30);
        hilda = person;

        person = new GuestPerson("Gayle");
        person.setGender(Person.Gender.female);
        person.getLocation().getPosition().x = 400;
        person.getLocation().getPosition().y = 200;
        person.setExpression(PersonExpression.malicious);
        person.getAppearance().hair = Hair.straightMid;
        person.getAppearance().hairColor1 = 0xff884422;
        person.getAppearance().hairColor2 = 0xff664422;
        person.getAppearance().clothesColor1 = 0xff000088;
        person.getAppearance().clothesColor2 = 0xff000000;
        person.getSocialState().changeAttribute(Needs.CIGARETTE_RATE, 2.0, Operation.Set);
        person.getSocialState().changeAttribute(Feelings.DEPRESSANT_RATE, -0.5, Operation.Set);
        person.getAppearance().eyeColor = 0xff000099;

        person.getSocialState().setAttribute(Personality.OUTLOOK, 50);
        gayle = person;

        person = new GuestPerson("Victim");
        person.setGender(Person.Gender.male);
        person.getLocation().getPosition().x = 600;
        person.getLocation().getPosition().y = 200;
        person.setExpression(PersonExpression.concerned);
        person.getAppearance().hair = Hair.topHat;
        person.getAppearance().hairColor1 = 0xff000000;
        person.getAppearance().hairColor2 = 0xff888888;
        person.getAppearance().clothes = Clothes.bowtie;
        person.getAppearance().clothesColor1 = 0x00000000;
        person.getAppearance().clothesColor2 = 0x00000000;
        person.getAppearance().clothesColors.add(0xffff0000);
        person.getAppearance().clothesColors.add(0xffffffff);

        person.getSocialState().setAttribute(Personality.OUTLOOK, 40);
        victim = person;

        person = new GuestPerson("Colonel Hughes");
        person.setGender(Person.Gender.male);
        person.getLocation().getPosition().x = 800;
        person.getLocation().getPosition().y = 300;
        person.setExpression(PersonExpression.happyExcited);
        person.getAppearance().hair = Hair.simpleTopFlat;
        person.getAppearance().hairColor1 = 0xff000000;
        person.getAppearance().hairColor2 = 0xff888888;
        person.getAppearance().clothes = Clothes.officer;
        person.getAppearance().clothesColor1 = 0x00000000;
        person.getAppearance().clothesColor2 = 0x00000000;
        person.getAppearance().clothesColors.add(0xff888800);
        person.getAppearance().clothesColors.add(0xffffffff);
        person.getSocialState().changeAttribute(Needs.ALCOHOL_RATE, 1.5, Operation.Set);
        person.getSocialState().changeAttribute(Feelings.DEPRESSANT_RATE, -0.5, Operation.Set);
        person.getAppearance().eyeColor = 0xff000066;

        person.getSocialState().setAttribute(Personality.OUTLOOK, 0);
        hughes = person;

        person = new ServerPerson("Fred");
        person.setGender(Person.Gender.male);
        person.getLocation().getPosition().x = 10;
        person.getLocation().getPosition().y = 10;
        person.setExpression(PersonExpression.feignedInterest);
        person.getAppearance().hair = Hair.simpleFullFlat;
        person.getSocialState().changeAttribute(Needs.CIGARETTE_RATE, 3.0, Operation.Set);
        person.getAppearance().eyeColor = 0xff664400;
        fred = person;

        person = new ServerPerson("Rose");
        person.setGender(Person.Gender.female);
        person.getLocation().getPosition().x = 20;
        person.getLocation().getPosition().y = -30;
        person.setExpression(PersonExpression.feignedInterest);
        person.getAppearance().hair = Hair.straightLong;
        person.getAppearance().hairColor1 = 0xff000000;
        person.getAppearance().hairColor2 = 0xff000044;
        person.getAppearance().eyeColor = 0xff663300;
        rose = person;
    }
}
