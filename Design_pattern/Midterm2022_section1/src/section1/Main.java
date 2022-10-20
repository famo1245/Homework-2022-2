package section1;

// 수정하지 말 것
public class Main {
    public static void main(String[] args) {
        NumberData nd = new NumberDataSortingFactory();
        String[] algs = { "Bubble", "Insertion", "Selection" };
        for (int i = 0; i < algs.length; i++) {
            System.out.printf("Using %s algorithm\n", algs[i]);
            nd.stirSortPrintData(algs[i]);
        }
    }
}
