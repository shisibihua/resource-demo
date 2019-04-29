package com.honghe.resource.dao;

import com.honghe.resource.entity.Video;

import java.util.List;

/**
 * 查询资源平台视频
 * @author caoqian
 * @date 20190429
 */
public interface VideoDao {
    /**
     * 根据上传时间查询视频列表
     * @param dateTime   上传日期
     * @return
     */
    List<Video> searchVideos(String dateTime);
}
