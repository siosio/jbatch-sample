package siosio

import org.slf4j.*
import javax.batch.api.*
import javax.enterprise.context.*
import javax.inject.*

@Named
@Dependent
class Step2Batchlet : AbstractBatchlet() {
  companion object {
    val logger = LoggerFactory.getLogger(Step2Batchlet::class.java)
  }
  override fun process(): String? {
    logger.info("step2")
    return "ok"
  }
}