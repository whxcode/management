package com.cn.scitc.repository;

import com.cn.scitc.entity.UserMessageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * excel
 * @author jswzj
 */
public interface ExcelRepository extends JpaRepository<UserMessageInfo,Long> {

   Iterable < UserMessageInfo >findByType(String type);


}
