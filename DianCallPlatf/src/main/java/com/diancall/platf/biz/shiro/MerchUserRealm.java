package com.diancall.platf.biz.shiro;

import com.diancall.platf.biz.entity.merch.Merchuser;
import com.diancall.platf.biz.service.merch.MerchUserServiceI;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description: shiro 自定义realm
 * User: Tiki
 * Date: 2017-10-30
 * Time: 17:40
 */
@Component("merchUserRealm")
public class MerchUserRealm extends AuthorizingRealm {

    @Autowired
    private MerchUserServiceI merchUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo sa = new SimpleAuthorizationInfo();
        sa.setStringPermissions(merchUserService.findPermissionWithUserName(userName));
        return sa;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upt = (UsernamePasswordToken) authenticationToken;
        String userName = upt.getUsername();
        Merchuser mu = merchUserService.findMerchuserByName(userName);
        if(null == mu){
            throw new UnknownAccountException();
        }
        SimpleAuthenticationInfo sa = new SimpleAuthenticationInfo(userName,mu.getAccount(),mu.getPassword());
        return sa;
    }
}
