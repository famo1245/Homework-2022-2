package score;

import java.util.ArrayList;

public class DataSheetView implements Observer {
    private ScoreRecord scoreRecord;
    private int viewCount;

    public DataSheetView(ScoreRecord scoreRecord, int viewCount) {
        this.scoreRecord = scoreRecord;
        this.viewCount = viewCount;
    }

    public void update() { // 점수의 변경을 통보받음
        ArrayList<Integer> record = scoreRecord.getScoreRecord();   //점수를 조회함
        displayScores(record, viewCount);   //조회한 점수를 viewCount 만큼만 출려함
    }

    private void displayScores(ArrayList<Integer> record, int viewCount) {
        System.out.print("List of " + viewCount + " entries: ");

        for (int i = 0; i < viewCount && i < record.size(); i++) {
            System.out.print(record.get(i) + " ");
        }
        System.out.println();
    }
}
