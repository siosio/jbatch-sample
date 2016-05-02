package siosio

import org.slf4j.*
import java.util.*
import java.util.concurrent.*
import javax.batch.runtime.*


object StopBatchAction {
  val logger = LoggerFactory.getLogger(StopBatchAction::class.java)

  @JvmStatic
  fun main(args: Array<String>) {
    if (args.isEmpty()) {
      println("Usage: java siosio.StopBatchAction")
    }

    val properties = Properties()
    properties.setProperty("restart", "false")

    val jobOperator = BatchRuntime.getJobOperator()
    val executionId = jobOperator.start("stop-batchlet", properties)

    val jobExecution = jobOperator.getJobExecution(executionId)
    logger.info("ジョブを開始しました。ジョブ名=[{}], 実行ID=[{}]", jobExecution.jobName, executionId)

    // ジョブが開始状態になったら、停止要求をなげる
    wait(jobExecution, BatchStatus.STARTED)
    jobOperator.stop(executionId)

    // ジョブが停止状態になったらリスタートする
    wait(jobExecution, BatchStatus.STOPPED)
    logger.info("ジョブを再実行します。ジョブ名=[{}], 実行ID=[{}], ジョブステータス=[{}]",
        jobExecution.jobName,
        executionId,
        jobExecution.batchStatus)

    properties.setProperty("restart", "true")
    val restartedExecutionId = jobOperator.restart(executionId, properties)
    val restartedExecution = jobOperator.getJobExecution(restartedExecutionId)
    // 正常終了するまで処理を末
    wait(restartedExecution, BatchStatus.COMPLETED)
    logger.info("ジョブの再実行が終了しました。ジョブ名=[{}], 実行ID=[{}]", restartedExecution.jobName, restartedExecutionId)
  }

  private fun wait(jobExecution: JobExecution, expectedBatchStatus: BatchStatus) {
    (1..10).forEach {
      TimeUnit.SECONDS.sleep(5)
      if (jobExecution.batchStatus == expectedBatchStatus) {
        return
      }
      println("jobExecution.batchStatus = ${jobExecution.batchStatus}")
    }
    throw IllegalStateException("バッチの状態が不正です。")
  }
}
