package no1;

import java.util.Random;

public class MyRandom {
    // 출력 결과를 일치시키기 위해 시드 숫자를 0으로 고정시킴
    // 이렇게 해서 랜덤 정수 생성기는 일정한 패턴의 숫자들을 생성함
    private static final Random random = new Random(0);

    public static int randInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
