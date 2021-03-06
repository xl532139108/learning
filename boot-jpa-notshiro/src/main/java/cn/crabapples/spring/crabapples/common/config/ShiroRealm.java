package cn.crabapples.spring.crabapples.common.config;

import cn.crabapples.system.entity.SysUser;
import cn.crabapples.system.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


/**
 * TODO shiro配置
 *
 * @author Mr.He
 * 2020/3/5 1:03
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name 29404
 */
@Configuration
public class ShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
    private UserService userService;

    public ShiroRealm(UserService userService) {
        this.userService = userService;
    }

    /**
     * shiro授权调用的方法
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * shiro认证调用的方法
     *
     * @param authenticationToken shiro认证的token
     * @return 用户认证信息
     * @throws AuthenticationException IncorrectCredentialsException密码错误 UnknownAccountException用户名错误
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        logger.debug("开始对:[{}]执行shiro认证", username);
        SysUser user = userService.findByUsername(username).orElse(null);
        if (null == user) {
            throw new UnknownAccountException("用户不存在");
        }
        String password = user.getPassword();
        logger.debug("用户:[{}]shiro认证结束", username);
        return new SimpleAuthenticationInfo(user, password, this.getName());
    }
}
