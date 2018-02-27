package chap6;

import java.util.stream.IntStream;

/**
 * Created by hjy on 18-2-23.
 */
public class PrimeUtil {

    public static boolean isPrime(int number){
        int tmp = number;
        if (tmp<2){
            return false;
        }

        for (int i = 2; Math.sqrt(tmp) >= i  ; i++) {
            if (tmp % i==0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(IntStream.range(1,1000000).filter(PrimeUtil::isPrime).count());
        System.out.println(IntStream.range(1,1000000).parallel().filter(PrimeUtil::isPrime).count());

    }

}
