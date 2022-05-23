package edu.xcj.bilibili.domain.exception;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/5/23 21:25
 */
public class ConditionException extends RuntimeException{
    //序列化
    private static  final long serialVersionUID=1L;
    private String code;
    public ConditionException(String code,String name){
        super(name);
        this.code=code;
    }
    public ConditionException(String name){
        super(name);
        this.code="500";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
