package final2020;

public class PhoneNumber {
    String[] areaCode = { "02", "031", "032", "033", "041", "042", "043", "044", "051", "052", "053", "054",
                          "055", "061", "062", "063", "064" };
    String phoneNumber = ""; // empty string
    int countDigitInNum1 = 0;
    int countDigitInNum2 = 0;
    int errorPos = 0;
    private State state;
    private StateStart stateStart;
    private StateAC1 stateAC1;
    private StateAC2 stateAC2;
    private StateDash1 stateDash1;
    private StateDash2 stateDash2;
    private StateN1 stateN1;
    private StateN2 stateN2;
    private StateEnd stateEnd;
    private StateError stateError;

    public PhoneNumber() {
        stateStart = new StateStart(this);
        stateAC1 = new StateAC1(this);
        stateAC2 = new StateAC2(this);
        stateDash1 = new StateDash1(this);
        stateDash2 = new StateDash2(this);
        stateN1 = new StateN1(this);
        stateN2 = new StateN2(this);
        stateEnd = new StateEnd(this);
        stateError = new StateError(this);
        state = stateStart;
    }

    public int getErrorPos() {
        return errorPos;
    }

    public boolean verify(String str) {
        initialize();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            state.process(ch);
            if(state == stateError) {
                errorPos = phoneNumber.length();
                return false;
            }
        }

        if(state.checkNotEnd()) {
            errorPos = phoneNumber.length();
            return false;
        }
        return true;
    }

    private void initialize() {
        phoneNumber = ""; // empty string
        countDigitInNum1 = 0;
        countDigitInNum2 = 0;
        state = stateStart;
        errorPos = 0;
    }

    public boolean isAreaCode() {
        for (String s : areaCode) {
            if (s.equals(phoneNumber))
                return true;
        }
        return false;
    }

    public void setState(State state) {
        this.state = state;
    }

    public StateAC1 getStateAC1() {
        return stateAC1;
    }

    public StateAC2 getStateAC2() {
        return stateAC2;
    }

    public StateDash1 getStateDash1() {
        return stateDash1;
    }

    public StateDash2 getStateDash2() {
        return stateDash2;
    }

    public StateN1 getStateN1() {
        return stateN1;
    }

    public StateN2 getStateN2() {
        return stateN2;
    }

    public StateEnd getStateEnd() {
        return stateEnd;
    }

    public StateError getStateError() {
        return stateError;
    }
}
