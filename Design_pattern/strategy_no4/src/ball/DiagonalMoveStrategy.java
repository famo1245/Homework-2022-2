package ball;

public class DiagonalMoveStrategy extends DirectionStrategy {
    @Override
    public void move(Ball ball) {
        ball.setInterval(Ball.INTERVAL, Ball.INTERVAL);
        while(true) {
            ball.setX(ball.getX() + ball.getXinterval());
            ball.setY(ball.getY() + ball.getYinterval());

            if ((ball.getY() < 0 && ball.getYinterval() < 0) ||
                    ball.getY() + Ball.SIZE > BallFrame.HEIGHT - 25 && ball.getYinterval() > 0) {
                ball.setInterval(-ball.getXinterval(), -ball.getYinterval());
            }

            try {
                Thread.sleep(30);
            } catch (Exception e) {}
        }
    }
}
