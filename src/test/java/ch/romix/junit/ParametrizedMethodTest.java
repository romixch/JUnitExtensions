package ch.romix.junit;

import static org.junit.Assert.*;

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

	@Test
	public void testEquals() throws Exception {
		Method method = ParametrizedMethodTest.class.getMethod("testEquals");
		Parameter parameter = new MyParmeterForTesting(new String[] { "param" });
		Parameter otherParameter = new MyParmeterForTesting(new String[] { "otherParam" });
		ParametrizedMethod parametrizedMethodOne = new ParametrizedMethod(method, parameter);
		ParametrizedMethod parametrizedMethodTwo = new ParametrizedMethod(method, parameter);
		ParametrizedMethod otherParametrizedMethod = new ParametrizedMethod(method, otherParameter);
		assertTrue(parametrizedMethodOne.equals(parametrizedMethodTwo));
		assertTrue(parametrizedMethodTwo.equals(parametrizedMethodOne));
		assertFalse(parametrizedMethodOne.equals(otherParametrizedMethod));
		assertFalse(parametrizedMethodOne.equals(new Object()));
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
