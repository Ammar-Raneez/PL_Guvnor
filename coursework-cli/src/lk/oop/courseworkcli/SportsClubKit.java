package lk.oop.courseworkcli;

import java.awt.*;
import java.io.Serializable;

//helper class to create kits for clubs
class SportsClubKit implements Serializable {
    private Color bottomColor;
    private String sponsor;
    private Color topColor;

    /**
     * initializes a sports club kit
     * @param sponsor - sponsor of the club
     * @param topColor - t-shirt color
     * @param bottomColor - short color
     */
    public SportsClubKit(String sponsor, Color topColor, Color bottomColor) {
        this.sponsor = sponsor;
        this.topColor = topColor;
        this.bottomColor = bottomColor;
    }

    /**
     * @return bottom color
     */
    public Color getBottomColor() {
        return bottomColor;
    }

    /**
     * sets color of bottom
     * @param bottomColor - color of bottom
     */
    public void setBottomColor(Color bottomColor) {
        this.bottomColor = bottomColor;
    }

    /**
     * @return sponsor
     */
    public String getSponsor() {
        return sponsor;
    }

    /**
     * sets sponsor of club
     * @param sponsor - sponsor of the club
     */
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    /**
     * @return top color
     */
    public Color getTopColor() {
        return topColor;
    }

    /**
     * sets color of top
     * @param topColor - color of top
     */
    public void setTopColor(Color topColor) {
        this.topColor = topColor;
    }

    /**
     * @return overrun toString() method
     */
    @Override
    public String toString() {
        return "ClubKit{" +
                "sponsor='" + sponsor + '\'' +
                ", topColor=" + topColor +
                ", bottomColor=" + bottomColor +
                '}';
    }
}
