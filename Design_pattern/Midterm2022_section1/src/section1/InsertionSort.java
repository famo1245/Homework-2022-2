package section1;

public class InsertionSort implements SortAlg {
    @Override
    public void sort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int key = data[i];
            int j;
            for (j = i - 1; j >= 0 && data[j] > key; j--) {
                data[j + 1] = data[j];
            }
            data[j + 1] = key;
        }
    }
}
