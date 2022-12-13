package final2020;

public class StateEnd implements State {
    private PhoneNumber phoneNumber;

    public StateEnd(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void process(char ch) {
        phoneNumber.setState(phoneNumber.getStateError());  // end에서 입력이 들어오면 에러
    }

    @Override
    public boolean checkNotEnd() {
        return false;
    }
}
