package score;

import java.util.ArrayList;

public class StatisticsView implements Observer {
    private ScoreRecord scoreRecord;

    public StatisticsView(ScoreRecord scoreRecord) {
        this.scoreRecord = scoreRecord;
    }

    public void update() { // 점수의 변경을 통보받음
        ArrayList<Integer> record = scoreRecord.getScoreRecord();   //점수를 조회함
        displayScores(record);   //조회한 점수를 viewCount 만큼만 출려함
    }

    private void displayScores(ArrayList<Integer> record) {
        System.out.println("Average of List");
        int sum = 0;

        for (Integer i : record) {
            sum += i;
        }

        System.out.println("Average : " + (float)sum/record.size());
    }
}
