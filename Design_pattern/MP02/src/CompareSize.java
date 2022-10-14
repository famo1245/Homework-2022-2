public class CompareSize implements Comparable {
    @Override
    public int compareTo(Object o1, Object o2) {
        FileInfo info1 = (FileInfo) o1;
        FileInfo info2 = (FileInfo) o2;
        int num1 = info1.getSize();
        int num2 = info2.getSize();

        if(num1 > num2) {
            return 1;
        } else if (num1 == num2) {
            return 0;
        } else {
            return -1;
        }
    }
}
