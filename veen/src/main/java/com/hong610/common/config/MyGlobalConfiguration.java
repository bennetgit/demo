package com.hong610.common.config;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;

/**
 * Created by Hong on 2017/1/12.
 */
public class MyGlobalConfiguration extends GlobalConfiguration {
    public MyGlobalConfiguration(){
        super(new MySqlInjector());
    }
}
