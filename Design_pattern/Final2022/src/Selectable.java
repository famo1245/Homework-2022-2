public interface Selectable {
    public int getMinBoundsX();
    public int getMinBoundsY();
    public int getMaxBoundsX();
    public int getMaxBoundsY();

    public String getName();

    // 커서의 위치(x, y)가 Selectable의 Binding box(바인딩 박스) 안에 있으면 true 아니면 false 반환
    public boolean isSelected(int x, int y);

    // 이름과 bounding box 정보 출력
    // 출력 서식은 출력 화면 참조
    // Group에서는 Group의 bounding box뿐만 아니라 각 요소들도 출력
    public void print();
}
