package no1;

public class Main {
/*    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(10, 40);
        // 사각형의 바운딩 박스 계산
        r1.calcBounds();
        System.out.print(r1);
        Rectangle r2 = new Rectangle(20, 50);
        // 사각형의 바운딩 박스 계산
        r2.calcBounds();
        System.out.print(r2);
    }*/
    public static void main(String[] args) {
        // 팩토리 생성
        // 여기서 만들어지는 ShapeFactory는 우리가 배운 추상 팩토리와는 달리
        // 한 가지 종류의 객체만 생성함(Rectangle 또는 Triangle 객체)
        ShapeFactory shapeFactory = new ShapeFactory();
        final int MAX_SHAPES = 4;

        // ShapeFactory에서 사각형과 삼각형 생성 후 그룹으로 묶음. 그룹 이름: Group1
        Shape s1 = shapeFactory.createShape("Rectangle", "Rectangle1", 10, 40);
        Shape s2 = shapeFactory.createShape("Triangle", "Triangle1", 10, 40);
        Group g = new Group("Group1", s1, s2);

        // 기존 그룹과 새로운 도형을 더해 다시 그룹으로 구성함
        // 새로 추가되는 도형의 모양은 랜덤 정수를 생성해서 지정
        // 1: 사각형
        // 2: 삼각형
        for (int i = 0; i < MAX_SHAPES - 1; i++) {
            String shapeType = (MyRandom.randInt(1, 2) == 1) ? "Rectangle" : "Triangle";
            int min = MyRandom.randInt(10, 20);
            int max = MyRandom.randInt(50, 70);
            g = new Group("Group" + (i + 2), g,
                          shapeFactory.createShape(shapeType, shapeType + (i + 2), min, max));
        }
        // 그룹의 바운딩 박스 계산
        g.calcBounds();
        System.out.println(g);
    }
}
