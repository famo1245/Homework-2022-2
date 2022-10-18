package score;

public class Client {
    public static void main(String[] args) {
        ScoreRecord scoreRecord = new ScoreRecord();

        // 3개까지의 점수만 출력함
        DataSheetView dataSheetView3 = new DataSheetView(scoreRecord, 3);

        scoreRecord.registerObserver(dataSheetView3);
        MinMaxView minMaxView = new MinMaxView(scoreRecord);
        scoreRecord.registerObserver(minMaxView);

        for(int i=1; i <= 5; i++) {
            int score = i * 10;
            System.out.println("Adding " + score);

            // 10 20 30 40 50을 추가함, 추가할 때마다 최대 3개의 점수만 출력함
            scoreRecord.addScore(score);
        }

        scoreRecord.unregisterObserver(dataSheetView3);
        StatisticsView statisticsView = new StatisticsView(scoreRecord);
        scoreRecord.registerObserver(statisticsView);

        // 이제 minMaxView, StatisticsView 가 observer 임
        for(int i=1; i <= 5; i++) {
            int score = (i+3) * 10;
            System.out.println("Adding " + score);

            // 10 20 30 40 50을 추가함, 추가할 때마다 최대 3개의 점수만 출력함
            scoreRecord.addScore(score);
        }
    }
}
