package siosio

import org.slf4j.*
import javax.batch.api.chunk.*
import javax.enterprise.context.*
import javax.inject.*

@Named
@Dependent
class SampleItemWriter : AbstractItemWriter() {
  companion object {
    val logger = LoggerFactory.getLogger(SampleItemWriter::class.java)
  }

  override fun writeItems(items: MutableList<Any>?) {
    items?.forEachIndexed { index, item ->
      logger.info("$index = [$item]")
    }
  }
}
