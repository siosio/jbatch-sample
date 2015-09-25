package siosio.chunk;

import java.util.concurrent.TimeUnit;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.AbstractItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named
public class SampleReader extends AbstractItemReader {

    int index = 0;

    @BatchProperty
    private int max = 10;

    @Override
    public Object readItem() throws Exception {
        TimeUnit.SECONDS.sleep(5);
        index++;
        if (index <= max) {
            return index;
        }
        return null;
    }
}
