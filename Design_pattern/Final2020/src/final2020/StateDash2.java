package final2020;

public class StateDash2 implements State {
    private PhoneNumber phoneNumber;

    public StateDash2(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void process(char ch) {
        if(ch >= '0' && ch <= '9') {
            phoneNumber.phoneNumber += ch;
            phoneNumber.countDigitInNum2++;
            phoneNumber.setState(phoneNumber.getStateN2());
        }
        else
            phoneNumber.setState(phoneNumber.getStateError());
    }

    @Override
    public boolean checkNotEnd() {
        return true;
    }
}
