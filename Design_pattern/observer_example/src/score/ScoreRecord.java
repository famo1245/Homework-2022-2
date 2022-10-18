package score;

import java.util.ArrayList;

public class ScoreRecord implements Subject {
    private ArrayList<Integer> scores;
    private ArrayList<Observer> observers;

    public ScoreRecord() {
        observers = new ArrayList<>();
        scores = new ArrayList<>();
    }

    public void addScore(int score) {
        scores.add(score);
        notifyObservers();
    }

    public ArrayList<Integer> getScoreRecord() {
        return scores;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers) {
            o.update();
        }
    }
}
