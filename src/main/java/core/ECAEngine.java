package core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO Add some meaningful class description...
 */
public class ECAEngine {

	private static final Logger logger = LoggerFactory.getLogger(GenericInvocationHandler.class);

	private static class GenericInvocationHandler<T> implements InvocationHandler {

		private final T t;

		public GenericInvocationHandler(T t) {
			Objects.requireNonNull(t);

			this.t = t;
			checkConsistency();
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Method tMethod = t.getClass().getMethod(method.getName(), method.getParameterTypes());
			Event event = tMethod.getAnnotation(Event.class);
			Object result = null;
			long elapsed = System.nanoTime();
			try {
				result = method.invoke(t, args);
				if (null != event) {
					ECARule rule = event.value().newInstance();
					if (rule.check(args)) {
						rule.execute(args);
					}
				}
			} catch (InvocationTargetException anExc) {
				throw anExc.getCause();
			} finally {
				if (null != event) {
					logger.info("'{}({})' triggered '{}' and completed in {}ms",
								tMethod.getName(),
								Arrays.asList(tMethod.getParameterTypes()).stream()
										.map(Class::getSimpleName)
										.collect(Collectors.joining(", ")),
								event.value().newInstance().getClass().getSimpleName(),
								((int) (1_000 * (System.nanoTime() - elapsed) / 1_000_000_000.0)) / 1_000.0);
				}
			}
			return result;
		}

		private void checkConsistency() {
			assert tIsNotNull() : "'t' is not valid (null)";
		}

		private boolean tIsNotNull() {
			return (null != t);
		}

	}

	@SuppressWarnings("unchecked")
	public static <T> T enable(final T t) {
		return (T) Proxy.newProxyInstance(
				t.getClass().getClassLoader(),
				t.getClass().getInterfaces(),
				new GenericInvocationHandler(t));
	}

}
