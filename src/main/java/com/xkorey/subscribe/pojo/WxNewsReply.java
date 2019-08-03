package com.xkorey.subscribe.pojo;


import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;


@Data
public class WxNewsReply {

    @XmlElement
    private List<ReplayNews> item;


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
    }

}
