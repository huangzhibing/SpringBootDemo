package com.example.demo.shiro;

import com.example.demo.basedata.user.User;
import com.example.demo.basedata.user.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    UserMapper userMapper;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName =(String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        try {
            String role = userMapper.getUserByName(userName).getRole();
            authorizationInfo.addRole(role);
        }catch (Exception e){
            e.printStackTrace();
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        User user = userMapper.getUserByName((String)token.getPrincipal());
        if(user==null){
            throw new UnknownAccountException("用户名不存在!");
        }
        return new SimpleAuthenticationInfo(user.getName(),user.getPassword(),getName());
    }

    @Override
    public boolean hasRole(PrincipalCollection principals, String roleIdentifier) {
        String userName = (String)principals.getPrimaryPrincipal();
        return super.hasRole(principals,roleIdentifier)||"admin".equals(userName);
    }
}
