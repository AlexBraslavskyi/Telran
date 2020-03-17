import java.io.*;
import java.util.*;

/*
Чётные и нечётные циферки
*/

public class Test {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);


        for(int i = scan.nextInt(); i!=0; i=i/10){
            if (i%2 == 0){
                even++;}
            else{
                odd++;}
        }
        System.out.println("Even: " +even+ " Odd: "+odd);

    }

}
