package enums;
/**
 * @author xtt
 * @date 2021/1/14
 * 状态码信息
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
public enum HttpStatus {
    /**
     * 操作成功
     */
    OK(200,"成功"),
    /**
     * 操作失败
     */
    ERROR(201,"失败");
    /**
     * 资源不存在
     */
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
