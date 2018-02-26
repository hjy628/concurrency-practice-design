package chap7;

import java.util.List;

/**
 * Created by hjy on 18-2-26.
 */
public class Fitness {

    public static double fitness(List<Double> x){
        double sum = 0;
        for (int i = 1; i < x.size(); i++) {
            sum+=Math.sqrt(x.get(i));
        }

        return sum;
    }


}
