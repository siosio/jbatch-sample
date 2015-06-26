package siosio.rest;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/")
public class BatchResource {

    @GET
    @Path("/run/{jobName}")
    public String start(@PathParam("jobName") String jobName) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Long executionId = jobOperator.start(jobName, null);
        return executionId.toString();
    }
}
