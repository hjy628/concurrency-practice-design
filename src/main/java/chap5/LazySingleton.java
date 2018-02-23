package chap5;

/**
 * Created by hjy on 18-1-31.
 */
public class LazySingleton {

    private LazySingleton(){
        System.out.println("LazySingleton is create");
    }

    private static LazySingleton instance = null;

    public static synchronized LazySingleton getInstance(){
        if (instance==null)
            instance = new LazySingleton();
        return instance;
    }


}
