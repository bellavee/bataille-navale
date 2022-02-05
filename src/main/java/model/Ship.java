package model;

public class Ship {
    private int weight;

    /**
     * Contructs a ship with weight
     * 
     * @param weight Ship will be big or small: 5-4-3-3-2
     */

    public Ship(int weight) {
        this.weight = weight;
    }

    /**
     * If ship was shot, weight deceases 1 value
     */
    public void isShot() {
        this.weight--;
    }

    /**
     * Get ship's weight
     * 
     * @return Ship's weight
     */
    public int getWeight() {
        return this.weight;
    }

}
