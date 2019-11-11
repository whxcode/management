package com.cn.scitc.repository;

import com.cn.scitc.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * article
 * @author  jswzj
 */
public interface MessageReposiotry extends JpaRepository<Message,Long> {


    Message getById(Long id);

    /**
     * 文章显示 分页
     */
    Page<Message> findAll(Pageable pageable);


    /**
     * 文章列表显示
     * @param pageable
     * @return
     */
    @Query("SELECT  m.id,m.title  FROM Message m")
   Page<Message> findByIdAndTitle(Pageable pageable);





}
