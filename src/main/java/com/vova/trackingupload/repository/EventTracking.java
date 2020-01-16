package com.vova.trackingupload.repository;


import java.sql.Timestamp;

public class EventTracking {
    public EventTracking(String actName, Timestamp createTime, String pageCode, String eventName, String triggerDesc, String elementDesc, String type, String listType, String elementName, String elementId, String elementType, String elementPosition, String extra, String reportDisplayName, String reportDisplayNameDesc) {
        this.actName = actName;
        this.createTime = createTime;
        this.pageCode = pageCode;
        this.eventName = eventName;
        this.triggerDesc = triggerDesc;
        this.elementDesc = elementDesc;
        this.type = type;
        this.listType = listType;
        this.elementName = elementName;
        this.elementId = elementId;
        this.elementType = elementType;
        this.elementPosition = elementPosition;
        this.extra = extra;
        this.reportDisplayName = reportDisplayName;
        this.reportDisplayNameDesc = reportDisplayNameDesc;
    }

    public static EventTracking init(String... args) {
        return new EventTracking(
                args[0],
                Timestamp.valueOf(args[1]),
                args[2],
                args[3],
                args[4],
                args[5],
                args[6],
                args[7],
                args[8],
                args[9],
                args[10],
                args[11],
                args[12],
                args[13],
                args[14]
                );
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public String getPageCode() {
        return pageCode;
    }

    public String getEventName() {
        return eventName;
    }

    public String getTriggerDesc() {
        return triggerDesc;
    }

    public String getElementDesc() {
        return elementDesc;
    }

    public String getType() {
        return type;
    }

    public String getListType() {
        return listType;
    }

    public String getElementName() {
        return elementName;
    }

    public String getElementId() {
        return elementId;
    }

    public String getElementType() {
        return elementType;
    }

    public String getElementPosition() {
        return elementPosition;
    }

    public String getExtra() {
        return extra;
    }

    public String getReportDisplayName() {
        return reportDisplayName;
    }

    public String getReportDisplayNameDesc() {
        return reportDisplayNameDesc;
    }

    public String getActName() {
        return actName;
    }

    private String actName;
    private Timestamp createTime;
    private String pageCode;
    private String eventName;
    private String triggerDesc;
    private String elementDesc;
    private String type;
    private String listType;
    private String elementName;
    private String elementId;
    private String elementType;
    private String elementPosition;
    private String extra;
    private String reportDisplayName;
    private String reportDisplayNameDesc;

    @Override
    public String toString() {
        return "EventTracking{" +
                "actName='" + actName + '\'' +
                ", createTime=" + createTime +
                ", pageCode='" + pageCode + '\'' +
                ", eventName='" + eventName + '\'' +
                ", triggerDesc='" + triggerDesc + '\'' +
                ", elementDesc='" + elementDesc + '\'' +
                ", type='" + type + '\'' +
                ", listType='" + listType + '\'' +
                ", elementName='" + elementName + '\'' +
                ", elementId='" + elementId + '\'' +
                ", elementType='" + elementType + '\'' +
                ", elementPosition='" + elementPosition + '\'' +
                ", extra='" + extra + '\'' +
                ", reportDisplayName='" + reportDisplayName + '\'' +
                ", reportDisplayNameDesc='" + reportDisplayNameDesc + '\'' +
                '}';
    }
}
