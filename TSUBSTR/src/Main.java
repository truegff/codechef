import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    private final static HashSet<String> strings = new HashSet<String>();
    private final static Index index = new Index();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(System.out);

        //reading dimensions
        String[] tokens = bufferedReader.readLine().split("[ ]");
        int N = Integer.parseInt(tokens[0]), Q = Integer.parseInt(tokens[1]);

        //initializing factory
        LetterFactory letterFactory = new LetterFactory();
        letterFactory.init(N);
//        letterFactory.getLetter(0).depth=1;

        //reading given tree
        //and building the index
        char[] letters = bufferedReader.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            letterFactory.getLetter(i).ch = letters[i];
        }
        for (int i = 0; i < N - 1; i++) {
            tokens = bufferedReader.readLine().split("[ ]");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            Letter child = letterFactory.getLetter(b - 1);
            Letter parent = letterFactory.getLetter(a - 1);
            child.depth = parent.depth + 1;

            //uniting duplicate children by the way
            if (!parent.children.containsKey(child.ch)) {
                parent.children.put(child.ch, child);
            } else {
                letterFactory.letters[b - 1] = parent.children.get(child.ch);
            }
        }

        //return value for K=1
        index.value = "";

        //generate all possible sub-strings
        for (Letter letter : letterFactory.letters)
            if (!letter.visited) {
                letter.visited = true;

                Index subIndex = index.getElementOrCreate(letter.ch);
                int subSize = subIndex.size;

                subStringGenerator(subIndex, letter, "");

                index.size += subIndex.size - subSize;
            }

        //printing total number of distinct substrings
        printWriter.println(index.size);

        //printing answers string by string
        for (int i = 0; i < Q; i++) {
            tokens = bufferedReader.readLine().split("[ ]");
            int K = Integer.parseInt(tokens[1]);
            if (K > index.size) {
                printWriter.println("-1");
                continue;
            }

            String stepAnswer = getString(index, tokens[0], K);
            printWriter.println(stepAnswer);
        }

        printWriter.flush();
    }

    private static void subStringGenerator(Index index, Letter root, String subString) {
        String merged = subString + root.asString();

        if (!strings.contains(merged)) {
            strings.add(merged);
            index.value = merged;
        }

        for (Letter successor : root.children.values()) {
            Index subIndex = index.getElementOrCreate(successor.ch);
            int subSize = subIndex.size;

            subStringGenerator(subIndex, successor, merged);

            index.size += subIndex.size - subSize;
        }
    }
    
    private static String getString(Index index, String order, int K) {

        if (K==1) return index.value;

        K--;
        
        String answer;
        Index subIndex = null;
        char ch;

        for (int i=0; i<order.length(); i++) {
            ch = order.charAt(i);
            subIndex = index.getElement(ch);
            if (subIndex == null) continue;
            if (subIndex.size < K) K -= subIndex.size;
            else break;
        }
        
        answer = getString(subIndex, order, K);
        
        return answer;
    } 
}

class Letter {
    char ch;
    boolean visited;
    int depth;
    final HashMap<Character, Letter> children = new HashMap<Character, Letter>();

    public String asString() {
        return String.valueOf(ch);
    }
}

class LetterFactory {
    Letter[] letters;

    public void init(int total) {
        letters = new Letter[total];
    }

    public Letter getLetter(int i) {
        if (letters[i] == null) letters[i] = new Letter();
        return letters[i];
    }
}


class Index {
    //private int depth;
    String value;
    int size = 1;
    private final HashMap<Character, Index> index = new HashMap<Character, Index>();

    private Index addElement(char key) {
        Index created = new Index();
        //created.depth = depth + 1;
        index.put(key, created);
        size++;
        return created;
    }

    public Index getElementOrCreate(char key) {
        if (!index.containsKey(key)) return addElement(key);
        return index.get(key);
    }
    
    public Index getElement(char key) {
        try {
            return index.get(key);
        } catch (Exception e) {
            return null;
        }
    }
}

//8 4
//abcbbaca
//1 2
//2 3
//1 4
//4 5
//4 6
//4 7
//1 8
//abcdefghijklmnopqrstuvwxyz 5
//abcdefghijklmnopqrstuvwxyz 1
//bcadefghijklmnopqrstuvwxyz 5
//abcdefghijklmnopqrstuvwxyz 100