package src;

import java.util.List;

public class Response {
    private String msg;
    private int code;
    private Object responseObj;
    private List<Object> responseObjs;

    public void setResponseObjs(List<Object> responseObjs) {
        this.responseObjs = responseObjs;
    }

    public List<Object> getResponseObjs() {
        return responseObjs;
    }

    public void setResponseObj(Object responseObj) {
        this.responseObj = responseObj;
    }

    public Object getResponseObj() {
        return responseObj;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public static Response getResponse(String msg)
    {
        return getResponse(0,msg,null);
    }
    public static  Response getResponse(int code,String msg)
    {
        return getResponse(code,msg,null);
    }
    public static Response getResponse(int code,String msg,Object b)
    {
        Response rs = new Response();
        rs.setCode(code);
        rs.setMsg(msg);
        rs.setResponseObj(b);
        return rs;
    }



}
