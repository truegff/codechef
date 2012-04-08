import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Main {
    //public static BigInteger[][] arr = new BigInteger[1003][1003];
    public static long[][] arrLong = new long[1003][1003];

    //public static BigInteger[] sum = new BigInteger[1003];

    //public static final BigInteger modulus = new BigInteger("1000000007");
    public static final long modulusLong = 1000000007L;
    private static long[] sumLong = new long[1003];

    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(System.out);

        int T = Integer.parseInt(bufferedReader.readLine());

        //arr[1][1] = BigInteger.ONE;
        arrLong[1][1] = 1;

        //sum[1] = BigInteger.ZERO; //new BigInteger("1");
        sumLong[1] = 0;

        int prevReq = 1;
        //fill(prevReq, 4);
        fillLong(prevReq, 4);
        prevReq = 4;

//        BigInteger sum;
        
        for (int i = 0; i < T; i++) {
            int req = Integer.parseInt(bufferedReader.readLine());
            if (req > prevReq-1) {
                //fill(prevReq, req+1);
                fillLong(prevReq, req+1);
                prevReq = req+1;
            }

            //printWriter.println(sum[req].mod(modulus));
            printWriter.println(sumLong[req]);
        }

        printWriter.flush();
    }

//    public static void fill(int from, int to) {
//        for (int i = from; i <= to; i++) {
//            sum[i] = BigInteger.ZERO;
//            for (int j = 1; j < i+2; j++) arr[i+1][j] = BigInteger.ZERO;
//            for (int k = 1; k <= i; k++) {
//                if (k != i && k != 1) {
//                    arr[i + 1][k] = arr[i + 1][k].add(arr[i][k].multiply(new BigInteger(Integer.toString(k))));
//                    arr[i + 1][k + 1] = arr[i + 1][k + 1].add(arr[i][k]);
//                } else if (k == 1) {
//                    arr[i + 1][k] = BigInteger.ONE;
//                    arr[i + 1][k + 1] = arr[i + 1][k + 1].add(BigInteger.ONE);
//                } else if (k == i) {
//                    arr[i + 1][k] = arr[i + 1][k].add(new BigInteger(Integer.toString(k)));
//                    arr[i + 1][k + 1] = BigInteger.ONE;
//                }
//                sum[i] = sum[i].add(arr[i][k]);
//            }
//        }
//    }

    public static void fillLong(int from, int to) {
        for (int i = from; i <= to; i++) {
            sumLong[i] = 0;
            for (int j = 1; j < i+2; j++) arrLong[i+1][j] = 0;
            for (int k = 1; k <= i; k++) {
                if (k != i && k != 1) {
                    arrLong[i + 1][k] = (arrLong[i + 1][k]+arrLong[i][k]*k)%modulusLong ;
                    arrLong[i + 1][k + 1] = (arrLong[i + 1][k + 1] + arrLong[i][k])%modulusLong;
                } else if (k == 1) {
                    arrLong[i + 1][k] = 1;
                    arrLong[i + 1][k + 1] = (arrLong[i + 1][k + 1] + 1)%modulusLong;
                } else if (k == i) {
                    arrLong[i + 1][k] = (arrLong[i + 1][k] + k)%modulusLong;
                    arrLong[i + 1][k + 1] = 1;
                }
                sumLong[i] = (sumLong[i] + arrLong[i][k])%modulusLong;
            }
        }
    }

}
