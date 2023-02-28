package com.vice.bloodpressure.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: beauty
 * 类名:
 * 传参:
 * 描述:
 */
public class EducationInfo implements Serializable {
    private String bg;
    private String title;
    private String content;
    private String state;
    private String subject;
    private List<EducationInfo> childList;
    private List<EducationInfo> list;

    public EducationInfo(String title, String state) {
        this.title = title;
        this.state = state;
    }

    public EducationInfo(String bg, String title, String content, String state, String subject) {
        this.bg = bg;
        this.title = title;
        this.content = content;
        this.state = state;
        this.subject = subject;
    }

    /**
     * 那个按钮的展示状态 0展开 1收起状态，展开更多
     */
    private int isExpand=1;

    public List<EducationInfo> getList() {
        return list;
    }

    public void setList(List<EducationInfo> list) {
        this.list = list;
    }

    public int getIsExpand() {
        return isExpand;
    }

    public void setIsExpand(int isExpand) {
        this.isExpand = isExpand;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<EducationInfo> getChildList() {
        return childList;
    }

    public void setChildList(List<EducationInfo> childList) {
        this.childList = childList;
    }
}
