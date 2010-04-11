/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.objects;

/**
 *
 * @author Calvin Ashmore
 */
public enum PersonExpression {

    bored(0, 0, -.2),
    concerned(0, 0, -.5),
    happySmallSmile(.5, .5, 0),
    happyBigSmile(1, .5, 0),
    happyExcited(1, 1, 0),
    inquisitiveSmile(.25, .2, 0),
    feignedInterest(.25, 0, -.5),
    sympathetic(0, 0, -1),
    sympatheticSad(-.75, 0, -1),
    annoyed(-.5, 0, .5),
    angry(-1, 0, 1),
    malicious(.5, 0, 1);

    ;
    private double smileAmount; // positive values indicate smiling, negative values indicate frowning. range from -1 to 1
    private double eyebrowCurve; // from 0 to 1, where 0 is flat and 1 is most curved.
    private double eyebrowSlant; // from -1 to 1, where -1 is tilted outward (sympathetic), and 1 is tilted inward (frown)
    private boolean mouthOpen = false;

    private PersonExpression(double smileAmount, double eyebrowCurve, double eyebrowSlant) {
        this.smileAmount = smileAmount;
        this.eyebrowCurve = eyebrowCurve;
        this.eyebrowSlant = eyebrowSlant;
    }

    private PersonExpression(boolean mouthOpen, double eyebrowCurve, double eyebrowSlant) {
        this.mouthOpen = mouthOpen;
        this.eyebrowCurve = eyebrowCurve;
        this.eyebrowSlant = eyebrowSlant;
    }

    public double getEyebrowCurve() {
        return eyebrowCurve;
    }

    public double getEyebrowSlant() {
        return eyebrowSlant;
    }

    public boolean isMouthOpen() {
        return mouthOpen;
    }

    public double getSmileAmount() {
        return smileAmount;
    }
}
