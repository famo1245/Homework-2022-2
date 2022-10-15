import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        final int MIN = 11;
        final int MAX = 110;
        final int NUMS = 50;
        IntArrays intArrays = new IntArrays(MIN, MAX, NUMS);

        SearchStrategy search;
        int[] randints = intArrays.getNumbers();
        search = new LinearSearch();
        System.out.println(search.search(randints, randints[NUMS - 10]));
        System.out.println(search.search(randints, randints[NUMS - 20]));
        System.out.println(search.search(randints, MAX + 10));
        System.out.println(search.search(randints, MIN - 10));
        search = new BinarySearch();
        System.out.println(search.search(randints, randints[NUMS - 10]));
        System.out.println(search.search(randints, randints[NUMS - 20]));
        System.out.println(search.search(randints, MAX + 10));
        System.out.println(search.search(randints, MIN - 10));
        /* 순서
        System.out.println(search.linearSearch(randints, randints[NUMS - 10]));
        System.out.println(search.linearSearch(randints, randints[NUMS - 20]));
        System.out.println(search.linearSearch(randints, MAX + 10));
        System.out.println(search.linearSearch(randints, MIN - 10));
        System.out.println(search.binarySearch(randints, randints[NUMS - 10]));
        System.out.println(search.binarySearch(randints, randints[NUMS - 20]));
        System.out.println(search.binarySearch(randints, MAX + 10));
        System.out.println(search.binarySearch(randints, MIN - 10));*/
    }
}
