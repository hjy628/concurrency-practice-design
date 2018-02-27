package chap6;

import java.util.Arrays;
import java.util.function.IntConsumer;

/**
 * Created by hjy on 18-2-23.
 */
public class Test2 {

    static int[] arr = {1,3,4,5,6,7,8,9,10};

    public static void main(String[] args) {
/*
        Arrays.stream(arr).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });*/

/*
        Arrays.stream(arr).forEach((final int x)->{
            System.out.println(x);
        });
        */
/*
        Arrays.stream(arr).forEach((x)->{
            System.out.println(x);
        });*/
/*
        Arrays.stream(arr).forEach((x) -> System.out.println(x));
        */
        Arrays.stream(arr).forEach( System.out::println);


        IntConsumer outprintln = System.out::println;
        IntConsumer errprintln = System.err::println;
        Arrays.stream(arr).forEach(outprintln.andThen(errprintln));

    }

}
