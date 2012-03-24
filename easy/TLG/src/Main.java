import java.io.BufferedInputStream;
import java.util.Scanner;

public class Main {

    public static void main(String... args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));

        int nRounds = scanner.nextInt();
        int a = 0, b = 0;
        int aInc = 0, bInc = 0;
        int max = 0;
        int lastMaxBy = 0;
        for (int i = 0; i < nRounds; i++) {
            aInc = scanner.nextInt();
            bInc = scanner.nextInt();

            a += aInc;
            b += bInc;

            if ((a > b) && (max <= (a - b))) {
                max = (a - b);
                lastMaxBy = 1;
            }
            if ((a < b) && (max <= (b - a))) {
                max = (b - a);
                lastMaxBy = 2;
            }
        }
        System.out.println(lastMaxBy + " " + max);

    }
}

//Example
//
//Input:
//
//5
//140 82
//89 134
//90 110
//112 106
//88 90
//
//Output:
//
//1 58

