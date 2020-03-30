package cn.marssky.video.dto;


import lombok.*;

/**
 * 科目二题库(TwoQuestionBanks)数据传输对象
 *
 * @author makejava
 * @since 2020-03-25 16:33:01
 */
@AllArgsConstructor
@Data
@Builder
@ToString
@NoArgsConstructor
public class TwoQuestionBanksDto {
    
    private int id;
    /**
    * 标题
    */
    private String title;
    /**
    * 副标题
    */
    private String subtitle;
    /**
    * 图片
    */
    private String avatar;
    /**
    * 难点
    */
    private int difficulty;
    /**
    * 类型
    */
    private String avatarType;
    /**
    * 排序
    */
    private int sort;
    
    private byte isHide;
    
    private String createdAt;
    
    private String updatedAt;
    /**
    * 视频
    */
    private String video;
    
    private String videoTime;
    /**
    * 所属驾校ID
    */
    private int driverSchoolId;

}