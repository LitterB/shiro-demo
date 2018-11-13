package com.nkl.shirodemo.config;

import com.nkl.shirodemo.domain.Permission;
import com.nkl.shirodemo.domain.Role;
import com.nkl.shirodemo.domain.User;
import com.nkl.shirodemo.service.impl.LoginServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @Author: Sunshine
 * @Description:
 * @Date: Create in 2018/11/10 15:26
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private LoginServiceImpl loginService;
    /**
     * 角色权限和对应权限添加
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登陆用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名查询用户
        User user = loginService.findByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role: user.getRoles()){
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permission permission: role.getPermissions()){
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null){
            return null;
        }
        String name = authenticationToken.getPrincipal().toString();
        //获取用户信息
        User user = loginService.findByName(name);
        if (user == null){
            return null;
        }else{
            //验证authenticationToken和simpleAuthenticationInfo的信息
            return new SimpleAuthenticationInfo(name, user.getPassword().toString(), getName());
        }
    }
}
