package com.lineng.service;

import com.lineng.mapper.PermissionMapper;
import com.lineng.mapper.SystemUserMapper;
import com.lineng.model.Permission;
import com.lineng.model.SystemRole;
import com.lineng.vo.SystemUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by cailineng on 2017/12/30.
 */
public class CustomUserDetailsService implements UserDetailsService {

    //@Autowired
    //UserRepository userRepository;
    @Resource
    private SystemUserMapper systemUserMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map map = new HashMap();
        map.put("userName",username);
        SystemUserVo  systemUserVo = systemUserMapper.getSystemUserByUserInfo(map);
      //  User user = userRepository.findByLoginName(username);
        if(systemUserVo == null){
            throw new UsernameNotFoundException("not found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

      /*  for(SystemRole systemRole : systemUserVo.getSystemRoleList()){
            authorities.add(new SimpleGrantedAuthority(systemRole.getRoleName()));
        }
        User user =new org.springframework.security.core.userdetails.User(systemUserVo.getUserName(),
                systemUserVo.getPsw(), authorities);
        return user;*/

        List<Permission> permissions = permissionMapper.findByAdminUserId(systemUserVo.getId().intValue());
        List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
        for (Permission permission : permissions) {
            if (permission != null && permission.getName()!=null) {

                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                grantedAuthorities.add(grantedAuthority);
            }
        }


        return new User(systemUserVo.getUserName(), systemUserVo.getPsw(), grantedAuthorities);
    }

   // Collection<? extends GrantedAuthority> getAuthorities();



}

