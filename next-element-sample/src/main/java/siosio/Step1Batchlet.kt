package siosio

import org.slf4j.*
import javax.batch.api.*
import javax.batch.runtime.context.*
import javax.enterprise.context.*
import javax.inject.*

@Named
@Dependent
class Step1Batchlet :AbstractBatchlet(){
  companion object {
    val logger = LoggerFactory.getLogger(Step1Batchlet::class.java)
  }

  override fun process(): String? {
    logger.info("step1")
    return "ok"
  }
}