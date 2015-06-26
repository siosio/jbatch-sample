package siosio.batchlet;

import javax.batch.api.AbstractBatchlet;
import javax.batch.api.BatchProperty;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import siosio.entity.UserEntity;

@Dependent
@Named
@Transactional(TxType.REQUIRES_NEW)
public class DbAccessBatchlet extends AbstractBatchlet {

    @PersistenceContext(unitName = "batch")
    EntityManager em;

    @Inject
    @BatchProperty
    String count;

    @Override
    public String process() throws Exception {
        final int insertCount = Integer.parseInt(count);

        for (int i = 0; i < insertCount; i++) {
            em.persist(new UserEntity("name_" + (i + 1)));
        }

        return "SUCCESS";
    }
}
