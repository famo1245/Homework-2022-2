package final2020;

public class StateStart implements State{
    private PhoneNumber phoneNumber;

    public StateStart(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void process(char ch) {
        if(ch == '0') {
            phoneNumber.phoneNumber += ch;
            phoneNumber.setState(phoneNumber.getStateAC1());
        }
        else
            phoneNumber.setState(phoneNumber.getStateError());
    }

    @Override
    public boolean checkNotEnd() {
        return true;
    }
}
