package eu.sqooss.service.scheduler;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

import eu.sqooss.core.AlitheiaCore;
import eu.sqooss.service.db.DBService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Job.class)
public class JobExecutorTest {

	AlitheiaCore alitheiaCore = mock(AlitheiaCore.class);
	DBService dbService = mock(DBService.class);
	Job job = mock(Job.class);

	@Before
	public void setUp() {
		AlitheiaCore.setInstance(alitheiaCore);
		when(alitheiaCore.getDBService()).thenReturn(dbService);
	}

	@Test
	public void testExecuteRunning() throws Exception {
		when(dbService.isDBSessionActive()).thenReturn(false);
		when(job.state()).thenReturn(Job.State.Running);

		JobExecutor jobExecutor = new JobExecutor();
		long executionTime = jobExecutor.execute(job);

		verify(job, times(1)).setState(Job.State.Running);
		verify(job, times(1)).restart();
		verify(job, times(1)).setState(Job.State.Finished);
		assertTrue(executionTime >= 0);
	}

	@Test(expected = Exception.class)
	public void testExecuteException() throws Exception {
		Exception e = new Exception();
		when(dbService.isDBSessionActive()).thenThrow(e).thenReturn(true);

		JobExecutor jobExecutor = new JobExecutor();
		long executionTime = jobExecutor.execute(job);

		verify(dbService, times(1)).rollbackDBSession();
		verify(job, times(1)).setErrorException(e);
		verify(job, times(1)).setState(Job.State.Error);
	}

	@Test
	public void testWaitForFinished() {
		//when(job.getScheduler().getSchedulerStats());
		
		verify(workerThread, times(1)).takeJob(job);
	}

	class TestWorkerThread extends Thread implements WorkerThread {
		@Override
		public void stopProcessing() {

		}

		@Override
		public Job executedJob() {
			return null;
		}

		@Override
		public void takeJob(Job job) throws SchedulerException {

		}
	}
}
