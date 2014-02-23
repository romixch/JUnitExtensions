package ch.romix.junit;

import static org.junit.Assert.assertEquals;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Test;

public class ParametrizedMethodTest {

	@Test
	public void testCorrectName() throws Exception {
		Method method = ParametrizedMethodTest.class.getMethod("testCorrectName");
		Parameter parameter = new MyParmeterForTesting(new String[] { "variableName", "Value" });
		ParametrizedMethod parametrizedMethod = new ParametrizedMethod(method, parameter);
		assertEquals("testCorrectName_variableName_Value", parametrizedMethod.getName());
	}

	@Test
	public void testNameWithSeveralParameters() throws Exception {
		Method method = ParametrizedMethodTest.class.getMethod("testNameWithSeveralParameters");
		Parameter parameter = new MyParmeterForTesting(new String[] { "variableName", "Value1", "secondVariable", "SecondValue" });
		ParametrizedMethod parametrizedMethod = new ParametrizedMethod(method, parameter);
		assertEquals("testNameWithSeveralParameters_variableName_Value1_secondVariable_SecondValue", parametrizedMethod.getName());
	}

	class MyParmeterForTesting implements Parameter {

		private String[] params;

		public MyParmeterForTesting(String[] params) {
			this.params = params;
		}

		@Override
		public Class<? extends Annotation> annotationType() {
			return null;
		}

		@Override
		public String[] value() {
			return params;
		}
	}
}
