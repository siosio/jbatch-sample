package siosio

import siosio.sql.*
import javax.batch.api.*
import javax.enterprise.context.*
import javax.inject.*

/**
 * データベース・アクセスを行う[Batchlet]のサンプル実装
 */
@Dependent
@Named
class DbAccessBatchlet : AbstractBatchlet() {

  @Inject
  lateinit var database: Database

  override fun process(): String? {
    database.withTransaction {
      (1..10).forEach {
        database.execute("insert into user (name) values (:name)", User(name = "name_$it"))
      }
    }
    return "success"
  }
}