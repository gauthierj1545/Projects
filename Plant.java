/*
 * The Plant class is used to generate Plant objects of varying types.
 * These types include Trees, Flowers, and Vegetables objects. The methods
 * found in this class are inherited by each varying type of Plant, otherwise
 * known as Plant's subclasses.
 */
public class Plant {
    /*
     * Declares the Plant object's attributes. These are inherited by
     * Plant's subclasses.
     * typeID is the Character object to be used to plant the Plant the object.
     * plot is a 5 by 5 space in the Garden class that this plant
     * resides in.
     */
    protected String plant;
    protected String type;
    protected Character typeID;
    protected Character[][] plot;

    public Plant() {
        /*
         * Constructor method for Plant. As this constructor is called
         * to generate an empty (Plant-less) plot, typeID is initialized
         * as null and plot is initialized as a new Array then filled with
         * only '.' Characters.
         */
        this.plant = "empty";
        this.type = "empty";
        this.typeID = null;
        this.plot = new Character[5][5];

        // Fills up plot with '.' Character objects.
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                plot[i][j] = '.';
            }
        }
    }
    public Plant(String type) {
        /*
         * Overload constructor method. Initializes all Plant attributes
         * just as the default constructor, only typeID is actually initialized
         * with a type value.
         * NOTE: The Plant type being attempted to be initialized will override
         * this method with its own constructor. There, typeID will be tested
         * to see if it actually belongs in the Plant type called. If it is,
         * then that Plant is planted into the plot according to its type.
         */
        this.type = type;
        this.typeID = type.toLowerCase().charAt(0);

        // Fills up plot with '.' Character objects.
        this.plot = new Character[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                plot[i][j] = '.';
            }
        }
    }

    public String getType() {
        /*
         * Returns the plant type of a specific Plant subclass.
         */
        return this.type;
    }

    public String getPlant() {
        return this.plant;
    }

    /*
     * Returns the Character Array used for Plant's plot attribute.
     */
    public Character[][] getPlot() {
        return this.plot;
    }

    public void grow() {
        /*
         * Abstract method used to be overwritten by Plant subclasses, since
         * each
         * Plant type has a unique way of growing.
         */
    }
    public void harvest() {
        /*
         * Abstract method used to be overwritten by the Plant's Vegetables
         * subclass.
         */
    }

    public void cut() {
        /*
         * Abstract method used to be overwritten by the Plant's Trees
         * subclass.
         */
    }

    public void pick() {
        /*
         * Abstract method used to be overwritten by the Plant's Flowers
         * subclass.
         */
    }

    public String toString() {
        /*
         * Returns the String representation of the Plant's
         * plot attribute.
         */
        String stringToReturn = "";
        for (int i = 0; i < 5; i++) {
            for (Character item : plot[i]) {
                stringToReturn += item;
            }
            stringToReturn += "\n";
        }
        return stringToReturn.substring(0, stringToReturn.length() - 1);
    }
}
