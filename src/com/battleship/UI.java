package com.battleship;

/**
 * Created by casvd on 30-3-2017.
 */
class UI {
    private static UI instance;

    static UI getInstance() {
        if(instance == null) instance = new UI();
        return instance;
    }

    private UI() {
    }

    void print(String string) {
        System.out.print(string);
    }

    void println(String string) {
        System.out.println(string);
    }

    final void clear()
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
