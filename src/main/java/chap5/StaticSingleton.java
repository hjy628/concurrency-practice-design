package chap5;

/**
 * Created by hjy on 18-1-31.
 */
public class StaticSingleton {

    private StaticSingleton(){
        System.out.println("StaticSingleton is create");
    }

    private static class SingletonHolder{
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance(){
        return SingletonHolder.instance;
    }

}
