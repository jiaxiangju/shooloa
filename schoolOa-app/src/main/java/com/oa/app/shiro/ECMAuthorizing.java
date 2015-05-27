/**
 * 企业内容管理平台
 * com.pcitc.ecm.app.shiro
 * ECMAuthorizing.java
 * 
 * 2014年7月29日-下午3:50:02
 *  2014石化盈科信息技术有限责任公司-版权所有
 * 
 */
package com.oa.app.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.oa.app.service.user.UserServiceImpl;
import com.oa.common.entity.User;
import com.oa.common.util.SpringContextHolder;


/**
 * 
 * 类名称    ：ECMAuthorizing
 * 类描述    ：(简单描述这个类)
 * 创建人    ：张黎江
 * 创建时间：2014年7月29日 下午3:50:02
 * 修改人    ：张黎江
 * 修改时间：2014年7月29日 下午3:50:02
 * 修改备注：
 * 版本        ：@version 1.0.0
 * 
 */
public class ECMAuthorizing extends AuthorizingRealm{
    /** 
     * 权限认证 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {  
        //获取登录时输入的用户名  
        String loginName=(String) principalCollection.fromRealm(getName()).iterator().next();  
        UserServiceImpl userService=(UserServiceImpl)SpringContextHolder.getApplicationContext().getBean("userService");
        //到数据库查是否有此对象  
        User user=userService.queryUserByLoginName(loginName);
        if(user!=null){  
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）  
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            return info;  
        }  
        return null;  
    }  
  

	/* 登录认证
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException {
		//UsernamePasswordToken对象用来存放提交的登录信息  
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;  
        UserServiceImpl userService=(UserServiceImpl)SpringContextHolder.getApplicationContext().getBean("userService");  
        //查出是否有此用户  
        User user=userService.queryUserByLoginName(token.getUsername());  
        if(user!=null){  
            //若存在，将此用户存放到登录认证info中  
            return new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), getName());  
        }
        return null;  
	}  
  
}
