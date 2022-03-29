/*
 * The Garden class is used to keep several Plant objects in
 * a printable 2D Array.
 */
public class Garden {
    /*
     * Declares the Garden object's attributes.
     * rows = The number of rows of Plant objects
     * in the Array.
     * cols = The number of columns of Plant objects
     * in the Array.
     * colMax = the Maximum number of columns
     * Plant objects there can be in plots.
     * plots = The 2D Array of Plant objects.
     */
    private int rows;
    private int cols;
    private int colMax;
    private Plant[][] plots;
    
    public Garden(int rows, int cols) {
        this.colMax = 16;
        this.rows = rows;

        // Determines whether to set
        // the number of columns to what
        // was passed in or to colMax.
        if (cols <= colMax) {
            this.cols = cols;
        } else {
            System.out.println("Too many plot columns.");
            System.exit(0);
        }
        this.plots = new Plant[this.rows][this.cols];

        // Fills up plots with empty Plant objects.
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                plots[row][col] = new Plant();
            }
        }
    }

    public Plant[][] getPlots() {
        /*
         * Returns the 2D array of Plant objects.
         */
        return this.plots;
    }
    
    public String toString() {
        /*
         * Loops through each row of each Plant object in each garden row
         * and concats each Character to the String to be returned by this
         * method.
         */
        String stringToReturn = "";

        for (int gRow = 0; gRow < this.rows; gRow++) {
            for (int pRow = 0; pRow < 5; pRow++) {
                stringToReturn += "\n";
                for (int gCol = 0; gCol < this.cols; gCol++) {
                    for (int pCol = 0; pCol < 5; pCol++) {
                        stringToReturn += (plots[gRow][gCol].getPlot()[pRow][pCol]);
                    }
                }
            }
        }
        return stringToReturn.substring(1);
    }
}
