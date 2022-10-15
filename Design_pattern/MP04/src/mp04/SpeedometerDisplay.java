package mp04;

import javax.swing.*;
import java.awt.*;

public class SpeedometerDisplay extends DisplayDecorator {
    Display displayComponent;
    JPanel panel;
    LabelPanel label;
    SpeedometerDisplay(Display display, int width, int height) {
        super(display, width, height);
        displayComponent = display;
    }

    @Override
    public JPanel create() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // 패널의 전체 크기 지정
        panel.setMinimumSize(new Dimension(getWidth(), getHeight()));

        JPanel subPanel = displayComponent.create();
        panel.add(subPanel);

        label = new LabelPanel();
        panel.add(label.createPanel(super.getWidth(), super.getHeight()));
        return panel;
    }

    @Override
    public void show() {
        displayComponent.show();
        label.updateText("Speed: 50");
    }
    @Override
    public int getHeight() {
        return displayComponent.getHeight() + super.getHeight();
    }
}
