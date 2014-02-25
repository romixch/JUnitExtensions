package ch.romix.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParametrizedRunner.class)
public class MethodWithLong {
	@Test
	@Parameters({ @Parameter("42000000") })
	public void methodWithParameter(long param) {
		assertEquals(42000000, param);
	}
}
