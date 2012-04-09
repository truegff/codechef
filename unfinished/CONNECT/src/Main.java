import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static int bestprice = Integer.MAX_VALUE;
    public static int price = 0;

    public static int[] occ = new int[250];
    public static int[] occurences;
    public static BigInteger result = BigInteger.ZERO;

    public static int mstKey = 1;
    public static int cellKey = 1;

    public static int K;

    public static CellFactory cellFactory;

    public static boolean areWeDone() {
        if (result.bitCount() >= K) return true;
        return false;
    }

    public static void mst(Cell start, Cell parent) {
        if (Main.mstKey > start.mstKey) {
            start.parent = null;
            start.mst_price = 0;
            start.mstKey = Main.mstKey;
        }
        if (start.parent == null && !start.root) {
            if (parent == null)  {
                start.mst_price = 0;
                start.root = true;                
            }
            else {
                start.mst_price = parent.mst_price + parent.price;
                start.parent = parent;
            }

            if (start.l != null && start.l != start.parent) mst(start.l, start);
            if (start.r != null && start.r != start.parent) mst(start.r, start);
            if (start.u != null && start.u != start.parent) mst(start.u, start);
            if (start.d != null && start.d != start.parent) mst(start.d, start);
        } else {
            if (start.mst_price > parent.mst_price + parent.price) {
                start.parent = parent;
                start.mst_price = parent.mst_price + parent.price;
            }
        }

    }

//    public static void dfs(Cell start, int iteration) {
//        if (start.iteration < iteration) {
//            start.iteration = iteration;
//            conn_block.add(start);
//            start.blocked = true;
//            price += start.price;
//
//            if (start.index > 0) {
//                occurences[start.index]++;
//                result = result.setBit(start.index);
//            }
//
//            if (areWeDone()) {
//                bestprice = Math.min(bestprice, price);
//            } else if (price < bestprice) {
//                for (Cell cell : conn_block) {
//                    if (cell.l != null && !cell.l.blocked) dfs(cell.l, iteration);
//                    if (cell.r != null && !cell.r.blocked) dfs(cell.r, iteration);
//                    if (cell.u != null && !cell.u.blocked) dfs(cell.u, iteration);
//                    if (cell.d != null && !cell.d.blocked) dfs(cell.d, iteration);
//                }
//            }
//
//            if (start.index > 0) {
//                occurences[start.index]--;
//                if (occurences[start.index] == 0) result = result.clearBit(start.index);
//            }
//
//            price -= start.price;
//            start.blocked = false;
//            conn_block.remove(start);
//        }
//    }

    public static void recursion(int from, int to, String[] keys_arr) {
        for (int y = from; y < to; y++) {
            for (Cell c : cellFactory.byValue.get(keys_arr[y])) {
                c.add(Main.cellKey++);

                if (areWeDone()) {
                    Main.bestprice = Math.min(Main.bestprice, Main.price);
                } else {
                    int cMstPriceBackup = 0;
                    cMstPriceBackup = c.mst_price;
                    c.mst_price = c.parent.mst_price;

                    if (c.l != null && c.l != c.parent) mst(c.l, c);
                    if (c.r != null && c.r != c.parent) mst(c.r, c);
                    if (c.u != null && c.u != c.parent) mst(c.u, c);
                    if (c.d != null && c.d != c.parent) mst(c.d, c);
            
                    recursion(y + 1, to, keys_arr);
                    
                    c.mst_price = cMstPriceBackup;

                    if (c.l != null && c.l != c.parent) mst(c.l, c);
                    if (c.r != null && c.r != c.parent) mst(c.r, c);
                    if (c.u != null && c.u != c.parent) mst(c.u, c);
                    if (c.d != null && c.d != c.parent) mst(c.d, c);

                }

                c.remove(c.iteration);
            }
        }
    }

    public static void main(String... args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter printWriter = new PrintWriter(System.out);

        String[] tokens = bufferedReader.readLine().split("[ ]+");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);
        K = Integer.parseInt(tokens[2]);

        cellFactory = new CellFactory();

        cellFactory.init(N, M);
        occurences = new int[N * M + 5];

        for (int i = 0; i < N; i++) {
            tokens = bufferedReader.readLine().split("[ ]+");
            for (int j = 0; j < M; j++) {
                Cell current = cellFactory.getCell(i, j);

                if (M > 1) {
                    if (j == 0) current.r = cellFactory.getCell(i, j + 1);
                    if (j == M - 1) current.l = cellFactory.getCell(i, j - 1);
                    if (j > 0 && j < M - 1) {
                        current.l = cellFactory.getCell(i, j - 1);
                        current.r = cellFactory.getCell(i, j + 1);
                    }
                }

                if (N > 1) {
                    if (i == 0) current.d = cellFactory.getCell(i + 1, j);
                    if (i == N - 1) current.u = cellFactory.getCell(i - 1, j);
                    if (i > 0 && i < N - 1) {
                        current.u = cellFactory.getCell(i - 1, j);
                        current.d = cellFactory.getCell(i + 1, j);
                    }
                }

                current.index = Integer.parseInt(tokens[j]);
                if (current.index > 0) occ[current.index]++;

                if (!cellFactory.byValue.containsKey(tokens[j])) {
                    cellFactory.byValue.put(tokens[j], new ArrayList<Cell>());
                }
                cellFactory.byValue.get(tokens[j]).add(current);
            }
        }
        for (int i = 0; i < N; i++) {
            tokens = bufferedReader.readLine().split("[ ]+");
            for (int j = 0; j < M; j++) {
                cellFactory.getCell(i, j).price = Integer.parseInt(tokens[j]);
            }
        }

        if (cellFactory.byValue.containsKey("-1"))
            for (Cell forbidden : cellFactory.byValue.get("-1")) {
                if (forbidden.r != null) {
                    forbidden.r.l = null;
                    forbidden.r = null;
                }
                if (forbidden.l != null) {
                    forbidden.l.r = null;
                    forbidden.l = null;
                }
                if (forbidden.u != null) {
                    forbidden.u.d = null;
                    forbidden.u = null;
                }
                if (forbidden.d != null) {
                    forbidden.d.u = null;
                    forbidden.d = null;
                }
            }


        cellFactory.byValue.remove("-1");
        Set<String> keys = cellFactory.byValue.keySet();
        keys.remove("0");
        keys.remove("-1");
        String[] keys_arr = new String[keys.size()];
        keys.toArray(keys_arr);

        Arrays.sort(keys_arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int size1 = cellFactory.byValue.get(o1).size();
                int size2 = cellFactory.byValue.get(o2).size();

                return (new Integer(size1).compareTo(new Integer(size2)));
            }
        });


        for (int x = 0; x <= keys_arr.length - K; x++)
            for (Cell current : cellFactory.byValue.get(keys_arr[x]))
                if (current.index > 0) {
                    Main.mstKey++;
                    mst(current, null); // new Cell(1000, 1000));
                    current.add(cellKey++);
                    recursion(x + 1, keys_arr.length, keys_arr);
                    current.remove(current.iteration);
                    //dfs(current, iteration++);
                }


        if (bestprice == Integer.MAX_VALUE)
            printWriter.println(-1);
        else
            printWriter.println(bestprice);
        printWriter.flush();
    }
}

class Cell {
    boolean blocked;
    int index;
    int x;
    int y;
    int price;

    int mst_price;
    Cell parent;

    int iteration;
    public int mstKey;
    public boolean root;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int addingPrice() {
        if (this.blocked) return 0;
        if (parent != null) return parent.addingPrice() + price;
        return price;
    }

    public void add(int secret) {
        if (!blocked) {
//            System.out.println("Adding " + this.index + " (" + x + "," + y + ") " + addingPrice());
            blocked = true;
            this.iteration = secret;

            Main.price += this.price;

            if (this.index > 0) {
                Main.occurences[this.index]++;
                Main.result = Main.result.setBit(this.index);
            }

            if (parent != null)
                this.parent.add(secret);
        }
    }

    public void remove(int secret) {
        if (blocked && this.iteration == secret) {
//            System.out.println("Removing " + this.index + " (" + x + "," + y + ")");
            this.iteration = 0;

            Main.price -= this.price;

            if (this.index > 0) {
                Main.occurences[this.index]--;
                if (Main.occurences[this.index] == 0) Main.result = Main.result.clearBit(this.index);
            }

            blocked = false;
            if (parent != null)
                this.parent.remove(secret);
        }
    }

    Cell l;
    Cell r;
    Cell u;
    Cell d;
}

class CellFactory {
    Cell[][] cells;

    HashMap<String, ArrayList<Cell>> byValue = new HashMap<String, ArrayList<Cell>>();

    public void init(int N, int M) {
        cells = new Cell[N][M];
    }

    public Cell getCell(int x, int y) {
        if (cells[x][y] == null) cells[x][y] = new Cell(x, y);
        return cells[x][y];
    }
}


//Input:
//3 3 3
//0 0 1
//2 3 3
//-1 2 1
//3 1 5
//4 10 1
//9 3 4
//Output:
//8