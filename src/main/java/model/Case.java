package model;

import java.awt.Point;

public class Case {
    private Point point;
    private boolean bullet; // obus == bullet
    private Ship ship;

    /**
     * Create a case which will be used in a board
     * 
     * @param point Case's position
     * @param ship  Case will contains ship or not
     */

    public Case(Point point, Ship ship) {
        this.point = point;
        this.ship = ship;
        this.bullet = false;
    }

    /**
     * Check if this cas is shot or not
     * 
     * @return Is this case was shot?
     */
    public boolean isShot() {
        return this.bullet;
    }

    /**
     * Get case's ship
     * 
     * @return Case's ship
     */
    public Ship getShip() {
        return this.ship;
    }

    /**
     * Set a ship to a case
     * 
     * @param ship Choose a ship
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Shoot in this case
     */

    public void shoot() {
        this.bullet = true;
    }

    /**
     * Get case's position
     * 
     * @return Case's position
     */
    public Point getPoint() {
        return this.point;
    }

}
