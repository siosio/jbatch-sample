package siosio

import org.slf4j.*
import javax.batch.api.chunk.*
import javax.enterprise.context.*
import javax.inject.*

@Named
@Dependent
class Writer : AbstractItemWriter() {
  companion object {
    val logger = LoggerFactory.getLogger(Writer::class.java)
  }
  override fun writeItems(items: MutableList<Any>?) {
    logger.info("writer")
  }
}