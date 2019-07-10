package com.demo.realm;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyRealm extends AuthorizingRealm {

    //执行授权方法
    //认证方法中已经获取到角色和权限 先获取到用户 , 然后获取用户里面的角色和权限, 然后添加到AuthorizationInfo中
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");
        return info;
    }

    //执行认证  执行认证之后获取相关的角色放到集合listRole中 , 然后把listRole设置到User中用户拥有哪些角色
    //通过userid查询roleid 通过roleid查询出权限放到集合listPermission中 然后设置到User 用户拥有哪些权限
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("=======进行认证 ==============");
        //1>从token中获取身份信息 , 用户输入的
        String username = (String) token.getPrincipal();
        //2>根据用户名到数据库中取出用户信息  如果查询不到 返回null
        String password = "1620d20433da92e2523928e351e90f97";//假如从数据库中获取密码为1111
        //返回认证信息   password从数据库中获取到的是加过盐的 , 密码对应的盐值:ByteSource.Util.bytes("siggy") , 盐值一般数据库获取
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username,
                password, ByteSource.Util.bytes("siggy"), this.getName());
        return simpleAuthenticationInfo;
    }
    /**
     * 清除权限缓存
     * 使用方法：在需要清除用户权限的地方注入 ShiroRealm,
     * 然后调用其clearCache方法。
     */
    public void clearCache() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        System.out.println("11111111111112222333333");
        super.clearCache(principals);
    }
}

