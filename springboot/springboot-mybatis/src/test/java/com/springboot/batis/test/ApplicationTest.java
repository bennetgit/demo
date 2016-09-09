package com.springboot.batis.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.batis.application.Application;
import com.springboot.batis.domain.TestUser;
import com.springboot.batis.mapper.TestUserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class ApplicationTest {

	@Resource
	TestUserMapper userMapper;

	@Test
	@Rollback
	public void insertdd() {
		userMapper.insert("hello", 1);
		TestUser user = userMapper.findByName("hello");
		Assert.assertEquals(1, user.getAge());
	}

    private static native int readFromPage()
    /*-{
        if($wnd.ozonelayerConnectionGuardTimeout) {
            return $wnd.ozonelayerConnectionGuardTimeout;
        }
        return -1;
    }-*/;

    public void responseHandled() {
        // expectingResponse = false;
        // t.cancel();
        // if (isShowing()) {
        // hide();
        // }
    }
}
