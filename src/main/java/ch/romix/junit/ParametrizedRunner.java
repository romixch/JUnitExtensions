package ch.romix.junit;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class ParametrizedRunner extends BlockJUnit4ClassRunner {

	private List<FrameworkMethod> methods;

	public ParametrizedRunner(Class<?> klass) throws InitializationError {
		super(klass);
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
			testMethod.invokeExplosively(target, (Object[]) testMethod.getParameter().value());
		}
	}
}
