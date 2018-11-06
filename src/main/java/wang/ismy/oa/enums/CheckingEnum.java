package wang.ismy.oa.enums;

public enum CheckingEnum {

    ON_DUTY(true),OFF_DUTY(false);

    private boolean checkingType;


    public boolean isCheckingType() {
        return checkingType;
    }


    CheckingEnum(boolean checkingType) {

        this.checkingType=checkingType;
    }



}
