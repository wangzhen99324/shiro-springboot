package com.angzk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.angzk.dao.model.SysUser;
import com.angzk.service.SysUserRestService;
import com.kuajx.common.tools.EncryptUtil;

import net.sf.json.JSONObject;

/**
 * Springboot Test 启动类
 * 
 * @author Angzk
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { VueAdminBootApplication.class }) // 指定启动类
public class VueAdminBootApplicationTests {

	@Autowired
	private SysUserRestService sysUserRestService;

	@Test
	public void test2() throws Exception {
		SysUser sysUser = sysUserRestService.querySysUserInfo(1L);
		System.err.println(JSONObject.fromObject(sysUser).toString());
	}

	@Test
	public void test3() throws Exception {
		System.err.println(EncryptUtil.mD5("123456"));
	}

}
