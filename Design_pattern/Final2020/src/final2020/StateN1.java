package final2020;

public class StateN1 implements State {
    private PhoneNumber phoneNumber;

    public StateN1(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void process(char ch) {
        if(ch >= '0' && ch <= '9' && phoneNumber.countDigitInNum1 <= 3) {
            phoneNumber.phoneNumber += ch;
            phoneNumber.countDigitInNum1++;
        }
        else if(ch == '-' && (phoneNumber.countDigitInNum1 >= 3 && phoneNumber.countDigitInNum1 <= 4)) {
            phoneNumber.phoneNumber += ch;
            phoneNumber.setState(phoneNumber.getStateDash2());
        }
        else
            phoneNumber.setState(phoneNumber.getStateError());
    }

    @Override
    public boolean checkNotEnd() {
        return true;
    }
}
