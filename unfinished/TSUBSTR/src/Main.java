import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Main {
//    private final static Letter letters[] = new Letter[250000];
    private final static Index index = new Index(null);

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(System.out);

        //reading dimensions
        StringTokenizer stk = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(stk.nextToken()), Q = Integer.parseInt(stk.nextToken());

        //reading given tree
        //and building the index
        char alphabet[] = new char[N];
        Letter letters[] = new Letter[N];
        bufferedReader.read(alphabet, 0, N);
        for (int i = 0; i < N; i++) {
            letters[i] = new Letter(alphabet[i]);
        }
        bufferedReader.readLine();

        for (int i=0; i<N-1; i++) {
            stk = new StringTokenizer(bufferedReader.readLine());
            Letter parent = letters[Integer.parseInt(stk.nextToken())-1];
            int j = Integer.parseInt(stk.nextToken());
            Letter child = letters[j-1];

            //uniting duplicate children by the way
            if (parent.children[child.ch-97]==null) {
                parent.children[child.ch-97] = child;
            } else {
                letters[j-1] = parent.children[child.ch-97];
            }
        }

        //return value for K=1
        index.value = ' ';

        //generate all possible sub-strings
        for (int i=0; i<N-1; i++) {
            Letter letter = letters[i];
            if (!letter.visited) {
                letter.visited = true;

                Index subIndex = index.getElementOrCreate(letter.ch);
                long subSize = subIndex.size;

                subStringGenerator(subIndex, letter);

                index.size += subIndex.size - subSize;
            }
        }

        //printing total number of distinct substrings
        printWriter.println(index.size);

        //printing answers string by string
        char [] order = new char[26];
        for (int i = 0; i < Q; i++) {
            bufferedReader.read(order, 0, 26);

            int K = Integer.parseInt(bufferedReader.readLine().substring(1));
            if (K > index.size) {
                printWriter.println("-1");
                continue;
            }

            String stepAnswer = getString(index, order, K);
            printWriter.println(stepAnswer);
        }

        printWriter.flush();
    }

    private static void subStringGenerator(Index index, Letter root) {

        index.value = root.ch;

        for (Letter successor : root.children) {
            if (successor!=null) {
                Index subIndex = index.getElementOrCreate(successor.ch);
                long subSize = subIndex.size;

                subStringGenerator(subIndex, successor);

                index.size += subIndex.size - subSize;
            }
        }
    }
    
    private static String getString(Index index, char[] order, int K) {
            if (K==1) {
                StringBuilder sb = new StringBuilder();
                while (index.parent!=null) {
                    sb.append(index.value);
                    index = index.parent;
                }
                return sb.reverse().toString();
            }

        K--;
        
        Index subIndex = null;

        for (char ch : order) {
            subIndex = index.index[ch-97];
            if (subIndex == null) continue;
            if (subIndex.size < K) K -= subIndex.size;
            else break;
        }

        return getString(subIndex, order, K);
    } 
}

class Letter {
    char ch;
    boolean visited;
    Letter children[] = new Letter[26];
    Letter(char ch) {
        this.ch = ch;
    }
}

class Index {
    char value;
    long size = 1;
    public final Index index[] = new Index[26];
    public Index parent;

    Index(Index parent) {
        this.parent = parent;
    }

    private Index addElement(char key) {
        size++;
        return index[key-97]=new Index(this);
    }

    public Index getElementOrCreate(char key) {
        if (index[key-97]==null) return addElement(key);
        return index[key-97];
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