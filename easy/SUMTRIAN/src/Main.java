import java.io.*;
import java.util.Arrays;

public class Main {
    static int[] arr;

    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out));

        int ntotal = Integer.parseInt(bufferedReader.readLine());
        int[] arr1;
        int[] arr2;

        for (int i = 0; i < ntotal; i++) {
            int nlocal = Integer.parseInt(bufferedReader.readLine());
            arr1 = new int[nlocal];

            for (int j = 1; j <= nlocal; j++) {
                arr2 = new int[j];

                String[] tok = bufferedReader.readLine().split("[ ]+");

                for (int k = 0; k < j; k++) {
                    arr2[k] = Integer.parseInt(tok[k]);

                    if (k == 0) {
                        arr2[k] += arr1[k];
                        continue;
                    } else if (k == j - 1) {
                        arr2[k] += arr1[k - 1];
                        continue;
                    } else
                        arr2[k] += Math.max(arr1[k], arr1[k - 1]);
                }
                arr1 = arr2;
            }
            Arrays.sort(arr1);

            printWriter.println(arr1[arr1.length - 1]);
        }

        printWriter.flush();
    }
}


//Example
//
//Input:
//2
//3
//1
//2 1
//1 2 3
//4
//1
//1 2
//4 1 2
//2 3 1 1
//
//Output:
//5
//9
