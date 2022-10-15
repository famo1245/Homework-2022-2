import java.util.Arrays;

public class IntArrays {
    RandomNumberGenerator generator;
    int[] numbers;

    /** public IntArrays(int min, int max, int nums)
     * <p> 무작위 숫자 발생기를 이용해서 min~max 범위 내의 숫자를 nums개 만큼 생성한 후에 배열에 저장함
     * </p>
     * @param min 무작위 정수 발생시 최소값 지정
     * @param max 무작위 정수 발생시 최대값 지정
     * @param nums 생성할 정수 개수
     */
    public IntArrays(int min, int max, int nums) {
        generator = new RandomNumberGenerator();
        numbers = generator.generateNumbers(min, max, nums);
        Arrays.sort(numbers);
    }

    /** public int[] getNumbers()
     * <p> 숫자 발생기를 이용해서 만들어낸 배열 참조를 반환하는 접근자
     * </p>
     * @return 생성한 무작위 숫자를 담고 있는 배열 반환
     */
    public int[] getNumbers() { return numbers; }
}
