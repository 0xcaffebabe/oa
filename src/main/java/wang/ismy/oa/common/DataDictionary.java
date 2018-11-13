package wang.ismy.oa.common;

public enum  DataDictionary {

    LOGIN_SUCCESS("登录成功"),
    LOGIN_FAIL("登录失败"),
    NOT_LOGIN("未登录"),
    HAS_LOGIN("已登录"),
    INVALID_CHECKING_TIME("无效的打卡时间"),
    CHECKING_SUCCESS("打卡成功"),
    CHECKING_FAIL("打卡失败"),
    REPEAT_CHECKING("重复打卡"),
    START_OVER_END("起始时间大于结束时间"),
    TIME_OVER("事件时间已是过去"),
    TIME_EQUALS("起始时间相等"),
    EVENT_NOT_EXIST("事件不存在"),
    PERMISSION_DENIED("权限不足"),
    DELETE_SUCCESS("删除成功"),
    DELETE_FAIL("删除失败"),
    UPDATE_SUCCESS("更新成功"),
    UPDATE_FAIL("更新失败"),
    SAVE_SUCCESS("保存成功"),
    SAVE_FAIL("保存失败"),
    PARAM_ERROR("参数错误"),
    USER_NOT_EXIST("用户不存在");

    private Object data;

    DataDictionary(String data) {
        this.data=data;
    }


    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
