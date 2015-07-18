package siosio.rest

import javax.batch.runtime.BatchRuntime
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response

public class BatchResource {

  /**
   * ジョブを実行する
   */
  @GET
  @Path("/run/{jobName}")
  public fun start(@PathParam("jobName") jobName: String): String {
    val jobOperator = BatchRuntime.getJobOperator()
    val executionId = jobOperator.start(jobName, null)
    return executionId.toString()
  }

  /**
   * 実行中のジョブを停止する。
   */
  @GET
  @Path("/stop/{executionId}")
  fun stop(@PathParam("executionId") executionId:Long): Response {
    val jobOperator = BatchRuntime.getJobOperator()
    jobOperator.stop(executionId)
    return Response.ok().build()
  }
}
