package siosio

import siosio.sql.*
import javax.batch.api.chunk.*
import javax.enterprise.context.*
import javax.inject.*

@Named
@Dependent
class DatabaseWriter : AbstractItemWriter() {

  @Inject
  lateinit var database: Database

  override fun writeItems(items: MutableList<Any>?) {
    items?.let {
      database.withTransaction {
        batchExecute("insert into user (name) values (:name)", it)
      }
    }
  }
}