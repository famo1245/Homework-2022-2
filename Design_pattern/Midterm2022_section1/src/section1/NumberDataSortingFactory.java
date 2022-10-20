package section1;

public class NumberDataSortingFactory extends NumberData {

    //"Bubble", "Insertion", "Selection"
    public void create(String sortAlg) {
        if (sortAlg.equals("Bubble")) {
            super.setSortAlg(new BubbleSort());
        } else if (sortAlg.equals("Insertion")) {
            super.setSortAlg(new InsertionSort());
        } else if (sortAlg.equals("Selection")) {
            super.setSortAlg(new SelectionSort());
        } else {
            System.out.println("유효하지 않은 알고리즘");
            super.setSortAlg(null);
        }
    }
    @Override
    public void stirSortPrintData(String sortAlg) {
        create(sortAlg);
        super.stirSortPrintData(sortAlg);
    }
}
