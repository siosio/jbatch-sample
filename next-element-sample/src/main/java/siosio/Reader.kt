package siosio

import org.slf4j.*
import javax.batch.api.chunk.*
import javax.batch.runtime.context.*
import javax.enterprise.context.*
import javax.inject.*

@Named
@Dependent
class Reader : AbstractItemReader() {
  companion object {
    val logger = LoggerFactory.getLogger(Reader::class.java)
  }

  @Inject
  lateinit private var stepContext: StepContext

  override fun readItem(): Any? {
    logger.info("reader")
    stepContext.exitStatus = "ok"
    return null
  }
}