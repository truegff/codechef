import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));
        int req = scanner.nextInt();
        double bal = scanner.nextDouble();
        if ((bal - req - 0.5) < 0 || (req % 5 != 0)) {
            writer.printf("%.2f", bal);
        } else {
            writer.printf("%.2f", bal - req - 0.5);
        }
        writer.flush();
    }
}


//Example - Successful Transaction
//
//Input:
//30 120.00
//
//Output:
//89.50
//
//Example - Incorrect Withdrawal Amount (not multiple of 5)
//
//Input:
//42 120.00
//
//Output:
//120.00
//
//Example - Insufficient Funds
//
//Input:
//300 120.00
//
//Output:
//120.00
