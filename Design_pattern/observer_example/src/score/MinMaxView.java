package score;

import java.util.ArrayList;

public class MinMaxView implements Observer {
    private ScoreRecord scoreRecord;

    public MinMaxView(ScoreRecord scoreRecord) {
        this.scoreRecord = scoreRecord;
    }

    public void update() { // 점수의 변경을 통보받음
        ArrayList<Integer> record = scoreRecord.getScoreRecord();   //점수를 조회함
        displayScores(record);   //조회한 점수를 viewCount 만큼만 출려함
    }

    private void displayScores(ArrayList<Integer> record) {
        System.out.println("Min Max of List");
        int min = 1000;
        int max = 0;
        for(Integer i : record) {
            if (i < min) {
                min = i;
            }
            if (i > max) {
                max = i;
            }
        }
        System.out.println("Min: " + min + " Max: " + max);
    }
}
