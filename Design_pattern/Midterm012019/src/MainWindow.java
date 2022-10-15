import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends FrameWindow {
    private static final String MAIN_TITLE = "Main Window";
    private static final String LABEL_WINDOW_TITLE = "Label Window";
    private static final int X = 250;
    private static final int Y = 100;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 200;
    private static final int GAP = 50;

    private TimeObservableThread timeThread;
    private LabelWindow labelWindow1;
    private LabelWindow labelWindow2;

    public MainWindow(String title) {
        super(title, X, Y, WIDTH, HEIGHT);
        labelWindow1 = new LabelWindow(LABEL_WINDOW_TITLE, X, Y + HEIGHT + GAP, WIDTH, HEIGHT);
        labelWindow2 = new LabelWindow(LABEL_WINDOW_TITLE, X, Y + (HEIGHT + GAP) * 2, WIDTH, HEIGHT);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                timeThread.stopRunning();
                labelWindow1.closeWindow();
                labelWindow2.closeWindow();
                System.exit(0);
            }
        });

        timeThread = new TimeObservableThread(); // 객체 생성

        timeThread.registerObserver(labelWindow1);
        timeThread.registerObserver(labelWindow2);
        
        timeThread.run();  //시간에 대한 observer 작동
    }

    public JPanel createPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(width, height));
        return panel;
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow(MainWindow.MAIN_TITLE);
    }
}
