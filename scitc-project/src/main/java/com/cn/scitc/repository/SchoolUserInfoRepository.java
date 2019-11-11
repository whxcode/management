package com.cn.scitc.repository;

import com.cn.scitc.entity.UserMessageInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * student info
 * @author jswzj
 */
public interface SchoolUserInfoRepository extends JpaRepository<UserMessageInfo,Long> {


    /**
     * ids 查询留校信息信息
     * @param ids
     * @return
     */
    List<UserMessageInfo> getByIds(Long ids);

    Page<UserMessageInfo>findAll(Pageable pageable);

    Page<UserMessageInfo> findByType(String type,Pageable pageable);

    /**
     * spring data jpa 模糊查询
     * @param pageable
     * @return
     */

    Page<UserMessageInfo> findByTypeLikeAndClassesLikeOrStuNameLike(String type,String classes,String stuName,Pageable pageable);



    /**
     * 统计留校 离校数目
     * @param type
     * @return
     */
    @Query(value = "select count (u.type) from UserMessageInfo u where u.type =?1")
    Long findByType(String type);

    @Query(value = "select  count(u.type) from UserMessageInfo u")
    Long findByType();

}
