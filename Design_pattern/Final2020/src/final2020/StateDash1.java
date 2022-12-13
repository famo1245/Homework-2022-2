package final2020;

public class StateDash1 implements State {
    private PhoneNumber phoneNumber;

    public StateDash1(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void process(char ch) {
        if (ch >= '1' && ch <= '9') {
            phoneNumber.phoneNumber += ch;
            phoneNumber.countDigitInNum1++;
            phoneNumber.setState(phoneNumber.getStateN1());
        }
        else
            phoneNumber.setState(phoneNumber.getStateError());
    }

    @Override
    public boolean checkNotEnd() {
        return true;
    }
}
