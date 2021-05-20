package com.ccsu.jc.tvbank.service;

import com.ccsu.jc.tvbank.domain.VideoEntity;

import java.util.List;

/**
 * @author jc
 * @date 2021/5/8 18:13
 * @apiNote
 */
public interface VideoService {


    /**
     * 将video 全部信息全部查询出来
     */
    List<VideoEntity> videoList(String videoCategory);


    /**
     * 根据当前页查询出6条记录
     *
     * @param dangqianye            页面传过来的当前页
     * @param state                 需要查询的视频列别
     * @param meiyexianshiduoshaoge 每页显示多少个视频
     */
    List<VideoEntity> pageVideoList(String state, int dangqianye, int meiyexianshiduoshaoge);


    /**
     * 根据标记 查询出一共有多少条记录
     */
    int videoCount(String countvideo);


    /**
     * 随机在video 里面查询出5条记录
     */
    List<VideoEntity> videolistimit7();

    /**
     * 随机在video 里面查询出6条记录
     *
     * @return
     */
    List<VideoEntity> videolistimit6MAD();


    /**
     * 随机在video 里面查询出5条记录
     */
    List<VideoEntity> videolistimit5MAD();

    /**
     * search by name
     */
    List<VideoEntity> searchByName(String name);
}
