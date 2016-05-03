package siosio

import javax.batch.api.*
import javax.enterprise.context.*
import javax.inject.*

@Named
@Dependent
class PrintDatabaseBatchlet : AbstractBatchlet() {
  @Inject
  lateinit var service: UserService

  override fun process(): String? {
    service.printUser()
    return "success"
  }
}