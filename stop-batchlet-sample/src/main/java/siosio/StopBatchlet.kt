package siosio

import org.slf4j.*
import java.util.concurrent.*
import javax.batch.api.*
import javax.batch.runtime.context.*
import javax.enterprise.context.*
import javax.inject.*

@Named
@Dependent
open class StopBatchlet : AbstractBatchlet() {

  @Inject
  lateinit var stepContext: StepContext

  lateinit var currentThread: Thread

  @Inject
  @BatchProperty
  var restart: Boolean? = null

  companion object {
    val logger = LoggerFactory.getLogger(StopBatchlet::class.java)
  }

  override fun process(): String? {
    logger.info("ステップの処理が開始されました。ステップ=[{}]", stepContext.stepName)
    currentThread = Thread.currentThread()

    // 一定時間処理を停止する
    if (!(restart ?: false)) {
      try {
        TimeUnit.MINUTES.sleep(5)
      } catch(e: InterruptedException) {
        return "cancel"
      }
    }
    return "success"
  }

  override fun stop() {
    logger.info("ステップの実行をキャンセルします。ステップ=[{}]", stepContext.stepName)
    currentThread.interrupt()
  }
}