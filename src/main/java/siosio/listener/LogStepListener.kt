package siosio.listener

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.batch.api.listener.AbstractStepListener
import javax.batch.runtime.context.JobContext
import javax.batch.runtime.context.StepContext
import javax.enterprise.context.Dependent
import javax.inject.Inject
import javax.inject.Named
import kotlin.platform.platformStatic

@Dependent
@Named
public class LogStepListener : AbstractStepListener() {

  companion object {
    private val LOGGER: Logger = LoggerFactory.getLogger(javaClass<LogStepListener>())
  }

  @Inject
  private var jobContext: JobContext? = null

  @Inject
  private var stepContext: StepContext? = null

  override fun beforeStep() {
    writeLog("step start...")
  }

  override fun afterStep() {
    writeLog("step end...")
  }

  private fun writeLog(message: String) {
    LOGGER.info("{} job={}, step={}, status={}",
        message,jobContext!!.getJobName(), stepContext!!.getStepName(), stepContext!!.getBatchStatus())
  }
}