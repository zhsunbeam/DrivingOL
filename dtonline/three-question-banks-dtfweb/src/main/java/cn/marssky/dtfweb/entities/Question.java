package cn.marssky.dtfweb.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Long id; //题目编号
    private String title;//题目标题
    private String subtitle;//副标题
    private String avatar;//图片路径
    private int difficulty;//题目难度
    private String avatarType;//车型
    private int sort;//视频顺序
    private int isHide;//是否隐藏视频
    private String createdAt;//创建时间
    private String updatedAt;//更新时间
    private String video;//视频地址
    private String videoTime;//视频时长
    private int driverSchoolId;//所属驾校
}
