package final2020;

public class StateError implements State {
    private PhoneNumber phoneNumber;

    public StateError(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void process(char ch) {
        // do nothing
    }

    @Override
    public boolean checkNotEnd() {
        return false;
    }
}
