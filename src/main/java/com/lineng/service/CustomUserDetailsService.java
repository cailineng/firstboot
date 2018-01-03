package com.lineng.service;

import com.lineng.mapper.SystemUserMapper;
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

        for(SystemRole systemRole : systemUserVo.getSystemRoleList()){
            authorities.add(new SimpleGrantedAuthority(systemRole.getRoleName()));
        }
        User user =new org.springframework.security.core.userdetails.User(systemUserVo.getUserName(),
                systemUserVo.getPsw(), authorities);
        return user;
    }

   // Collection<? extends GrantedAuthority> getAuthorities();



}

