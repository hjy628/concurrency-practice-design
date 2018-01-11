package chap2;

/**
 * Created by hjy on 18-1-11.
 * V1=1073741827
 V2=1431655768
 ave=-894784850

 int 溢出
 */
public class TTT1 {

    public static void main(String[] args) {

        int v1 = 1073741827;
        int v2 = 1431655768;

        System.out.println("V1="+v1);
        System.out.println("V2="+v2);

        int ave = (v1+v2)/2;
        System.out.println("ave="+ave);


    }


}
