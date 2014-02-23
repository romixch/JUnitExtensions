JUnitExtensions
===============

This extensions contain currently one additional TestRunner: ParametrizedRunner. It helps you writing tests that are executed with different parameters.
Write your tests like this:

@RunWith(ParametrizedRunner.class)
public class ExampleTest {
	
	@Test
	@Parameters({ 
	  @Parameter({ "green" }), 
	  @Parameter({ "red" })
    })
	public void testColors(String color) {
		System.out.println(color);
	}
}

The method testColors is then executed twice. Once with the color "green" and once with "red".