package siosio.batchlet

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.batch.api.AbstractBatchlet
import javax.batch.api.BatchProperty
import javax.enterprise.context.Dependent
import javax.inject.Inject
import javax.inject.Named

@Dependent
@Named
public class JobParameterSampleBatchlet: AbstractBatchlet() {

  @Inject
  @BatchProperty
  var param:String? = null

  override fun process(): String {
    log.info("param: {}", param)
    return "success"
  }

  companion object {
    val log:Logger = LoggerFactory.getLogger(JobParameterSampleBatchlet::class.java)
  }

}