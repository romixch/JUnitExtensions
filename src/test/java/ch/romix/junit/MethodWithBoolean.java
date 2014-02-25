package ch.romix.junit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParametrizedRunner.class)
public class MethodWithBoolean {
	@Test
	@Parameters({ @Parameter("true") })
	public void methodWithParameter(boolean param) {
		assertTrue(param);
	}
}
