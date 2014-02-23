package ch.romix.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParametrizedRunner.class)
public class OnyOneParameter {

	@Test
	@Parameters({ @Parameter({ "paramvalue" }) })
	public void test(String param) {
		assertEquals("paramvalue", param);
	}
}