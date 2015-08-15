package siosio.batchlet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Properties;

import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import siosio.TestHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class JobParameterSqmpleBatchletTest {

    @Deployment
    public static WebArchive createDeployment() {
        System.getProperties().remove("javax.xml.parsers.SAXParserFactory");

        WebArchive webArchive = ShrinkWrap.create(EmbeddedGradleImporter.class).forThisProjectDirectory()
                .importBuildOutput().as(WebArchive.class).addClasses(TestHelper.class);

        System.setProperty("javax.xml.parsers.SAXParserFactory", "__redirected.__SAXParserFactory");
        return webArchive;
    }

    @Test
    public void testCompleted() throws Exception {
        Properties properties = new Properties();
        properties.put("job-param", "パラメータ");

        JobExecution jobExecution = TestHelper.start("jobParameter", properties);
        assertThat("ジョブは正常終了する", jobExecution.getBatchStatus(), is(BatchStatus.COMPLETED));
    }
}
