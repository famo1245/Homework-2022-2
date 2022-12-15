import java.util.Scanner;

// 입력된 문자 ch가 0~9까지의 숫자를 나타내는 문자인 경우, ch - '0'으로 0~9까지의 정수로 변환 가능
public class Main {
    public static void main(String[] args) {
        char ch;
        Calculator calc = new Calculator();
        Scanner sc = new Scanner(System.in);
        while(true) {    // 종료 문자가 아니면 반복
            System.out.print("입력: ");
            String input = sc.next();
            for(int i=0; i < input.length(); i++) {
                ch = input.charAt(i); // 키보드에서 한 글자 입력 받기
                if(ch == 'q' || ch == 'Q') {
                    System.out.println("계산기를 종료합니다...");
                    return;
                }
                calc.execute(ch);
            }
        }
    }
}
