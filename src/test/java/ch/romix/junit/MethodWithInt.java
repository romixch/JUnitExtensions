package ch.romix.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParametrizedRunner.class)
public class MethodWithInt {
	@Test
	@Parameters({ @Parameter("42") })
	public void methodWithIntParameter(int param) {
		assertEquals(42, param);
	}
}
