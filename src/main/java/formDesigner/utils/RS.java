package formDesigner.utils;

public class RS {
    private Integer code;
    private Boolean success;
    private String message;

    private RS(){};

    public static RS ok(){
        RS r = new RS();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public RS message(String msg){
        this.setMessage(msg);
        return this;
    }

    public static RS error(){
        RS r = new RS();
        r.setCode(ResultCode.ERROR);
        r.setSuccess(false);
        r.setMessage("失败");
        return r;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
