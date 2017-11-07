package com.hong610.common.helper;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 自动生成映射工具类
 * Created by Hong on 2016/12/13.
 */
public class AutoGeneratorHelper {

	/**
	 * 执行
	 */
	public static void main( String[] args ) {
		AutoGenerator autoGenerator = new AutoGenerator();

		PackageConfig pc = new PackageConfig();
		pc.setParent("com.hong610");
		pc.setModuleName(null);
		pc.setServiceImpl("dao.impl");
		pc.setService("dao");
		pc.setMapper("mapper");
		pc.setXml("mapper.xml");
		pc.setEntity("domain");
		pc.setController("controller");

		DataSourceConfig bc = new DataSourceConfig();
		bc.setDbType(DbType.MYSQL);
		bc.setDriverName("com.mysql.jdbc.Driver");
		bc.setUsername("root");
		bc.setPassword("123456");
		bc.setUrl("jdbc:mysql://localhost:3306/tb_security?useUnicode=true&characterEncoding=utf8");

		StrategyConfig sc = new StrategyConfig();
		sc.setFieldNaming(NamingStrategy.underline_to_camel);// 字段名生成策略
		sc.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		//sc.setInclude(new String[] { "user" }); // 需要生成的表
		sc.setSuperEntityClass("com.hong610.domain.base.BaseEntity");//Base Entity
		sc.setExclude(new String[]{"id", "modified_time", "status", "is_delete"});//排除
		sc.setSuperControllerClass("com.hong610.controller.base.BaseController");//Base COntroller


		TemplateConfig tc = new TemplateConfig();
		tc.setEntity("/com/hong610/common/helper/templates/entity.java.vm");
		tc.setXml("/com/hong610/common/helper/templates/mapper.xml.vm");
		tc.setMapper("/com/hong610/common/helper/templates/mapper.java.vm");
		tc.setService("/com/hong610/common/helper/templates/service.java.vm");
		tc.setServiceImpl("/com/hong610/common/helper/templates/serviceImpl.java.vm");
		tc.setController("/com/hong610/common/helper/templates/controller.java.vm");

		GlobalConfig gc = new GlobalConfig();
		gc.setFileOverride(true);
		gc.setActiveRecord(true);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(false);// XML ResultMap
		gc.setBaseColumnList(true);// XML columList
		gc.setAuthor("Hong");
		gc.setServiceName("I%sDao");//自定义文件命名，注意 %s 会自动填充表实体属性！
		gc.setServiceImplName("%sDaoImpl");
		gc.setOutputDir("E:\\java");//C:\Users\Hong\git\SpringSecurity\src\main\java

		// 注入自定义配置，可以在 VM 中使用 cfg.xxx 设置的值
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("time", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
				this.setMap(map);
			}
		};
		autoGenerator.setCfg(cfg);


		ConfigBuilder config = new ConfigBuilder(pc, bc, sc, tc, gc);
		autoGenerator.setConfig(config);
		autoGenerator.execute();
	}

}
