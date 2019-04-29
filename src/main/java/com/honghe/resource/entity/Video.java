package com.honghe.resource.entity;

/**
 * 视频实体
 * @author caoqian
 * @date 201904289
 */
public class Video {
    private  int videoId;
    private String uuid;
    private String clientIp;
    private int teacherId;
    private String teacherName;
    private int source;
    private int subjectId;
    private int gradeId;
    private int versionId;
    private int uId;
    private String title;
    private String uploadTime;
    private int isPublish;

    public Video() {
    }

    public Video(int videoId, String uuid, String clientIp, int teacherId, String teacherName, int source, int subjectId,
                 int gradeId, int versionId, int uId, String title, String uploadTime, int isPublish) {
        this.videoId = videoId;
        this.uuid = uuid;
        this.clientIp = clientIp;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.source = source;
        this.subjectId = subjectId;
        this.gradeId = gradeId;
        this.versionId = versionId;
        this.uId = uId;
        this.title = title;
        this.uploadTime = uploadTime;
        this.isPublish = isPublish;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(int isPublish) {
        this.isPublish = isPublish;
    }
}
