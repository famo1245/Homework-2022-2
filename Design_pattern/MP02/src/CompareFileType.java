public class CompareFileType implements Comparable{
    @Override
    public int compareTo(Object o1, Object o2) {
        FileInfo info1 = (FileInfo) o1;
        FileInfo info2 = (FileInfo) o2;
        String s1 = info1.getType();
        String s2 = info2.getType();

        return s1.compareTo(s2);
    }
}
