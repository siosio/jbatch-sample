package siosio.chunk;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

import siosio.entity.UserEntity;

@Named
@Dependent
public class SampleProcessor implements ItemProcessor {

    @Override
    public Object processItem(Object item) throws Exception {
        return new UserEntity("user name " + item);
    }
}
