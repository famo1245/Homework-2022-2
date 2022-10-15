import java.util.Random;
import java.util.Arrays;

public class RandomNumberGenerator {
    Random random = new Random();

    RandomNumberGenerator() {
    }

    public int randint(int min, int max)
    {
        int n = (int) Math.floor(random.nextDouble() * (max - min + 1) + min);
        return (n > max || n < min) ? max : n;
    }

    public int[] generateNumbers(int min, int max, int nums) {
        final int SIZE = 2;
        int[] randints = new int[nums];
        boolean[] numbers = new boolean[nums * SIZE];  // allocate big enough spaces
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = false;
        }
        int n = randint(min, max);
        for (int i = 0; i < nums; i++) {
            while (numbers[n - min]) {
                n = randint(min, max);
            }
            numbers[n - min] = true;
            randints[i] = n;
        }
        return randints;
    }

//    public static void main(String[] args) {
//        final int MIN = 11;
//        final int MAX = 110;
//        final int NUMS = 50;
//        final int SIZE = 100;
//        RandomNumberGenerator generator = new RandomNumberGenerator();
//        int[] randints = generator.generateNumbers(MIN, MAX, NUMS);
//        Arrays.sort(randints);
//        for (int i = 0; i < randints.length; i++) {
//            System.out.println(randints[i]);
//        }
//    }
}
