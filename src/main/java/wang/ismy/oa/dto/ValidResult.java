package wang.ismy.oa.dto;

import lombok.Data;

@Data
public class ValidResult {

    private boolean isValid;

    private String validFailMessage;

    public ValidResult(boolean isValid,String validFailMessage){
        this.isValid=isValid;
        this.validFailMessage=validFailMessage;
    }

    public ValidResult(boolean isValid){
        this.isValid=isValid;
    }
}
