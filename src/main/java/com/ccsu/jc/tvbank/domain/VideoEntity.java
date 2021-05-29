package com.ccsu.jc.tvbank.domain;

import lombok.Data;

@Data
public class VideoEntity {

    private String videoID; //视频ID
    private String videoName;//视频名字
    private String videoImage;//视频图片
    private String videoAddress;//视频地址
    private String videolookTime;//视频时长
    private String videoCollection;//收藏
    private String videoLeaving;//视频留言
    private String videoTime;//视频上传时间
    private String videoState;//视频状态
    private String videoCategory;//视频类别
    private String upName; //视频上传者
    private String categoryName; //视频分类名

}
