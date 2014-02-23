package ch.romix.junit;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParametrizedRunner.class)
public class FailingOnlyOneParameter {

	@Test
	@Parameters({ @Parameter({ "value 1" }) })
	public void test(String param) {
		fail();
	}
}