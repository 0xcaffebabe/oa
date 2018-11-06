package wang.ismy.oa.common;

public enum  DataDictionary {

    LOGIN_SUCCESS("登录成功"),
    LOGIN_FAIL("登录失败"),
    NOT_LOGIN("未登录"),
    HAS_LOGIN("已登录"),
    INVALID_CHECKING_TIME("无效的打卡时间"),
    CHECKING_SUCCESS("打卡成功"),
    CHECKING_FAIL("打卡失败"),
    REPEAT_CHECKING("重复打卡");

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
