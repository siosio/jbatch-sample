package siosio.batchlet

import javax.batch.api.AbstractBatchlet
import javax.batch.runtime.context.JobContext
import javax.enterprise.context.Dependent
import javax.inject.Named

@Named
@Dependent
public class SampleBatchlet : AbstractBatchlet() {

  var jobContext: JobContext? = null

  @Throws(Exception::class)
  override fun process(): String {
    System.out.format(
          "**************************************************%n"
        + "  job name: %s%n"
        + "  **************************************************",
        jobContext!!.getJobName())
    return "SUCCESS"
  }
}

