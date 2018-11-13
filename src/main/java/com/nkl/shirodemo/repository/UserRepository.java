package com.nkl.shirodemo.repository;

import com.nkl.shirodemo.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @Author: Sunshine
 * @Description:
 * @Date: Create in 2018/11/10 16:19
 */
@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    User findByName(String name);
}
