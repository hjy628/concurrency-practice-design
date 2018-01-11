package chap1;

/**
 * Created by hjy on 18-1-11.
 * 0000000000000000000000000000000000000000000000000000000001101111
   1111111111111111111111111111111111111111111111111111110000011001
   0000000000000000000000000000000000000000000000000000000101001101
   1111111111111111111111111111111111111111111111111111111001000100
 *
 *
 */
public class LongTest {

    public static void main(String[] args) {


        Long a = +111L;
        Long b = -999L;
        Long c = +333L;
        Long d = -444L;


        System.out.println(Long.toBinaryString(a));
        System.out.println(Long.toBinaryString(b));
        System.out.println(Long.toBinaryString(c));
        System.out.println(Long.toBinaryString(d));

    }



}
