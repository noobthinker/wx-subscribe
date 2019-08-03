package com.xkorey.subscribe.pojo;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "xml")
@Data
public class MessageRequest implements Serializable {
    @XmlElement
    private String ToUserName;
    @XmlElement
    private String FromUserName;
    @XmlElement
    private Long CreateTime;
    @XmlElement
    private String MsgType;
    @XmlElement
    private String Content;
    @XmlElement
    private Long MsgId;
    @XmlElement
    private Integer ArticleCount;
    @XmlElement
    private WxNewsReply Articles;
    @XmlElement
    private String EventKey;
    @XmlElement
    private String Event;

}
