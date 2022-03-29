/*
 * This class is a subclass of Plant, thus, a lot of
 * Trees' attributes are inherited from Plant, including
 * toString, typeID, and plot.
 */
public class Trees extends Plant {
    public Trees(String type) {
        /*
         * Constructor method for Trees. Executes Plant's overload
         * constructor and adds a tree type at the bottom of the plot.
         */
        super(type);
        plot[4][2] = typeID;
        this.plant = "tree";
    }
    public void grow() {
        /*
         * Overrides Plant's grow method. Grows the tree type in the plot
         * down by one if it can.
         */
        for (int i = 4; i >= 0; i--) {
            if (plot[i][2].equals('.')) {
                plot[i][2] = typeID;
                break;
            }
        }
    }
    public void cut() {
        /*
         * Overrides Plant's cut method. removes all tree types in the plot.
         */
        for (int i = 4; i >= 0; i--) {
            plot[i][2] = '.';
        }
    }

}
