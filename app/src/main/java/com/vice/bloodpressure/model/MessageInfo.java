package com.vice.bloodpressure.model;

import java.io.Serializable;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class MessageInfo implements Serializable {
    /**
     * 内容
     */
    private String content;
    /**
     * 标题
     */
    private String title;


    private String num = "";
    /**
     * 消息类型
     */
    private String msgType;
    /**
     * 消息数据
     */
    private String msgData;

    /**
     * 文章id
     */
    private String articleId;
    /**
     * type
     */
    private String pkId;
    /**
     * 在宣教列表宣教类型:1->图文;2->音频;3->视频;
     * 在异常数据列表中，表示 1血糖数据  2 血压数据
     */
    private String type;
    /**
     * 宣教简介
     */
    private String bi;
    /**
     * 时间
     */
    private String sendTime;
    /**
     * 0 未阅读 1 已阅读
     */
    private String status;
    /**
     * 详情
     */
    private String detail;
    /**
     * 时间
     */
    private String createTime;
    /**
     * 文件路径
     */
    private String fileUrl;

    /**
     * id
     */
    private String id;
    /**
     * 异常类型   1 全部 2 血压偏高 3 血压偏低 4 血糖偏高 5 血糖偏低 6 血糖全部 7 血压全部
     */
    private String exceptionType;

    /**
     * 值
     */
    private String value;
    /**
     * 单位
     */
    private String unit;
    /**
     * 时间
     */
    private String addTime;
    /**
     * 0未读 1 已读
     */
    private String readStatus;
    /**
     * 提示
     */
    private String promptText;

    /**
     * 消息id
     */
    private String msgId;

    /**
     * 患者宣教中h5的链接
     */
    private String iframeUrl;

    /**
     * 血糖目标：1
     * 血压目标：2
     * 用药提醒：3
     * 运动目标：4
     */
    private String tagType;
    /**
     * 消息数据
     */
    private String tagData;
    /**
     * 消息数据
     */
    private String tagDate;

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getTagData() {
        return tagData;
    }

    public void setTagData(String tagData) {
        this.tagData = tagData;
    }

    public String getTagDate() {
        return tagDate;
    }

    public void setTagDate(String tagDate) {
        this.tagDate = tagDate;
    }

    public String getIframeUrl() {
        return iframeUrl;
    }

    public void setIframeUrl(String iframeUrl) {
        this.iframeUrl = iframeUrl;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getPromptText() {
        return promptText;
    }

    public void setPromptText(String promptText) {
        this.promptText = promptText;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getPkId() {
        return pkId;
    }

    public void setPkId(String pkId) {
        this.pkId = pkId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }


    public MessageInfo(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgData() {
        return msgData;
    }

    public void setMsgData(String msgData) {
        this.msgData = msgData;
    }
}
