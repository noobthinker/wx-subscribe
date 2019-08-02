package com.xkorey.subscribe.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class StaffItemResponse {

    @JsonProperty("media_id")
    private String mediaId;

    private StaffContent content;


    @Data
    public static class StaffContent{
        @JsonProperty("news_item")
        private List<Item> newsItem;
    }

    @Data
    public static class Item{
        private String  title;
        @JsonProperty("thumb_media_id")
        private String thumbMediaId;
        @JsonProperty
        private String showCoverPic;

        private String author;

        private String digest;

        private String content;

        private String url;
        @JsonProperty("content_source_url")
        private String contentSourceUrl;

    }



}
