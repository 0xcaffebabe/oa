package wang.ismy.oa.dto;

import lombok.Data;
import wang.ismy.oa.common.DataDictionary;
import wang.ismy.oa.enums.ResultState;

@Data
public class Result<T> {


    private int code;

    private String message;

    private T data;
    public Result(T data){
        this.data=data;
    }

    public Result(ResultState resultState,T data){
        this.code=resultState.getCode();
        this.message=resultState.getMessage();
        this.data=data;
    }





}
