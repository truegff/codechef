import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.*;

public class Main {
    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(System.out);

        int T = Integer.parseInt(bufferedReader.readLine());
        
        String[] tokens;
        BigInteger A,B,C,D,K;
        int[] inputdata;

        BigInteger div1, div2, div3, ans;
        
        
        for (int i = 0; i < T; i++) {
            tokens = bufferedReader.readLine().split("[ ]+");
            A = new BigInteger(tokens[0]);
            B = new BigInteger(tokens[1]);
            C = new BigInteger(tokens[2]);
            D = new BigInteger(tokens[3]);
            K = new BigInteger(tokens[4]);

            div1 = A.gcd(B);
            div2 = C.gcd(D);         
            div3 = div1.multiply(div2);
            div3 = div3.abs();
            div3 = div3.divide(div1.gcd(div2));

            ans = K.divide(div3).multiply(new BigInteger("2"));

            ans = BigInteger.ONE.add(ans);


            printWriter.println(ans);
        }

        printWriter.flush();
    }
}