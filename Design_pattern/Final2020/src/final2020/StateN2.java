package final2020;

public class StateN2 implements State {
    private PhoneNumber phoneNumber;

    public StateN2(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void process(char ch) {
        if(ch >= '0' && ch <= '9' && phoneNumber.countDigitInNum2 <= 3) {
            phoneNumber.phoneNumber += ch;
            phoneNumber.countDigitInNum2++;
            if (phoneNumber.countDigitInNum2 == 4)
                phoneNumber.setState(phoneNumber.getStateEnd());
        }
        else
            phoneNumber.setState(phoneNumber.getStateError());
    }

    @Override
    public boolean checkNotEnd() {
        return true;
    }
}
