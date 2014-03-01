package ch.romix.junit;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParametrizedRunner.class)
public class MethodWithObjectTypeDate {
	@Test
	@Parameters({ @Parameter("15.04.80 08:20") })
	public void methodWithParameter(Date param) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1980, 3, 15, 8, 20, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date expected = calendar.getTime();
		assertEquals(expected, param);
	}
}
