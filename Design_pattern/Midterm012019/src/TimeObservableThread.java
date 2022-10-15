import java.time.LocalTime;
import java.util.ArrayList;

public class TimeObservableThread implements Runnable, Subject {
    ArrayList<Observer> observers = new ArrayList<>();
    private static final int SLEEPTIME = 1000;
    private LocalTime time;
    private String localTime;
    private boolean stopRunning = false;
    public void stopRunning() { stopRunning = true; }

    public void startRunning() {
        stopRunning = false;
        run();
    }

    private void generateTime() {
        while (!stopRunning) {
            time = LocalTime.now();
            localTime = "" + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond();
            System.out.println(localTime);
            notifyObservers();
            try {
                Thread.sleep(SLEEPTIME); // 1초 쉼
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() { generateTime(); }



    public void notifyObservers() {
        for(Observer o : observers) {
            o.update(localTime);
        }
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        observers.remove(o);
    }
}
