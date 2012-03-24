import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));
        int req = scanner.nextInt();
        for (int i = 0; i < req; i++) {
            int num = scanner.nextInt();
            int answer = 0;
            double five = 1;
            while ((five *= 5) <= num)
                answer += new Double(Math.floor(num / five)).intValue();
            writer.println(answer);
        }

        writer.flush();
    }
}

//Example
//
//Sample Input:
//
//6
//3
//60
//100
//1024
//23456
//8735373
//
//Sample Output:
//
//0
//14
//24
//253
//5861
//2183837