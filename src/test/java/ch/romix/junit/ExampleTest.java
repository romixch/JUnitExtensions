package ch.romix.junit;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ParametrizedRunner.class)
public class ExampleTest {
	@Test
	@Parameters({ @Parameter({ "green" }), @Parameter({ "red" }) })
	public void testColors(String color) {
		System.out.println(color);
	}
}