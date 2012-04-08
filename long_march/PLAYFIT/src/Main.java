import java.io.*;

public class Main {
    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        PrintWriter printWriter = new PrintWriter(System.out);

        int N, T = Integer.parseInt(bufferedReader.readLine());
        int min, max, gl_max, current_reading;

        while (T>0) {
            T--;
            N = Integer.parseInt(bufferedReader.readLine());

            gl_max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            String line = bufferedReader.readLine()+" ";

            for (String str : line.split("[ ]")) {
                current_reading = Integer.parseInt(str);

                if (current_reading <= min) {
                    max = min = current_reading;
                } else 
                if (current_reading > max) {
                    max = current_reading;
                    gl_max = Math.max(gl_max, max-min);
                }
            }

            if (gl_max > 0) printWriter.println(gl_max);
            else printWriter.println("UNFIT");
        }

        printWriter.flush();
    }
}