package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer id;
    private Integer userId;
    private Integer cgoodsId;
    private String remark;
    private String remarkPic;
    private Timestamp time;
}
