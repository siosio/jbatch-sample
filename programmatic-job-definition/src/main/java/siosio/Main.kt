package siosio

import org.jberet.job.model.*
import org.jberet.operations.*
import java.util.concurrent.*
import javax.batch.runtime.*

object Main {
  @JvmStatic
  fun main(args: Array<String>) {
    val job = JobBuilder("sample-batchlet")
        .step(
            StepBuilder("batchlet-step")
                .batchlet("sampleBatchlet")
                .next("chunk-step")
                .build()
        )
        .step(
            StepBuilder("chunk-step")
                .reader("sampleItemReader")
                .writer("sampleItemWriter")
                .itemCount(2)
                .build()
        )
        .build()

    val jobOperator = BatchRuntime.getJobOperator() as JobOperatorImpl
    val executionId = jobOperator.start(job, null)

    val execution = jobOperator.getJobExecution(executionId)

    val runningStatus = arrayOf(BatchStatus.STARTED, BatchStatus.STARTING, BatchStatus.STOPPING)
    while (true) {
      TimeUnit.SECONDS.sleep(5)
      if (execution.batchStatus !in runningStatus) {
        break
      }
    }
  }
}

