package siosio.batchlet;

import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Dependent
public class SampleBatchlet extends AbstractBatchlet {

    @Inject
    JobContext jobContext;

    @Override
    public String process() throws Exception {
        System.out.format("**************************************************%n"
                        + "  job name: %s%n  **************************************************",
                jobContext.getJobName());
        return "SUCCESS";
    }
}

