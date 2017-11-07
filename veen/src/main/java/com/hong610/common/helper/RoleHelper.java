package com.hong610.common.helper;

import com.hong610.domain.Resources;
import com.hong610.domain.Role;
import com.hong610.security.entity.MyGrantedAuthority;
import com.hong610.security.entity.UserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * 角色帮助类
 * Created by Hong on 2016/12/16.
 */
public class RoleHelper {

    public static final String getRoleOne(){
        UserDetail user = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Role> list = user.getRoles();
        return list.size() == 0 ? "" : list.get(0).getNameRemark();
    }

    public static final String getRole(){
        UserDetail user = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Role> list =  user.getRoles();
        return toString(list);
    }

    private static final String toString(List<Role> list){
        StringBuffer buffer = new StringBuffer();
        for (Role g : list){
            buffer.append(",");
            buffer.append(g.getNameRemark());
        }
        if(buffer.length() > 0){
            buffer.deleteCharAt(0);
        }
        return buffer.toString();
    }

}
