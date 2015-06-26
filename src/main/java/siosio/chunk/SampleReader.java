package siosio.chunk;

import javax.batch.api.chunk.AbstractItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Dependent
@Named
public class SampleReader extends AbstractItemReader {

    int index = 0;

    @Override
    public Object readItem() throws Exception {
        index++;
        if (index <= 10) {
            return index;
        }
        return null;
    }
}
