package final2020;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String validData = "02-123-1234\n02-1234-1234\n055-123-1234\n055-1234-1234\n";
        final String invalidData = "058-1234-1234\n123-123-1234\n064-12-1234\n064-12345-1234\n02-123-12345\n02-123-123\n";
        final String quit = "quit";

        PhoneNumber phoneNumber = new PhoneNumber();
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(validData + invalidData + quit);  // 문자열에서 입력 받기

        System.out.println("전화 번호를 입력하세요: ");
        String s = sc.nextLine();
        while (!s.toUpperCase().equals("QUIT")) {
            System.out.printf("s = %s\n", s);
            boolean id = phoneNumber.verify(s);
            if (id) {
                System.out.printf("\"%s\"는 적합한 전화번호입니다.\n", s);
            }
            else {
                System.out.printf("\"%s\"는 부적합한 전화번호입니다.\n", s);
                for (int i = 0; i < phoneNumber.getErrorPos() + 1; i++) {
                    System.out.print(" ");
                }
                System.out.println("^");
            }
            System.out.println("\n전화 번호를 입력하세요: ");
            s = sc.nextLine();
        }
    }
}
