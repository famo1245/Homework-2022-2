package mp08;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CommandButton extends JButton {
    public CommandButton(String text, ActionListener listener, Dimension buttonDimension) {
        super(text);
        addActionListener(listener);
        setPreferredSize(buttonDimension);
    }
}

