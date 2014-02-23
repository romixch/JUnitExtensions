package ch.romix.junit;

import java.lang.reflect.Method;

import org.junit.runners.model.FrameworkMethod;

public class ParametrizedMethod extends FrameworkMethod {

	private Parameter parameter;

	public ParametrizedMethod(Method method, Parameter parameter) {
		super(method);
		this.parameter = parameter;
	}

	@Override
	public String getName() {
		String name = super.getMethod().getName();
		for (String param : parameter.value()) {
			name = name + '_' + param;
		}
		return name;
	}

	public Parameter getParameter() {
		return parameter;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + parameter.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ParametrizedMethod) {
			return super.equals(obj) && parameter.equals(ParametrizedMethod.class.cast(obj).getParameter());
		} else {
			return false;
		}
	}
}
