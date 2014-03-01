package ch.romix.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParametrizedRunner.class)
public class MethodWithShort {
	@Test
	@Parameters({ @Parameter("300") })
	public void methodWithParameter(short param) {
		assertEquals(300, param);
	}
}
