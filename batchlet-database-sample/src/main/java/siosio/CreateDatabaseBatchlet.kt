package siosio

import siosio.sql.*
import javax.batch.api.*
import javax.enterprise.context.*
import javax.inject.*

/**
 * [DbAccessBatchlet]で使うテーブルを作る[Batchlet]
 */
@Dependent
@Named
class CreateDatabaseBatchlet : AbstractBatchlet() {

  @Inject
  lateinit var database: Database

  override fun process(): String? {
    database.execute("drop table if exists user")
    database.execute("create table user(id bigint auto_increment, name varchar(100), primary key (id))")
    return "success"
  }
}
