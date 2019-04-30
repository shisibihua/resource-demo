package com.honghe.resource;

import com.honghe.resource.dao.impl.VideoDaoImpl;
import com.honghe.resource.dao.VideoDao;
import com.honghe.resource.entity.Video;
import com.honghe.util.ConfigUtil;
import com.honghe.util.HttpRequestUtil;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;


/**
 * 主程序
 * @author caoqian
 */
public class Main
{
    private static final Logger logger = Logger.getLogger(Main.class);
    private static String url;
    private static String ip;
    private static int port;
    private static long delay;
    private static String path;
    private static String delVideoDay;

    static{
        url=ConfigUtil.getInstance().getConfig("resource_url").toString();
        ip= ConfigUtil.getInstance().getConfig("resource_ip").toString();
        port=Integer.parseInt(ConfigUtil.getInstance().getConfig("resource_port").toString());
        path="http://"+ip+":"+port+url;
        delay = Long.parseLong(ConfigUtil.getInstance().getConfig("delay").toString());
        delVideoDay=ConfigUtil.getInstance().getConfig("del_video_day").toString();

    }
    private static void delVideos()
    {
        try{
            VideoDao dao=new VideoDaoImpl();
            List<Video> videos=dao.searchVideos(getTimeOutTime());
            if(videos!=null && !videos.isEmpty()){
                for(Video video:videos){
                    String query="id="+video.getVideoId()+"&vid="+video.getUuid();
                    try {
                        String result = HttpRequestUtil.sendGet(path, query);
                        logger.debug("删除视频，接口返回结果===="+result+",延迟" + (delay / 1000L) + "s发送接口通知");
                    }catch (Exception e){
                        ;
                    }
                    Thread.sleep(delay);
                }
            }
        }
        catch (Exception e)
        {
            logger.error("删除资源平台过期视频异常！！", e);
        }
    }

    /**
     * 获取过期时间
     * @return
     */
    private static String getTimeOutTime() {
        Date now=new Date();
        Date timeOutDate=addDay(now,Integer.parseInt("-"+delVideoDay));
        SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
        String dateTime=simp.format(timeOutDate)+" 00:00:00";
        return dateTime;
    }

    private static void delTimeOutVideos(){
        Calendar calendar = Calendar.getInstance();
        int hour=Integer.parseInt(ConfigUtil.getInstance().getConfig("send_hour").toString());
        int minute=Integer.parseInt(ConfigUtil.getInstance().getConfig("send_min").toString());
        int second=Integer.parseInt(ConfigUtil.getInstance().getConfig("send_sec").toString());
        // 控制时
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        // 控制分
        calendar.set(Calendar.MINUTE, minute);
        // 控制秒
        calendar.set(Calendar.SECOND, second);
        // 得出执行任务的时间,此处为今天的03：00：00
        Date time = calendar.getTime();
        if (time.before(new Date())) {
            time = addDay(time, 1);
        }
        //每天执行一次。第一次在指定time时间点执行任务，之后每天03:00:00调用任务一次。
        Timer timer=new Timer("delVideos");
        long period = Long.parseLong(ConfigUtil.getInstance().getConfig("period").toString());
        long delPeriod=period*Integer.parseInt(delVideoDay);
        try {
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    delVideos();
                }
            }, time, delPeriod);
        }catch (IllegalArgumentException e){
            logger.error("定时器发生异常",e);
        }catch (Exception e){
            logger.error("定时器发成异常。。。。。。。。。。。",e);
        }
    }

    /**
     * 增加或减少天数
     * @param date
     * @param num
     * @return
     */
    private static Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }

    public static void main(String[] args) {
        logger.debug("程序启动完成,开始删除过期视频文件............");
        delTimeOutVideos();
    }
}
