package com.nicchagil.test.util.mapperbaseservice;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicchagil.orm.mapper.UserMapper;
import com.nicchagil.util.mapperbaseservice.CommonUserService;
import com.nicchagil.util.test.BaseSpringBootTest;

public class CommonUserServiceTest extends BaseSpringBootTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommonUserService commonUserService;

	@Test
	public void getMapperTest() {
		UserMapper userMapper = this.commonUserService.getMapper();
		this.logger.info("userMapper : {}", userMapper);
	}
	
}
