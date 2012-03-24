import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int nRounds = Integer.parseInt(bufferedReader.readLine());

        ArrayList<Banknote> notes;
        int n = 0, sum = 0;
        for (int i = 0; i < nRounds; i++) {

            String[] tok = bufferedReader.readLine().split("[ ]+");
            n = Integer.parseInt(tok[0]);
            sum = Integer.parseInt(tok[1]);

            notes = new ArrayList<Banknote>();

            for (int j = 0; j < n; j++) {
                int weight = Integer.parseInt(bufferedReader.readLine());
                if (weight <= sum) notes.add(new Banknote(weight));
            }

            if (trySum(notes, sum)) System.out.println("Yes");
            else System.out.println("No");
        }
    }

    static boolean trySum(ArrayList<Banknote> notes, int sum) {
        for (Banknote note : notes) {
            if (!note.isBlocked()) {
                if (note.getWeight() == sum) return true;

                if (note.getWeight() < sum) {
                    note.setBlocked(true);
                    boolean result = trySum(notes, sum - note.getWeight());
                    note.setBlocked(false);
                    if (result) return result;
                    else continue;
                }

            }
        }
        return false;
    }
}

class Banknote {
    private int weight;
    private boolean blocked;

    Banknote(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}


//Example
//
//Input:
//5
//3 3
//1
//1
//1
//5 11
//1
//2
//4
//8
//16
//5 23
//1
//2
//4
//8
//16
//5 13
//1
//5
//5
//10
//10
//20 132
//17
//6
//4
//998
//254
//137
//259
//153
//154
//3
//28
//19
//123
//542
//857
//23
//687
//35
//99
//999
//
//Output:
//Yes
//Yes
//Yes
//No
//Yes
