/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.representations;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Calvin Ashmore
 */
public class PersonAppearance {

    public int skinColor1 = 0xffddffdd;
    public int skinColor2 = 0xff888888;
    public int eyeColor = 0xff008800;
    public int mouthColor = 0xff884444;
    public int hairColor1 = 0xff8888ff;
    public int hairColor2 = 0xff0000ff;
    public int clothesColor1 = 0xffddccaa; // surface color
    public int clothesColor2 = 0xff888888; // outline color
    public List<Integer> clothesColors = new ArrayList<Integer>();

    public Hair hair = Hair.longCurls;
    public Clothes clothes = Clothes.plain;

    public enum Hair {
        simpleTopFlat,
        simpleFullFlat,
        blob,
        straightLong,
        straightBob,
        straightMid,
        shortTopCurls,
        shortFullCurls,
        longCurls
    };

    public enum Clothes {
        plain, tuxedo
        
    }
}
