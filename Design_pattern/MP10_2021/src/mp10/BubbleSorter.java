package mp10;

public abstract class BubbleSorter {
    public void sort(Person[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1; j++) {
                if (compareTo(data[j], data[j + 1]) > 0) {
                    Person temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    abstract int compareTo(Person data1, Person data2);
}
