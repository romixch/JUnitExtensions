package ch.romix.junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

public class ParametrizedRunnerTest {

	private RunNotifier runNotifier;
	private MyRunListener runListener;

	@Before
	public void before() {
		runNotifier = new RunNotifier();
		runListener = new MyRunListener();
		runNotifier.addFirstListener(runListener);
	}

	@Test
	public void testSuccessWithOnlyOneParameter() throws Exception {
		ParametrizedRunner runner = new ParametrizedRunner(OnyOneParameter.class);
		assertEquals(1, runner.testCount());
		runner.run(runNotifier);
		assertNoFailures();
		assertFinishedCount(1);
		assertMethodName(0, "test_paramvalue");
	}

	@Test
	public void testSuccessWithTwoParameterSets() throws Exception {
		ParametrizedRunner runner = new ParametrizedRunner(TwoParameters.class);
		assertEquals(2, runner.testCount());
		runner.run(runNotifier);
		assertNoFailures();
		assertFinishedCount(2);
		assertMethodName(0, "test_value 1");
		assertMethodName(1, "test_value 2");
	}

	@Test
	public void testFailureWithOneParameter() throws Exception {
		ParametrizedRunner runner = new ParametrizedRunner(FailingOnlyOneParameter.class);
		assertEquals(1, runner.testCount());
		runner.run(runNotifier);
		assertFailures(1);
		assertMethodName(0, "test_value 1");
	}

	@Test
	public void testExecutionOfTestMethodWithIntParameter() throws Exception {
		assertMethodIsExecutedSuccessfully(MethodWithInt.class);
	}

	@Test
	public void testExecutionOfTestMethodWithBooleanParameter() throws Exception {
		assertMethodIsExecutedSuccessfully(MethodWithBoolean.class);
	}

	@Test
	public void testExecutionOfTestMethodWithLongParameter() throws Exception {
		assertMethodIsExecutedSuccessfully(MethodWithLong.class);
	}

	@Test
	public void testExecutionOfTestMethodWithByteParameter() throws Exception {
		assertMethodIsExecutedSuccessfully(MethodWithByte.class);
	}

	private void assertMethodIsExecutedSuccessfully(Class<?> clazz) throws InitializationError {
		ParametrizedRunner runner = new ParametrizedRunner(clazz);
		assertEquals(1, runner.testCount());
		runner.run(runNotifier);
		assertNoFailures();
		assertFinishedCount(1);
	}

	private void assertNoFailures() {
		assertFailures(0);
	}

	private void assertFailures(int expectedCount) {
		assertEquals(expectedCount, runListener.failures.size());
	}

	private void assertMethodName(int methodIndex, String expectedName) {
		Description desc = runListener.finishedDescriptions.get(methodIndex);
		assertEquals(expectedName, desc.getMethodName());
	}

	private void assertFinishedCount(int count) {
		assertEquals(count, runListener.finishedDescriptions.size());
	}

	private class MyRunListener extends RunListener {
		private List<Failure> failures = new ArrayList<>();
		private List<Description> finishedDescriptions = new ArrayList<>();

		@Override
		public void testAssumptionFailure(Failure failure) {
			failures.add(failure);
		}

		@Override
		public void testFailure(Failure failure) throws Exception {
			failures.add(failure);
		}

		@Override
		public void testFinished(Description description) throws Exception {
			finishedDescriptions.add(description);
		}
	}
}