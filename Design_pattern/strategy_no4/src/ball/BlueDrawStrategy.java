package ball;

import java.awt.*;

public class BlueDrawStrategy extends DrawStrategy {
    @Override
    public void draw(Ball ball) {
        ball.setColor(Color.blue);
    }
}
