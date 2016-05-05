package siosio

import org.slf4j.*
import siosio.sql.*
import javax.batch.api.*
import javax.enterprise.context.Dependent
import javax.inject.*

@Named
@Dependent
class PrintDatabaseBatchlet : AbstractBatchlet() {

  companion object {
    private val logger = LoggerFactory.getLogger(PrintDatabaseBatchlet::class.java)
  }

  @Inject
  lateinit private var dataase: Database

  override fun process(): String? {
    dataase.eachRow(User::class, "select * from user order by id") {
      logger.info("user = {}", it)
    }

    return "success"
  }

}
