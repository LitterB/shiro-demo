package com.nkl.shirodemo.service;

import com.nkl.shirodemo.domain.User;
import org.springframework.stereotype.Service;

/**
 * @Author: Sunshine
 * @Description:
 * @Date: Create in 2018/11/10 16:23
 */
@Service
public interface LoginService {

    /**
     * 根据用户名获取用户信息
     * @param name
     * @return
     */
    User findByName(String name);
}
