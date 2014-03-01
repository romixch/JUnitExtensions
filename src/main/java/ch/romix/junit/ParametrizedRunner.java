package ch.romix.junit;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class ParametrizedRunner extends BlockJUnit4ClassRunner {

	private List<FrameworkMethod> methods;
	private Map<Class<?>, TypeConverter> converters;

	public ParametrizedRunner(Class<?> klass) throws InitializationError {
		super(klass);
		converters = new HashMap<>();
		converters.put(Date.class, new DateConverter());
	}

	@Override
	protected List<FrameworkMethod> computeTestMethods() {
		List<ParametrizedMethod> moreMethods = new ArrayList<>();
		if (methods == null) {
			methods = super.computeTestMethods();
			Iterator<FrameworkMethod> it = methods.iterator();
			while (it.hasNext()) {
				FrameworkMethod frameworkMethod = it.next();
				Parameters parameters = frameworkMethod.getAnnotation(Parameters.class);
				if (parameters != null) {
					it.remove();
					for (Parameter parameter : parameters.value()) {
						ParametrizedMethod methodWithParam = new ParametrizedMethod(frameworkMethod.getMethod(), parameter);
						moreMethods.add(methodWithParam);
					}
				}
			}
			methods.addAll(moreMethods);
		}
		return methods;
	}

	@Override
	protected void validatePublicVoidNoArgMethods(Class<? extends Annotation> annotation, boolean isStatic, List<Throwable> errors) {
		disableNoArgsValidationOfAnnotatedParameterMethods(annotation, isStatic, errors);
	}

	private void disableNoArgsValidationOfAnnotatedParameterMethods(Class<? extends Annotation> annotation, boolean isStatic,
			List<Throwable> errors) {
		if (annotation.isAssignableFrom(Test.class)) {
			return;
		}
		super.validatePublicVoidNoArgMethods(annotation, isStatic, errors);
	}

	@Override
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		if (method instanceof ParametrizedMethod) {
			return new InvokeMethodWithParameters(ParametrizedMethod.class.cast(method), test);
		} else {
			return super.methodInvoker(method, test);
		}
	}

	private final class InvokeMethodWithParameters extends Statement {
		private ParametrizedMethod testMethod;
		private Object target;

		private InvokeMethodWithParameters(ParametrizedMethod testMethod, Object target) {
			this.testMethod = testMethod;
			this.target = target;
		}

		@Override
		public void evaluate() throws Throwable {
			Class<?>[] parameterTypes = testMethod.getMethod().getParameterTypes();
			Object[] args = new Object[parameterTypes.length];
			for (int i = 0; i < parameterTypes.length; i++) {
				String paramValue = testMethod.getParameter().value()[i];
				Class<?> paramClass = parameterTypes[0];
				args[i] = convertParameterToMethodArguments(paramClass, paramValue);
			}
			testMethod.invokeExplosively(target, args);
		}

		private Object convertParameterToMethodArguments(Class<?> clazz, String paramValue) {
			if (clazz.isPrimitive()) {
				String primitiveTypeName = clazz.getName();
				return convertPrimitiveType(paramValue, primitiveTypeName);
			} else {
				return convertObjectType(paramValue, clazz);
			}
		}

		private Object convertPrimitiveType(String paramValue, String primitiveTypeName) {
			switch (primitiveTypeName) {
			case "int":
				return Integer.parseInt(paramValue);
			case "boolean":
				return Boolean.parseBoolean(paramValue);
			case "long":
				return Long.parseLong(paramValue);
			case "byte":
				return Byte.parseByte(paramValue);
			case "double":
				return Double.parseDouble(paramValue);
			case "float":
				return Float.parseFloat(paramValue);
			case "short":
				return Short.parseShort(paramValue);
			case "char":
				return paramValue.toCharArray()[0];
			default:
				return null;
			}
		}
	}

	public Object convertObjectType(String paramValue, Class<?> type) {
		if (String.class.isAssignableFrom(type)) {
			return paramValue;
		} else {
			TypeConverter converter = converters.get(type);
			if (converter == null) {
				String message = String.format("Could not find a converter for your parameter of type %s.", type);
				throw new IllegalArgumentException(message);
			}
			return converter.convert(paramValue);
		}
	}
}
