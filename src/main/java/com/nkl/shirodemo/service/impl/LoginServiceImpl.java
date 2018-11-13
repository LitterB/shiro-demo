package com.nkl.shirodemo.service.impl;

import com.nkl.shirodemo.domain.Permission;
import com.nkl.shirodemo.domain.Role;
import com.nkl.shirodemo.domain.User;
import com.nkl.shirodemo.repository.RoleRepository;
import com.nkl.shirodemo.repository.UserRepository;
import com.nkl.shirodemo.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author: Sunshine
 * @Description:
 * @Date: Create in 2018/11/10 16:24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;

    /**
     * 添加用户
     */
    public User addUser(Map<String, Object> map) {
        User user = new User();
        user.setName(map.get("username").toString());
        user.setPassword(Integer.valueOf(map.get("password").toString()));
        userRepository.save(user);
        return  user;
    }

    public Role addRole(Map<String, Object> map) {
        Optional<User> user = userRepository.findById(Long.valueOf(map.get("userId").toString()));
        Role role = new Role();
        role.setRoleName(map.get("roleName").toString());
        role.setUser(user.get());
        Permission permission1 = new Permission();
        permission1.setPermission("create");
        permission1.setRole(role);
        Permission permission2 = new Permission();
        permission2.setPermission("update");
        permission2.setRole(role);
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        roleRepository.save(role);
        return role;
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
}
