package ch.romix.junit;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParametrizedRunner.class)
public class TwoParameters {

	@Test
	@Parameters({ @Parameter({ "value 1" }), @Parameter({ "value 2" }) })
	public void test(String param) {
	}
}