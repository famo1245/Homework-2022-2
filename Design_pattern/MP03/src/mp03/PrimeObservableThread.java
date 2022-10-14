package mp03;

import java.util.ArrayList;

public class PrimeObservableThread implements Runnable, Subject {
    ArrayList<Observer> observers = new ArrayList<>();
    private static final int SLEEPTIME = 500;

    private int primeNumber;
    private int numCount = 1;
    private boolean stopRunning = false;

    public int getPrimeNumber() { return primeNumber; }

    public void stopRunning() { stopRunning = true; }

    public void startRunning() {
        stopRunning = false;
        run();
    }

    private void generatePrimeNumber() {
        primeNumber = 2;
        System.out.println(primeNumber);
        while (stopRunning == false) {
            numCount += 2; // 2를 제외한 짝수는 소수가 될 수 없음. 따라서 홀수만 검사
            if (isPrimeNumber(numCount)) {
                primeNumber = numCount;
                System.out.println(primeNumber);
                notifyObservers();
            }
            try {
                Thread.sleep(SLEEPTIME); // 1초 쉼
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isPrimeNumber(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() { generatePrimeNumber(); }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void unregisterObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(""+primeNumber);
        }
    }
}
