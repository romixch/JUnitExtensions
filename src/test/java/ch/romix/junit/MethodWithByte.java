package ch.romix.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParametrizedRunner.class)
public class MethodWithByte {
	@Test
	@Parameters({ @Parameter("42") })
	public void methodWithParameter(byte param) {
		assertEquals(42, param);
	}
}
