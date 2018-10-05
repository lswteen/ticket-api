package travel.config.exception;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.AsyncTaskExecutor;

/**
 * 
 * travel.config.exception
 *    |_ HandlingAsyncTaskExecutor.java
 * @brief
 * @author 어드민/박종성
 * @version 1.0
 * @date 생성 : 2014. 12. 15.
 * @date 최종수정 : 2014. 12. 15.
 */
public class HandlingAsyncTaskExecutor implements AsyncTaskExecutor {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private AsyncTaskExecutor executor;

	/**
	 * 
	 * @param executor
	 */
	public HandlingAsyncTaskExecutor(AsyncTaskExecutor executor) {
		this.executor = executor;
	}

	@Override
	public void execute(Runnable task) {
		executor.execute(task);
	}

	@Override
	public void execute(Runnable task, long startTimeout) {
		executor.execute(createWrappedRunnable(task), startTimeout);
	}

	@Override
	public Future<?> submit(Runnable task) {
		return executor.submit(createWrappedRunnable(task));
	}

	@Override
	public <T> Future<T> submit(final Callable<T> task) {
		return executor.submit(createCallable(task));
	}

	private <T> Callable<T> createCallable(final Callable<T> task) {
		return new Callable<T>() {
			@Override
			public T call() throws Exception {
				try {
					return task.call();
				} catch (Exception e) {
					handle(e);
					throw e;
				}
			}
		};
	}

	private Runnable createWrappedRunnable(final Runnable task) {
		return new Runnable() {
			@Override
			public void run() {
				try {
					task.run();
				} catch (Exception e) {
					handle(e);
				}
			}
		};
	}

	private void handle(Exception ex) {
		LOGGER.error("Error during @Async execution: "
				+ ExceptionUtils.getStackTrace(ex));

	}
}