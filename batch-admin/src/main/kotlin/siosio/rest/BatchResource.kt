package siosio.rest

import javax.batch.runtime.BatchRuntime
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

@Path(value = "/")
open class BatchResource {

  @GET
  @Path(value = "/run/{jobName}")
  open fun start(PathParam("jobName") jobName: String): String {
    val jobOperator = BatchRuntime.getJobOperator()
    val executionId = jobOperator.start(jobName, null)
    return executionId.toString()
  }
}

