package final2020;

public class StateAC2 implements State {
    private PhoneNumber phoneNumber;

    public StateAC2(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void process(char ch) {
        if(ch >= '1' && ch <= '5' && phoneNumber.phoneNumber.length() < 3)
            phoneNumber.phoneNumber += ch;
        else if(ch == '-' && phoneNumber.isAreaCode()) {
            phoneNumber.phoneNumber += ch;
            phoneNumber.setState(phoneNumber.getStateDash1());
        }
        else
            phoneNumber.setState(phoneNumber.getStateError());
    }

    @Override
    public boolean checkNotEnd() {
        return false;
    }
}
