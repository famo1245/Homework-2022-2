package hw;

import java.util.*;

public class Menu {
    private static String menu = "========================================\n" +
            "(0) 종료\n" +
            "(1) 릴레이션 생성 및 데이터 추가\n" +
            "(2) 제목을 이용한 검색\n" +
            "(3) 관객수를 이용한 검색\n" +
            "(4) 개봉일을 이용한 검색\n" +
            "========================================\n" +
            "원하는 번호를 입력 하시오 >> ";

    private MovieDAO movieDAO;
    private Scanner sc;

    public Menu() {
        System.out.println("Connecting to database");
        movieDAO = new MovieDAO();
        sc = new Scanner(System.in);
    }

    public void run() {
        while(true){
            System.out.print(menu);
            int select = sc.nextInt();
            switch(select) {
                case 0:
                    System.out.println("시스템을 종료합니다");
                    sc.close();
                    movieDAO.terminate();
                    return;

                case 1:
                    selectOne();
                    break;

                case 2:
                    selectTwo();
                    break;

                case 3:
                    selectThree();
                    break;

                case 4:
                    selectFour();
                    break;

                default:
                    System.out.println("잘못된 번호를 입력하셨습니다 다시 입력해주세요");
            }
            System.out.println();
        }
    }

    private void selectOne() {
        System.out.println("파일의 내용을 데이터베이스에 추가합니다...");
        if(movieDAO.insertData()) {
            // check insert did well
            for (MovieInfo m : movieDAO.findAll())
                System.out.println(m);
            System.out.println("상기 내용 입력 완료..");
        } else
            System.out.println("이미 파일의 내용이 추가되었습니다");
    }

    private void selectTwo() {
        System.out.print("검색할 영화의 제목을 입력해주세요 >> ");
        String title = sc.next();
        System.out.println("검색 결과..");
        for(MovieInfo m : movieDAO.findByTitle(title))
            System.out.println(m);
    }

    private void selectThree() {
        System.out.print("검색할 관객 수를 입력해주세요 >> ");
        int totalNum = sc.nextInt();
        System.out.println("검색 결과..");
        for(MovieInfo m : movieDAO.findByTotalNum(totalNum))
            System.out.println(m);
    }

    private void selectFour() {
        System.out.print("두 날짜를 입력해주세요 >> ");
        String input = sc.next();
        String date1 = input.split(",")[0];
        String date2 = sc.next();
        System.out.println("검색 결과..");
        for(MovieInfo m : movieDAO.findByReleaseDate(date1, date2))
            System.out.println(m);
    }
}
