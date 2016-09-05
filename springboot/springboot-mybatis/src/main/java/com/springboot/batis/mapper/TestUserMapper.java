package com.springboot.batis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.springboot.batis.domain.TestUser;

@Mapper
public interface TestUserMapper {

	@Select("select * from test_user where name = #{name}")
	TestUser findByName(@Param("name") String name);

	
	@Insert("insert into test_user(name, age) values(#{name}, #{age})")
	int insert(@Param("name") String name, @Param("age") int age);
}
