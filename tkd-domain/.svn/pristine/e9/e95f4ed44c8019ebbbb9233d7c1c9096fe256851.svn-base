package com.lc.zy.ball.domain;

import java.util.UUID;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-test.xml" })
public class AbstractTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	public String uuid(){
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}
	
}