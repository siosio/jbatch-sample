package siosio

import org.slf4j.*
import javax.batch.api.*
import javax.enterprise.context.*
import javax.inject.*

@Named("sample")
@Dependent
class SampleBatchlet : AbstractBatchlet() {
  companion object {
    val logger = LoggerFactory.getLogger(SampleBatchlet::class.java)
  }

  override fun process(): String? {
    logger.info("hello world!!!")
    return "success"
  }
}
