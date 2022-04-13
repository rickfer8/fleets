package com.fleets.seguros.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class HashUtilTest {
	
	@Test
	public void getSecureHashText() {
		String hash = HashUtil.getSecureHash("1234");
		System.out.println(hash);
		
		assertThat(hash.length()).isEqualTo(64);
	}

}
