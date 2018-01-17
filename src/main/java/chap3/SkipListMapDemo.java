package chap3;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by hjy on 18-1-17.
 */
public class SkipListMapDemo {

    public static void main(String[] args) {

        Map<Integer,Integer> map = new ConcurrentSkipListMap<Integer,Integer>();
        for (int i = 0; i < 30; i++) {
            map.put(i,i);
        }

        for (Map.Entry<Integer,Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }



}
