package siosio.chunk;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import siosio.TestHelper;
import siosio.entity.UserEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SampleChunkTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(EmbeddedGradleImporter.class)
                .forThisProjectDirectory()
                .importBuildOutput()
                .as(WebArchive.class)
                .addClasses(TestHelper.class);
    }

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction utx;

    @Before
    public void setUp() throws Exception {
        utx.begin();
        final Query query = em.createNativeQuery("delete from USERS");
        query.executeUpdate();
        utx.commit();
    }

    /**
     * ジョブが正常に終了するはずのケース
     */
    @Test
    public void testJobCompleted() throws Exception {
        final JobExecution jobExecution = TestHelper.start("chunk-sample");
        assertThat("正常終了していること", jobExecution.getBatchStatus(), is(BatchStatus.COMPLETED));

        final List<UserEntity> result = em.createNamedQuery("findUsersAll", UserEntity.class)
                .getResultList();

        assertThat("10レコード登録されていること", result.size(), is(10));

        for (int i = 0; i < 10; i++) {
            int index = i + 1;
            assertThat("ユーザ名", result.get(i)
                    .getName(), is("user name " + index));
        }
    }
}
