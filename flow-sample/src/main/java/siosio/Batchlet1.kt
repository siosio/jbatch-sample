package siosio

import org.jberet.cdi.*
import org.slf4j.*
import javax.batch.api.*
import javax.inject.*

@Named
@StepScoped
open class Batchlet1 : AbstractBatchlet() {

  @Inject
  @BatchProperty
  lateinit private var property: String

  companion object {
    private val logger = LoggerFactory.getLogger(Batchlet1::class.java)
  }

  override fun process(): String? {
    logger.info("property = [{}]", property)
    return "ok"
  }
}
