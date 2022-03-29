
/*
 * This class is a subclass of Plant, thus, a lot of
 * Flowers' attributes are inherited from Plant, including
 * toString, typeID, and plot.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flowers extends Plant {
    public Flowers(String type) {
        /*
         * Constructor method for Flowers. Executes Plant's overload
         * constructor and adds a flower type at the center of the plot.
         */
        super(type);
        plot[2][2] = this.typeID;
        this.plant = "flower";
    }

    public Map<Integer, List<Integer>> getFlowers() {
        /*
         * Returns a map of coordinates of where the flower type has
         * bloomed in the plot.
         */
        Map<Integer, List<Integer>> flowerLocals = new HashMap<Integer, List<Integer>>();
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (!(this.plot[row][col].equals('.'))) {
                    if (!(flowerLocals.containsKey(row))) {
                        flowerLocals.put(row, new ArrayList<Integer>());
                    }
                    flowerLocals.get(row).add(col);
                }
            }
        }
        return flowerLocals;
    }

    public void grow() {
        /*
         * Overrides Plant's grow method. Using the getFlowers method,
         * checks where each flower type is in the plot and Grows each
         * in all four directions by one if it can.
         */
        Map<Integer, List<Integer>> flowerLocals = getFlowers();

        for (int row = 0; row < 5; row++) {
            if (flowerLocals.containsKey(row)) {
                for (int col = 0; col < 5; col++) {
                    List<Integer> currRow = flowerLocals.get(row);
                    if (currRow.contains(col)) {
                        if (row > 0 && row < 4) {
                            this.plot[row - 1][col] = this.typeID;
                            this.plot[row + 1][col] = this.typeID;
                        }
                        if (col > 0 && col < 4) {
                            this.plot[row][col - 1] = this.typeID;
                            this.plot[row][col + 1] = this.typeID;
                        }
                    }
                }
            }
        }
    }

    public void pick() {
        /*
         * Overrides Plant's pick method. removes all flower types in the plot.
         */
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                plot[row][col] = '.';
            }
        }
    }
}
