import java.util.Date;

public class CompareModifiedDate implements Comparable {
    @Override
    public int compareTo(Object o1, Object o2) {
        FileInfo info1 = (FileInfo) o1;
        FileInfo info2 = (FileInfo) o2;
        Date date1 = info1.getModifiedDate();
        Date date2 = info2.getModifiedDate();

        return date1.compareTo(date2);
    }
}
