/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
