package com.ccsu.jc.tvbank.service.impl;

import com.ccsu.jc.tvbank.TvBankApplicationTests;
import com.ccsu.jc.tvbank.domain.VideoEntity;
import com.ccsu.jc.tvbank.service.VideoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class VideoServiceImplTest extends TvBankApplicationTests {

    @Autowired
    private VideoService videoService;

    @Test
    void videoList() {
        List<VideoEntity> list = videoService.videoList("1");
        if (list != null && list.size() >= 1) {
            System.out.println(list.size());
        } else {
            System.out.println("error");
        }
    }
}