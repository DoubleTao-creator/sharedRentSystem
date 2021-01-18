package entity;
/**
 * @author xtt
 * @date 2021/1/14
 * 状态码信息
 */

import lombok.AllArgsConstructor;
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
    ERROR(201,"失败"),
    /**
     * 资源不存在
     */
    NOT_FOUND(404,"未找到该资源"),
    /**
     * 服务器内部错误
     */
    INTERNAL_ERROR(500,"服务器内部错误");
    private int code;
    private String message;
}
