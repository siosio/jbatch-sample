package siosio;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;

/**
 * テスト実行をちょっと便利にするクラス
 */
public class TestHelper {

    public static JobExecution start(String jobId) throws Exception {
        final JobOperator operator = BatchRuntime.getJobOperator();
        final long executionId = operator.start(jobId, null);
        final JobExecution jobExecution = operator.getJobExecution(executionId);
        while (jobExecution.getEndTime() == null) {
            TimeUnit.MILLISECONDS.sleep(500);
        }
        return jobExecution;
    }

    public static JobExecution start(String jobId, Properties parameter) throws Exception {

        final JobOperator operator = BatchRuntime.getJobOperator();
        final long executionId = operator.start(jobId, parameter);

        final JobExecution jobExecution = operator.getJobExecution(executionId);
        while (jobExecution.getEndTime() == null) {
            TimeUnit.MILLISECONDS.sleep(500);
        }
        return jobExecution;

    }
}
