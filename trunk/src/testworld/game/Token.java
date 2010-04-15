/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.game;

/**
 *
 * @author Calvin Ashmore
 */
public class Token {

    private String id;
    private String description;
    private boolean found = false;

    public Token(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFound() {
        return found;
    }

    public String getId() {
        return id;
    }
}
