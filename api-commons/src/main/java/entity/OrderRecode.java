package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author xtt
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRecode {
    private Integer id;
    private Integer goodsId;
    private Integer userId;
    private Integer modeId;
    private Double cost;
    private Timestamp paytime;
    private String info;
}
