package zed.eureka.cli.constant;

public enum ResultEnum {
    //正常
    OK(10000, "正常"),
    //失败
    FAIL(40000, "失败"),
    //登入拒绝
    DENIED(40001, "登入拒绝"),
    //权限不足
    NO_ACCESS(40002, "权限不足"),
    //查找失败
    NOT_FOUND(40400, "查找失败"),
    //服务器错误
    INTERNAL_SERVER_ERROR(50000, "服务器错误");
    private String statusInfo;
    private int statusCode;

     ResultEnum(int code, String Info) {
        setStatusCode(code);
        setStatusInfo(Info);
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
