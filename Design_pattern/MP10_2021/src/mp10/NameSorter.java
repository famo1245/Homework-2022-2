package mp10;

public class NameSorter extends BubbleSorter {
    public int compareTo(Person data1, Person data2) {
        return data1.getName().compareTo(data2.getName());
    }
}
