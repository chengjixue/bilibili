package edu.xcj.bilibili.domain;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/23 19:50
 */
public class JsonResponse<T> {
    private String code;
    private String msg;
    private T data;

    public JsonResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResponse(T data) {
        this.data = data;
        msg = "成功";
        code = "0";
    }

    /*
     *不需要返回前端，但请求成功的的请求
     *
     * */

    public static JsonResponse<String> success() {
        return new JsonResponse<>(null);
    }

    /*
     *需要返回前端数据调用
     *
     * */

    public static JsonResponse<String> sucess(String data) {
        return new JsonResponse<>(data);
    }

    /*
     *请求失败返回
     *
     * */

    public static JsonResponse<String> fail() {
        return new JsonResponse<>("1", "失败");
    }

    /*
     *请求失败自定义msg返回
     *
     * */

    public static JsonResponse<String> fail(String code, String msg) {
        return new JsonResponse<>(code, msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
