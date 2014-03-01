package ch.romix.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParametrizedRunner.class)
public class MethodWithChar {
	@Test
	@Parameters({ @Parameter("a") })
	public void methodWithParameter(char param) {
		assertEquals('a', param);
	}
}
