package section1;

import java.util.Random;

public class NumberData {
    private static final int NUM = 10;
    private Random numGenerator;
    private int[] numbers;
    private SortAlg sortAlg;    
    
    // numbers 배열을 NUM 개수만큼 생성
    // 무작위로 0~99까지의 정수를 생성해서 numbers 배열을 채움
    // 0~99까지의 정수를 생성하는 것은 Random클래스의 nextInt(n) 사용
    // Random.nextInt(int n)함수는 0~n-1까지의 정수를 생성함
    // numGenerator 생성 코드는 수정하지 말 것
    public NumberData() {
        numGenerator = new Random(0); // 동일한 패턴을 얻기 위해 seed를 고정시킴
        numbers = new int[NUM];
        for (int i = 0; i < NUM; i++) {
            numbers[i] = numGenerator.nextInt(100);
        }
        sortAlg = null;
    }

    // numbers 배열에 있는 데이터를 오름차순으로 정렬
    // sortAlg변수가 null이면 아무것도 안함
    public void sort() {
        sortAlg.sort(numbers);
    }

    // numbers 배열에 있는 데이터를 화면에 출력
    public void printNumbers() {
        for(int i=0; i<numbers.length; i++) {
            System.out.print(numbers[i]);
            System.out.print(" ");
        }
        System.out.println();
    }

    // numbers 배열에 있는 데이터들을 무작위로 변경해서 정렬되지 않은 상태롤 만듦
    // 0~9까지의 인덱스 두 개를 뽑아서 서로 바꾸는 형태로 구현 (총 10번 교환)
    // 예) 랜덤 정수 인덱스가 1과 7이 나오면 numbers[1]과 numbers[7]을 서로 교환
    public void stirNumbers() {
        for(int i=0; i<NUM; i++) {
            int random_index = numGenerator.nextInt(NUM);

            int temp = numbers[random_index];
            numbers[random_index] = numbers[i];
            numbers[i] = temp;
        }
    }

    // numbers에 있는 숫자들을 다시 섞은 후에 출력
    // algName에 주어진 이름의 정렬 알고리즘을 생성
    // 생성된 알고리즘을 이용해서 다시 정렬 후 출력
    public void stirSortPrintData(String algName) {
        stirNumbers();
        sort();
        printNumbers();
    }

    public void setSortAlg(SortAlg sortAlg) {
        this.sortAlg = sortAlg;
    }
}
