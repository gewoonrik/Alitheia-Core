package eu.sqooss.service.scheduler;

import eu.sqooss.core.AlitheiaCore;
import eu.sqooss.service.db.DBService;

public class JobExecutor {

    /**
     * Executes the job. Makes sure that all dependencies are met.
     *
     * @return The time required to execute the Job in milliseconds.
     * @throws Exception
     */
    final public long execute(Job job) throws Exception {
        DBService dbs = AlitheiaCore.getInstance().getDBService();
        long timer = System.currentTimeMillis();
        try {
            job.setState(Job.State.Running);
            job.restart();

            // Idiot/bad programmer proofing
            assert (!dbs.isDBSessionActive());

            if (dbs.isDBSessionActive()) {
                dbs.rollbackDBSession();
                job.setState(Job.State.Error); // No uncommitted sessions are tolerated
            } else {
                if (job.state() != Job.State.Yielded) {
                    job.setState(Job.State.Finished);
                }
            }
        } catch(Exception e) {
            if (dbs.isDBSessionActive()) {
                dbs.rollbackDBSession();
            }

            // In case of an exception, state becomes Error
            job.setErrorException(e);
            job.setState(Job.State.Error);

            // the Exception itself is forwarded
            throw e;
        }
        return System.currentTimeMillis() - timer;
    }

    /**
     * Waits for the job to finish.
     * Note that this method even returns when the job's state changes to Error.
     */
    public final void waitForFinished(Job job) {
        try {
            synchronized (this) {
                // if this method is running inside of a WorkerThread
                // we try to pass the job we're waiting for to the thread.
                if (Thread.currentThread() instanceof WorkerThread) {
                    WorkerThread t = (WorkerThread) Thread.currentThread();
                    t.takeJob(job);
                } else {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            // if something went wrong with taking the job
            // ok - we might be stuck...

            if (job.getScheduler().getSchedulerStats().getIdleWorkerThreads() == 0) {
                job.getScheduler().startOneShotWorkerThread();
            }
        }
        synchronized (this) {
            while (job.state() != Job.State.Finished) {
                if (job.state() == Job.State.Error) {
                    return;
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
