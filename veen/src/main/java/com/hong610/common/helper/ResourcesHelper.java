package com.hong610.common.helper;

import com.hong610.common.enums.types.ResourcesType;
import com.hong610.domain.Resources;
import com.hong610.security.entity.MyGrantedAuthority;
import com.hong610.security.entity.UserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * 资源帮助类
 * Created by Hong on 2016/12/14.
 */
public class ResourcesHelper {

    /**
     * 获取当前页面的资源信息
     * @param url 路径
     * @return [0]父级名称 [1]当前名称
     */
    public static final String[] info(String url){
        //这里建议加入缓存
        String[] strs = new String[]{"", ""};
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetail) {
            UserDetail user = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<MyGrantedAuthority> list = (List<MyGrantedAuthority>) user.getAuthorities();
            long parentId = 0;
            for(MyGrantedAuthority resource : list){
                if(url.startsWith(resource.getResource().getUrl())){
                    strs[1] = resource.getResource().getName();
                    parentId = resource.getResource().getParentId();
                    break;
                }
            }
            for(MyGrantedAuthority resource : list){
                if(resource.getResource().getId() == parentId){
                    strs[0] = resource.getResource().getName();
                    break;
                }
            }
        }
        return strs;
    }

    /**
     * 是否存在资源
     */
    public static final boolean isResources(List<Resources> userResources, String url){
        if(userResources == null || userResources.size() == 0 || url == null)
            return false;
        for(Resources resources : userResources){
            if(resources.getUrl().equals(url)){
                return true;
            }
        }
        return false;
    }

    /**
     * 用户是否存在这个资源访问权限
     */
    public static final boolean exists(String url){
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetail) {
            UserDetail user = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Resources> list = user.getResources();
            for(Resources resource : list){
                if(resource.getType() == ResourcesType.LINK.getValue() && resource.getUrl().endsWith(url)){
                    return true;
                }
            }
        }
        return false;
    }
}
