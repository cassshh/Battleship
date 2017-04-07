package com.battleship;

/**
 * Created by casvd on 30-3-2017.
 */
public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void print(Object obj) {
        System.out.print(obj);
    }

    public static void println(Object obj) {
        System.out.println(obj);
    }

    public static void printf(Object obj, Object... vars) {
        System.out.printf((String) obj, vars);
    }

    public static void clear()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
            //this.println("Something went wrong clearing the console... ):");
        }
    }
}
