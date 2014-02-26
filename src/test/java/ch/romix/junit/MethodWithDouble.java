package ch.romix.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParametrizedRunner.class)
public class MethodWithDouble {
	@Test
	@Parameters({ @Parameter("42.00000024") })
	public void methodWithParameter(double param) {
		assertEquals(42.00000024, param, 0.0000001);
	}
}
