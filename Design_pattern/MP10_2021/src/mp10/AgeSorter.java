package mp10;

public class AgeSorter extends BubbleSorter {
    public int compareTo(Person data1, Person data2) {
        return data1.getAge() - data2.getAge();
    }
}
