package elevator;

import java.util.ArrayList;

public class ElevatorController implements Subject {
    private ArrayList<Observer> observers;
    private int curFloor = 1;

    public int getCurFloor() {
        return curFloor;
    }

    public ElevatorController() {
        observers = new ArrayList<>();
    }
    public void gotoFloor(int destination) {
        curFloor = destination;
        notifyObservers();
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
            o.update(getCurFloor());
        }
    }
}
