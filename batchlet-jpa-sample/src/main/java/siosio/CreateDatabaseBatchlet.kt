package siosio

import javax.batch.api.*
import javax.enterprise.context.*
import javax.inject.*
import javax.persistence.*

@Named
@Dependent
class CreateDatabaseBatchlet : AbstractBatchlet() {
  override fun process(): String? {
    Persistence.createEntityManagerFactory("batch").use { em ->
      em.createNativeQuery("drop table if exists user").executeUpdate()
      em.createNativeQuery("create table user (id bigint auto_increment, name varchar(100), primary key(id))").executeUpdate()
    }

    return "success"
  }
}
