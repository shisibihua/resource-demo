package com.honghe.resource.dao.impl;

import com.honghe.resource.dao.VideoDao;
import com.honghe.resource.entity.Video;
import com.honghe.util.JdbcTemplateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VideoDaoImpl implements VideoDao{
    @Override
    public List<Video> searchVideos(String dateTime) {
        String sql="select distinct id,videoid from hvideo_db_3_1_0.hv_video_profile where upload_time<'"+dateTime+"'";
        List<Map<String,String>> list=JdbcTemplateUtil.getJdbcTemplate().findList(sql);
        List<Video> videoList=new ArrayList<>();
        if(list!=null && !list.isEmpty()){
            for(Map<String,String> video:list){
                Video vEntity=new Video();
                vEntity.setVideoId(Integer.parseInt(video.get("id")));
                vEntity.setUuid(video.get("videoid"));
                videoList.add(vEntity);
            }
        }
        return videoList;
    }
}
