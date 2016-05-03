package siosio

import javax.batch.api.AbstractBatchlet
import javax.enterprise.context.Dependent
import javax.inject.Inject
import javax.inject.Named
import javax.transaction.*

@Dependent
@Named
class JpaSampleBatchlet : AbstractBatchlet() {

  @Inject
  lateinit var service: UserService

  override fun process(): String? {
    service.register()
    return "success"
  }
}
