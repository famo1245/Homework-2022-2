public class BinarySearch implements SearchStrategy {
    @Override
    public boolean search(int[] objs, int num) {
        int left = 0;
        int right = objs.length - 1;
        while (left <= right) {
            int mid = (right - left)  / 2 + left;
            if (objs[mid] == num) return true;
            else if (objs[mid] > num) { // remove right half
                right = mid - 1;
            } else {  // remove left half
                left = mid + 1;
            }
        }
        return false;
    }
}
