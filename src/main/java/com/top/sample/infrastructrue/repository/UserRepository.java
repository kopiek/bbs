package com.top.sample.infrastructrue.repository;

import com.top.sample.infrastructrue.po.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author wangzhikang
 */
public interface UserRepository extends JpaRepository<UserPo,Long> {

    UserPo getByUserName(String userName);
}
