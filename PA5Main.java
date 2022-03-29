
/*
 * Program: PA5-Garden
 * Author: Joseph Gauthier
 * Description: This program reads in a user's file and performs various
 * prompts read from within the file, using the custom classes
 * Garden, Plant, and Plant's various subclasses (Trees, Fruits, and Vegetables). 
 * These commands include plant, grow, harvest, pick, cut, and print. All of these 
 * relate to initializing Garden, a 2D Array of Plant objects, 
 * and placing/removing various plants in it.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PA5Main {
    /*
     * The main class that reads the commands from the user file
     * and manipulates the Garden, Plant, and Plant subclass objects
     * accordingly.
     */

    // Enums of all the allowed types of Plants assorted into three
    // categories, veggies, trees, and flowers.

    static enum veggies {
        GARLIC, ZUCCHINI, TOMATO, YAM, LETTUCE
    }
    static enum trees {
        OAK, WILLOW, BANANA, COCONUT, PINE;
    }
    static enum flowers {
        IRIS, LILY, ROSE, DAISY, TULIP, SUNFLOWER;
    }

    public static Scanner readFile(String fileName) {
        /*
         * Reads in and returns the user's file using the
         * file name the user entered on the command line
         * in main.
         */
        Scanner fileInput = null;
        try {
            fileInput = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileInput;
    }

    public static Garden initGarden(Scanner file) {
        /*
         * Initializes the only Garden object in the program with the
         * coordinates
         * stored in the user file's first two lines.
         */
        Garden garden = null;

        if (file.hasNextLine()) {
            String[] rows = file.nextLine().split(" ");
            String[] cols = file.nextLine().split(" ");
            garden = new Garden(Integer.parseInt(rows[1]),
                    Integer.parseInt(cols[1]));
        } else {
            // Exits program if an empty file is passed in.

            System.out.println("Empty File!");
            System.exit(0);
        }
        return garden;
    }

    public static String enumCheck(String type) {
        /*
         * Loops through each enum initialized above to find
         * which one the passed in plant type is contained in.
         * Returns the name of the enum the plant type is in, or
         * an empty string if it's in not in any of them.
         */
        for (veggies item : veggies.values())
            if (item.name().equals(type.toUpperCase())) {
                return "veggie";
            }
        for (trees item : trees.values())
            if (item.name().equals(type.toUpperCase())) {
                return "tree";
            }
        for (flowers item : flowers.values())
            if (item.name().equals(type.toUpperCase())) {
                return "flower";
            }
        return "";
    }

    public static Integer[] convertCoord(String coors) {
        /*
         * Takes in coordinates entered from the user's file and
         * Converts them to Integers. Appends these coordinates into an
         * array.
         */
        String tempCoor = coors.substring(1, coors.length() - 1);
        String[] coorArray = tempCoor.split(",");
        Integer[] finalCoors = new Integer[2];

        finalCoors[0] = Integer.parseInt(coorArray[0]);
        finalCoors[1] = Integer.parseInt(coorArray[1]);
        return finalCoors;
    }

    public static void plant(Integer[] coors, String type, Garden garden) {
        /*
         * Replaces the empty Plant object, in the Garden at the
         * passed in coordinates , with a new, filled, Plant object.
         * Uses enumCheck() to find what kind of new Plant object
         * is to replace the old one.
         */
        String plantType = enumCheck(type);

        // Checks if the coordinates entered are within the bounds
        // of the garden.
        if (coors[0] >= garden.getPlots().length
                || coors[1] >= garden.getPlots()[0].length) {
            System.out.println("Can't plant there.");
            return;
        }

        if (plantType.equals("veggie")) {
            garden.getPlots()[coors[0]][coors[1]] = new Vegetables(type);
        } else if (plantType.equals("tree")) {
            garden.getPlots()[coors[0]][coors[1]] = new Trees(type);
        } else if (plantType.equals("flower")) {
            garden.getPlots()[coors[0]][coors[1]] = new Flowers(type);
        } else {

            // If enumCheck returns the empty String, this
            // is printed and nothing is planted.
            System.out.println(type + " is not a valid plant type.");
        }
    }

    public static void grow(Garden garden, String[] currArray) {
        /*
         * The master grow method. Checks the line of the user's
         * file passed into it and depending on what it is comprised of,
         * calls other methods to grow certain plants in garden.
         */
        int numOfGrows = Integer.parseInt(currArray[1]);
        if (currArray.length < 3) {
            growAll(garden, numOfGrows);

        } else if (!(enumCheck(currArray[2]).equals(""))) {
            growByType(garden, numOfGrows, currArray[2]);

        } else if (currArray[2].contains(")")) {
            growAtCoor(currArray, garden, numOfGrows);

        } else {
            growByPlant(currArray[2], garden, numOfGrows);
        }
    }

    public static void growAll(Garden garden, int numOfGrows) {
        /*
         * Grows every Plant object in the Garden object. Called when
         * there is no specifier in main that tells the program to grow
         * any certain Plant.
         */
        int timeCount = 0;
        while (timeCount < numOfGrows) {
            for (int row = 0; row < garden.getPlots().length; row++) {
                for (int col = 0; col < garden.getPlots()[row].length; col++) {
                    garden.getPlots()[row][col].grow();
                }
            }
            timeCount += 1;
        }
    }

    public static void growByType(Garden garden, int numOfGrows, String type) {
        /*
         * Loops though garden and grows each Plant in it if that plant is the
         * same plant type as the type passed into the function.
         */
        int timeCount = 0;
        while (timeCount < numOfGrows) {
            for (int row = 0; row < garden.getPlots().length; row++) {
                for (int col = 0; col < garden.getPlots()[row].length; col++) {
                    if (garden.getPlots()[row][col].getType()
                            .equals(type.toLowerCase())) {
                        garden.getPlots()[row][col].grow();
                        timeCount += 1;
                    }
                }
            }
        }
    }

    public static void growAtCoor(String[] currArray, Garden garden,
            int numOfGrows) {
        /*
         * Grows the plant at the specified coordinate of garden the
         * desired number of times.
         */
        Integer[] coors = convertCoord(currArray[2]);

        if (coors[0] >= garden.getPlots().length
                || coors[1] >= garden.getPlots()[0].length) {
            System.out.println("Can't grow there.");

        } else {
            int growCount = 0;
            while (growCount < numOfGrows) {
                garden.getPlots()[coors[0]][coors[1]].grow();
                growCount += 1;
            }
        }
    }

    public static void growByPlant(String plant, Garden garden,
            int numOfGrows) {
        /*
         * Loops though garden and grows every kind of one subclass type of
         * Plant. EX) if flower is passed in, then all Flower objects in
         * garden will grow.
         */
        int timeCount = 0;
        while (timeCount < numOfGrows) {
            for (int row = 0; row < garden.getPlots().length; row++) {
                for (int col = 0; col < garden.getPlots()[row].length; col++) {
                    if (garden.getPlots()[row][col].getPlant()
                            .equals(plant.toLowerCase())) {
                        garden.getPlots()[row][col].grow();
                    }
                }
            }
            timeCount += 1;
        }
    }

    public static void pick(Garden garden, String[] currArray) {
        /*
         * The master pick method. Checks the line of the user's
         * file passed into it and depending on what it is comprised of,
         * calls other methods to pick certain Flowers in garden.
         */
        if (currArray.length < 2) {
            pickAll(garden);

        } else if (!(enumCheck(currArray[1]).equals(""))) {
            pickByType(garden, currArray[1]);

        } else if (currArray[1].contains(")")) {
            pickAtCoor(currArray, garden);
        }
    }

    public static void pickAll(Garden garden) {
        /*
         * Picks every flower object in garden. Called when
         * there is no specifier in main that tells the program to pick
         * any certain Flowers.
         */
        for (int row = 0; row < garden.getPlots().length; row++) {
            for (int col = 0; col < garden.getPlots()[row].length; col++) {
                if (garden.getPlots()[row][col].getPlant().equals("flower")) {
                    garden.getPlots()[row][col].pick();
                }
            }
        }
    }

    public static void pickAtCoor(String[] currArray, Garden garden) {
        /*
         * Picks the Flowers at the specified coordinates of garden.
         */
        Integer[] coors = convertCoord(currArray[1]);

        // Checks if the coordinates passed in are within the garden boundaries.
        if (coors[0] >= garden.getPlots().length
                || coors[1] >= garden.getPlots()[0].length) {
            System.out.println("Can't pick there.");

        } else {

            // Checks if the Plant at the coordinates is a Flower.
            if (garden.getPlots()[coors[0]][coors[1]].getPlant()
                    .equals("flower")) {
                garden.getPlots()[coors[0]][coors[1]].pick();

            } else {
                System.out.println("Can't pick there.");
            }
        }
    }

    public static void pickByType(Garden garden, String type) {
        /*
         * Loops though garden and picks each Flower in it if that
         * flower type is the same type as the type passed into the function.
         */
        for (int row = 0; row < garden.getPlots().length; row++) {
            for (int col = 0; col < garden.getPlots()[row].length; col++) {
                if (garden.getPlots()[row][col].getType()
                        .equals(type.toLowerCase())) {
                    garden.getPlots()[row][col].pick();
                }
            }
        }
    }

    public static void harvest(Garden garden, String[] currArray) {
        /*
         * The master harvest method. Checks the line of the user's
         * file passed into it and depending on what it is comprised of,
         * calls other methods to harvest certain Vegetables in garden.
         */
        if (currArray.length < 2) {
            harvestAll(garden);

        } else if (!(enumCheck(currArray[1]).equals(""))) {
            harvestByType(garden, currArray[1]);

        } else if (currArray[1].contains(")")) {
            harvestAtCoor(currArray, garden);
        }
    }

    public static void harvestAll(Garden garden) {
        /*
         * Harvests every Vegetable object in garden. Called when
         * there is no specifier in main that tells the program to harvest
         * any certain Vegetable.
         */
        for (int row = 0; row < garden.getPlots().length; row++) {
            for (int col = 0; col < garden.getPlots()[row].length; col++) {
                if (garden.getPlots()[row][col].getPlant()
                        .equals("vegetable")) {
                    garden.getPlots()[row][col].harvest();
                }
            }
        }
    }

    public static void harvestAtCoor(String[] currArray, Garden garden) {
        /*
         * Harvests the Vegetable at the specified coordinate of garden.
         */
        Integer[] coors = convertCoord(currArray[1]);

        // Checks if the coordinates passed in are within the garden boundaries.
        if (coors[0] >= garden.getPlots().length
                || coors[1] >= garden.getPlots()[0].length) {
            System.out.println("Can't harvest there.");

        } else {

            // Checks if the Plant at the coordinates is a Vegetable.
            if (garden.getPlots()[coors[0]][coors[1]].getPlant()
                    .equals("vegetable")) {
                garden.getPlots()[coors[0]][coors[1]].harvest();

            } else {
                System.out.println("Can't harvest there.");
            }
        }
    }

    public static void harvestByType(Garden garden, String type) {
        /*
         * Loops though garden and harvests each Vegetable in it if that
         * Vegetable
         * is the same type as the type passed into the function.
         */
        for (int row = 0; row < garden.getPlots().length; row++) {
            for (int col = 0; col < garden.getPlots()[row].length; col++) {
                if (garden.getPlots()[row][col].getType()
                        .equals(type.toLowerCase())) {
                    garden.getPlots()[row][col].harvest();
                }
            }
        }
    }

    public static void cut(Garden garden, String[] currArray) {
        /*
         * The master cut method. Checks the line of the user's
         * file passed into it and depending on what it is comprised of,
         * calls other methods to cut certain Trees in garden.
         */
        if (currArray.length < 2) {
            cutAll(garden);

        } else if (!(enumCheck(currArray[1]).equals(""))) {
            cutByType(garden, currArray[1]);

        } else if (currArray[1].contains(")")) {
            cutAtCoor(currArray, garden);
        }
    }

    public static void cutAll(Garden garden) {
        /*
         * Cuts every flower object in garden. Called when
         * there is no specifier in main that tells the program to cut
         * any certain Trees.
         */
        for (int row = 0; row < garden.getPlots().length; row++) {
            for (int col = 0; col < garden.getPlots()[row].length; col++) {
                if (garden.getPlots()[row][col].getPlant().equals("tree")) {
                    garden.getPlots()[row][col].cut();
                }
            }
        }
    }

    public static void cutAtCoor(String[] currArray, Garden garden) {
        /*
         * Cuts the Trees at the specified coordinates of garden.
         */
        Integer[] coors = convertCoord(currArray[1]);

        // Checks if the coordinates passed in are within the garden boundaries.
        if (coors[0] >= garden.getPlots().length
                || coors[1] >= garden.getPlots()[0].length) {
            System.out.println("Can't cut there.");

        } else {

            // Checks if the Plant at the coordinates is a Flower.
            if (garden.getPlots()[coors[0]][coors[1]].getPlant()
                    .equals("tree")) {
                garden.getPlots()[coors[0]][coors[1]].cut();

            } else {
                System.out.println("Can't cut there.");
            }
        }
    }

    public static void cutByType(Garden garden, String type) {
        /*
         * Loops though garden and cuts each Tree in it if that
         * tree type is the same type as the type passed into the function.
         */
        for (int row = 0; row < garden.getPlots().length; row++) {
            for (int col = 0; col < garden.getPlots()[row].length; col++) {
                if (garden.getPlots()[row][col].getType()
                        .equals(type.toLowerCase())) {
                    garden.getPlots()[row][col].cut();
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner file = readFile(args[0]);
        Garden garden = initGarden(file);
        while (file.hasNext()) {
            String currLine = file.nextLine();
            String[] currArray = currLine.split(" ");

            if (currArray[0].toLowerCase().equals("plant")) {
                Integer[] coors = convertCoord(currArray[1]);
                plant(coors, currArray[2], garden);

            } else if (currArray[0].toLowerCase().equals("grow")) {
                System.out.println(
                        "> GROW" + currLine.substring(currArray[0].length()));
                grow(garden, currArray);

            } else if (currArray[0].toLowerCase().equals("harvest")) {
                System.out.println("> HARVEST"
                        + currLine.substring(currArray[0].length()));
                harvest(garden, currArray);

            } else if (currArray[0].toLowerCase().equals("pick")) {
                System.out.println(
                        "> PICK" + currLine.substring(currArray[0].length()));
                pick(garden, currArray);

            } else if (currArray[0].toLowerCase().equals("cut")) {
                System.out.println(
                        "> CUT" + currLine.substring(currArray[0].length()));
                cut(garden, currArray);
            } else if (currArray[0].toLowerCase().equals("print")) {
                System.out.println(
                        "> PRINT" + currLine.substring(currArray[0].length()));
                System.out.println(garden.toString());
            }
        }
        file.close();
    }
}
