package com.battleship;

public class UI {

    /**
     * ANSI color codes
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";


    /**
     * Print to console
     *
     * @param obj to be printed
     */
    public static void print(Object obj) {
        System.out.print(obj);
    }

    /**
     * Print line to console
     *
     * @param obj to be printed
     */
    public static void println(Object obj) {
        System.out.println(obj);
    }


    /**
     * Print format to console
     *
     * @param obj  unformated object to be printed
     * @param vars values to be placed
     */
    public static void printf(Object obj, Object... vars) {
        System.out.printf((String) obj, vars);
    }

    /**
     * Clear prompt
     */
    public static void clear() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
            //this.println("Something went wrong clearing the console... ):");
        }
    }
}
