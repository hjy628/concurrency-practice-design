package chap6;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjy on 18-2-23.
 */
public class BadMethodRef {

    public static void main(String[] args) {
        List<Double> numbers = new ArrayList<Double>();
        for (int i = 0; i < 10; i++) {
            numbers.add(Double.valueOf(i));
        }

     //   numbers.stream().map(Double::toString).forEach(System.out::println);

    }

}
