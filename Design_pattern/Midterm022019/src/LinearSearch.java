public class LinearSearch implements SearchStrategy {
    @Override
    public boolean search(int[] objs, int num) {
        for (int n : objs) {
            if (num == n) return true;
        }
        return false;
    }
}
