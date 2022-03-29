/* 
 * This class is a subclass of Plant, thus, a lot of
 * Vegetables' attributes are inherited from Plant, including
 * toString, typeID, and plot.
 */
public class Vegetables extends Plant {
    public Vegetables(String type) {
        /*
         * Constructor method for Vegetables. Executes Plant's overload
         * constructor and adds a veggie type at the bottom of the plot.
         */
        super(type);
        this.plant = "vegetable";
        plot[0][2] = typeID;
    }

    public void grow() {
        /*
         * Overrides Plant's grow method. Grows the veggie type in the plot
         * up by one if it can.
         */
        for (int i = 0; i < 5; i++) {
            if (plot[i][2].equals('.')) {
                plot[i][2] = typeID;
                break;
            }
        }
    }

    public void harvest() {
        /*
         * Overrides Plant's harvest method. removes all veggie types in the
         * plot.
         */
        for (int i = 0; i < 5; i++) {
            plot[i][2] = '.';
        }
    }

}
