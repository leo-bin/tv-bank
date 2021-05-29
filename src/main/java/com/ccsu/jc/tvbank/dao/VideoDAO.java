package com.ccsu.jc.tvbank.dao;

import com.ccsu.jc.tvbank.domain.VideoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jc
 * @date 2021/5/8 16:33
 * @apiNote
 */
@Repository
public interface VideoDAO {

    void insert(VideoEntity videoEntity);

    /**
     * 将video 全部信息全部查询出来
     */
    List<VideoEntity> videolist(@Param("videoCategory") String videoCategory);


    /**
     * 随机在video 里面查询出5条记录
     */
    List<VideoEntity> videolistimit7();

    /**
     * 随机在video 里面查询出5条记录
     */
    List<VideoEntity> videolistimit5MAD();

    /**
     * 随机在video 里面查询出6条记录
     */
    List<VideoEntity> videolistimit6MAD();

    /**
     * 根据当前页查询出6条记录
     */
    List<VideoEntity> pageVideoList(@Param("videoCategory") String videoCategory, @Param("dangqianye") int dangqianye, @Param("meiyexianshiduoshaoge") int meiyexianshiduoshaoge);


    /**
     * 根据标记 查询出一共有多少条记录
     */
    int videoCount(String countvideo);

    /**
     * search by name
     */
    List<VideoEntity> searchByName(@Param("name") String name);

}
