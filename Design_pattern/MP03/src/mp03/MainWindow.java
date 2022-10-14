package mp03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends FrameWindow implements ActionListener {
    private static final String MAIN_TITLE = "Main Window";
    private static final String TEXTFIELD_WINDOW_TITLE = "TextField Window";
    private static final String LABEL_WINDOW_TITLE = "Label Window";
    private static final String TEXTFIELD_OBSERVER_BUTTON_TITLE = "Add TextField Window Observer";
    private static final String TEXTFIELD_REMOVE_OBSERVER_BUTTON_TITLE = "Remove TextField Window Observer";
    private static final String LABEL_OBSERVER_BUTTON_TITLE = "Add Label Window Observer";
    private static final String LABEL_REMOVE_OBSERVER_BUTTON_TITLE = "Remove Label Window Observer";
    private static final String STOP_THREAD_BUTTON_TITLE = "Stop Generating Prime Number";
    private static final int X = 250;
    private static final int Y = 100;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 200;
    private static final int GAP = 50;
    private static final int NUM_BUTTONS = 3;

    private JButton stopButton;
    private JButton updateTextFieldObserverButton;
    private JButton updateLabelObserverButton;
    private PrimeObservableThread primeThread;
    private TextFieldWindow textFieldWindow;
    private LabelWindow labelWindow;

    public MainWindow(String title) {
        super(title, X, Y, WIDTH, HEIGHT);
        textFieldWindow = new TextFieldWindow(TEXTFIELD_WINDOW_TITLE, X, Y + HEIGHT + GAP, WIDTH, HEIGHT);
        labelWindow = new LabelWindow(LABEL_WINDOW_TITLE, X, Y + (HEIGHT + GAP) * 2, WIDTH, HEIGHT);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                primeThread.stopRunning();
                textFieldWindow.closeWindow();
                labelWindow.closeWindow();
                System.exit(0);
            }
        });

        primeThread = new PrimeObservableThread(); // 객체 생성

        primeThread.registerObserver(textFieldWindow);
        primeThread.registerObserver(labelWindow);
        
        primeThread.run();  // 소수 생성 시작. 이 함수가 실행된 후에는 stopButton이 눌리기 전까지 무한 반복됨
    }

    public JPanel createPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(width, height));
        updateTextFieldObserverButton = createButton(TEXTFIELD_REMOVE_OBSERVER_BUTTON_TITLE, this, width, height);
        panel.add(updateTextFieldObserverButton);
        updateLabelObserverButton = createButton(LABEL_REMOVE_OBSERVER_BUTTON_TITLE, this, width, height);
        panel.add(updateLabelObserverButton);
        stopButton = createButton(STOP_THREAD_BUTTON_TITLE, this, width, height);
        panel.add(stopButton);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateTextFieldObserverButton) {
            String text = updateTextFieldObserverButton.getText();
            switch (text) {
                case TEXTFIELD_OBSERVER_BUTTON_TITLE:
                    updateTextFieldObserverButton.setText(TEXTFIELD_REMOVE_OBSERVER_BUTTON_TITLE);
                    primeThread.registerObserver(textFieldWindow);
                    break;

                case TEXTFIELD_REMOVE_OBSERVER_BUTTON_TITLE:
                    updateTextFieldObserverButton.setText(TEXTFIELD_OBSERVER_BUTTON_TITLE);
                    primeThread.unregisterObserver(textFieldWindow);
                    break;
            }
        }
        else if (e.getSource() == updateLabelObserverButton) {
            String text = updateLabelObserverButton.getText();
            switch (text) {
                case LABEL_OBSERVER_BUTTON_TITLE:
                    updateLabelObserverButton.setText(LABEL_REMOVE_OBSERVER_BUTTON_TITLE);
                    primeThread.registerObserver(labelWindow);
                    break;

                case LABEL_REMOVE_OBSERVER_BUTTON_TITLE:
                    updateLabelObserverButton.setText(LABEL_OBSERVER_BUTTON_TITLE);
                    primeThread.unregisterObserver(labelWindow);
                    break;
            }
        }
        else if (e.getSource() == stopButton) {
            primeThread.stopRunning();
        }
    }

    private JButton createButton(String text, ActionListener listener, int width, int height) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        Dimension buttonDimension = new Dimension(width, height / NUM_BUTTONS);
        button.setMaximumSize(buttonDimension);
        button.setMinimumSize(buttonDimension);
        button.setPreferredSize(buttonDimension);
        return button;
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow(MainWindow.MAIN_TITLE);
    }
}
