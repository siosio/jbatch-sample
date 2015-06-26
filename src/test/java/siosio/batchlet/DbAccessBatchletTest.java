package siosio.batchlet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import siosio.TestHelper;
import siosio.entity.UserEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class DbAccessBatchletTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(EmbeddedGradleImporter.class)
                .forThisProjectDirectory()
                .importBuildOutput()
                .as(WebArchive.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addClasses(TestHelper.class);
    }

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction utx;

    @Before
    public void setUp() throws Exception {
        utx.begin();
        final Query query = em.createNativeQuery("DELETE FROM USERS");
        query.executeUpdate();
        utx.commit();
        em.clear();
    }

    @Test
    public void testCompleted() throws Exception {
        final JobExecution jobExecution = TestHelper.start("db-access-job");
        assertThat("正常終了していること", jobExecution.getBatchStatus(), is(BatchStatus.COMPLETED));

        final TypedQuery<UserEntity> resultQuery = em.createNamedQuery("findUsersAll", UserEntity.class);
        final List<UserEntity> result = resultQuery.getResultList();
        assertThat("50レコード登録されていること", result.size(), is(50));
    }

    @Test
    public void testFailed() throws Exception {
        utx.begin();
        final Query query = em.createNativeQuery("INSERT INTO USERS VALUES (?, ?)");
        query.setParameter(1, 35L)
                .setParameter(2, "重複すると思われるデータ")
                .executeUpdate();
        query.setParameter(1, 85L)
                .executeUpdate();
        utx.commit();

        final JobExecution jobExecution = TestHelper.start("db-access-job");
        assertThat("異常終了していること", jobExecution.getBatchStatus(), is(BatchStatus.FAILED));

        final TypedQuery<UserEntity> resultQuery = em.createNamedQuery("findUsersAll", UserEntity.class);
        final List<UserEntity> result = resultQuery.getResultList();
        assertThat("登録は失敗するので、初期データの2レコードのみ存在している", result.size(), is(2));

    }
}