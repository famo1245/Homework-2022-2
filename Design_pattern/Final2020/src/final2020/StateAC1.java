package final2020;

public class StateAC1 implements State {
    private PhoneNumber phoneNumber;

    public StateAC1(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void process(char ch) {
        if(ch >= '2' && ch <= '6') {
            phoneNumber.phoneNumber += ch;
            phoneNumber.setState(phoneNumber.getStateAC2());
        }
        else
            phoneNumber.setState(phoneNumber.getStateError());
    }

    @Override
    public boolean checkNotEnd() {
        return true;
    }
}
