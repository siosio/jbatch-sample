package siosio

import siosio.sql.*
import javax.batch.api.*
import javax.enterprise.context.*
import javax.inject.*

@Named
@Dependent
class CreateDatabaseBatchlet : AbstractBatchlet() {
  @Inject
  lateinit private var database: Database

  override fun process(): String? {
    database.withTransaction {
      execute("drop table if exists user")
      execute("create table user (id int auto_increment, name varchar(100), primary key(id))")
    }
    return "success"
  }
}