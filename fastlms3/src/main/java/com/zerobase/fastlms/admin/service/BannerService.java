package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;

import java.util.List;

public interface BannerService {

    // 배너 추가
    boolean add(BannerInput parameter);
    
    // 배너 수정
    boolean set(BannerInput parameter);

    // 배너 삭제
    boolean del(String idList);

    // 배너 정보
    BannerDto getById(long id);

    // 배너 리스트
    List<BannerDto> list(BannerParam parameter);

    // 프론트 배너 리스트
    List<BannerDto> frontList();

}
