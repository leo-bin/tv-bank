package com.ccsu.jc.tvbank.service.impl;

import com.ccsu.jc.tvbank.dao.VideoDAO;
import com.ccsu.jc.tvbank.domain.VideoEntity;
import com.ccsu.jc.tvbank.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jc
 * @date 2021/5/8 18:14
 * @apiNote
 */
@Service("VideoService")
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDAO videoDAO;

    @Override
    public void insertVideo(VideoEntity videoEntity) {
        videoDAO.insert(videoEntity);
    }

    @Override
    public List<VideoEntity> videoList(String videoCategory) {
        List<VideoEntity> list = videoDAO.videolist(videoCategory);
        return list;
    }

    @Override
    public List<VideoEntity> videolistimit7() {
        List<VideoEntity> list = videoDAO.videolistimit7();
        return list;
    }

    @Override
    public List<VideoEntity> videolistimit5MAD() {
        List<VideoEntity> list = videoDAO.videolistimit5MAD();
        return list;
    }


    @Override
    public List<VideoEntity> pageVideoList(String state, int dangqianye, int meiyexianshiduoshaoge) {
        List<VideoEntity> list = videoDAO.pageVideoList(state, dangqianye, meiyexianshiduoshaoge);
        return list;
    }

    @Override
    public int videoCount(String countvideo) {
        int num = videoDAO.videoCount(countvideo);
        return num;
    }

    @Override
    public List<VideoEntity> videolistimit6MAD() {
        List<VideoEntity> list = videoDAO.videolistimit6MAD();
        return list;
    }

    @Override
    public List<VideoEntity> searchByName(String name) {
        return videoDAO.searchByName("%" + name + "%");
    }
}
