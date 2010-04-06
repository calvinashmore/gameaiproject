/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package utils.math;

import java.util.Random;

/**
 *
 * @author hartsoka
 */
public class RandomManager {

    private static Random instance;

    public static Random get()
    {
        if (instance == null)
        {
            instance = new Random();
        }

        return instance;
    }

}
