package chap5;

/**
 * Created by hjy on 18-1-31.
 */
public class Singleton {
/*
    private Singleton() {
        System.out.println("Singleton is create");
    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }*/

    public static int STATUS = 1;

    private Singleton() {
        System.out.println("Singleton is create");
    }
    private static Singleton instance = new Singleton();
    public static Singleton getInstance(){
        return instance;
    }


}
