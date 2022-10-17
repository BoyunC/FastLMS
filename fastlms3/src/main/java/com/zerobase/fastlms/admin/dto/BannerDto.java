package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BannerDto {

    Long id;

    String bannerName;
    String bannerUrl;
    String target;
    String linkUrl;
    int sortValue;
    boolean pubYn;

    LocalDateTime regDt;

    String fileName;
    String urlFilename;

    //추가컬럼
    long totalCount;
    long seq;

    public static BannerDto of(Banner x) {
        if (x == null) {
            return null;
        }
        return BannerDto.builder()
            .id(x.getId())
            .bannerName(x.getBannerName())
            .fileName(x.getFileName())
            .linkUrl(x.getLinkUrl())
            .pubYn(x.isPubYn())
            .regDt(x.getRegDt())
            .sortValue(x.getSortValue())
            .target(x.getTarget())
            .urlFilename(x.getUrlFilename())
            .build();
    }

    public static List<BannerDto> of(List<Banner> xList) {
        if (xList == null) {
            return null;
        }

        List<BannerDto> bannerList = new ArrayList<>();
        for(Banner x : xList) {
            bannerList.add(BannerDto.of(x));
        }
        return bannerList;
    }

}

