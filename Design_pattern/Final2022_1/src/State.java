public interface State {
    default void processDigit(int digit) {
        System.out.println("invalid operation");
    }

    default void processArithmeticOperator(char ch) {
        System.out.println("invalid operation");
    }

    default void processEqualOperator() {
        System.out.println("invalid operation");
    }
}
