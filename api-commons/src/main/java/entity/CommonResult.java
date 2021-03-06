package entity;
/**
 * @author xtt
 * @date 2021/1/14
 * 返回结果类封装
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
    public void setMsg(String msg) {
        this.message = msg;
    }
}
