package siosio

import org.slf4j.*
import siosio.sql.*
import javax.batch.api.*
import javax.enterprise.context.*
import javax.inject.*

@Named
@Dependent
class PrintDatabaseBatchlet : AbstractBatchlet() {

  companion object {
    val logger = LoggerFactory.getLogger(PrintDatabaseBatchlet::class.java)
  }

  @Inject
  lateinit var database:Database

  override fun process(): String? {
    database.eachRow(User::class, "select * from user order by id") {
      logger.info("id:[{}], name:[{}]", it.id, it.name)
    }

    return "success"
  }
}