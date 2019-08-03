package com.xkorey.subscribe.pojo;


import com.sun.xml.internal.txw2.annotation.XmlCDATA;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Articles")
@Data
public class WxNewsReply {

    @XmlElement
    private List<ReplayNews> item;


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "item")
    @Data
    public static class ReplayNews{

        @XmlElement
        private String Title;

        @XmlElement
        private String Description;

        @XmlElement
        private String PicUrl;

        @XmlElement
        private String url;

        @XmlCDATA
        public String getPicUrl() {
            return PicUrl;
        }

        @XmlCDATA
        public String getUrl() {
            return url;
        }
    }

}
