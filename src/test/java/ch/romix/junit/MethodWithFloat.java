package ch.romix.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParametrizedRunner.class)
public class MethodWithFloat {
	@Test
	@Parameters({ @Parameter("42.00024") })
	public void methodWithParameter(float param) {
		assertEquals(42.00024f, param, 0.0000001f);
	}
}
