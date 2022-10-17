package com.zerobase.fastlms.admin.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BannerInput {

    Long id;

    String bannerName;
    String fileName;
    String linkUrl;
    boolean pubYn;
    LocalDateTime regDt;
    int sortValue;
    String target;
    String urlFilename;

    String idList;

}
