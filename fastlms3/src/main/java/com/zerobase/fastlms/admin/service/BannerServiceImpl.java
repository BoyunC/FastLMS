package com.zerobase.fastlms.admin.service;


import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public boolean add(BannerInput parameter) {

        Banner banner = Banner.builder()
            .bannerName(parameter.getBannerName())
            .linkUrl(parameter.getLinkUrl())
            .target(parameter.getTarget())
            .sortValue(parameter.getSortValue())
            .pubYn(parameter.isPubYn())
            .fileName(parameter.getFileName())
            .urlFilename(parameter.getUrlFilename())
            .regDt(LocalDateTime.now())
            .build();
        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean set(BannerInput parameter) {
        bannerRepository.findById(parameter.getId()).ifPresent(e -> {
            e.setBannerName(parameter.getBannerName());
            e.setLinkUrl(parameter.getLinkUrl());
            e.setTarget(parameter.getTarget());
            e.setSortValue(parameter.getSortValue());
            e.setPubYn(parameter.isPubYn());
            e.setFileName(parameter.getFileName());
            e.setUrlFilename(parameter.getUrlFilename());
            bannerRepository.save(e);
        });

        return true;
    }

    @Override
    public boolean del(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Override
    public BannerDto getById(long id) {
        Banner banner = bannerRepository.getById(id);
        return BannerDto.of(banner);
    }

    @Override
    public List<BannerDto> list(BannerParam parameter) {

        long totalCount = bannerMapper.selectListCount(parameter);
        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public List<BannerDto> frontList() {

        List<BannerDto> bannerList = null;

        Optional<List<Banner>> optionalBanners = bannerRepository.findByPubYnOrderBySortValue(true);

        if (optionalBanners.isPresent()) {
            List<Banner> banners = optionalBanners.get();
            bannerList = BannerDto.of(banners);
        }

        return bannerList;
    }

}


