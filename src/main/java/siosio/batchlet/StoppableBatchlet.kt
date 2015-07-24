package siosio.batchlet

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.SQLException
import java.sql.Statement
import javax.annotation.Resource
import javax.batch.api.AbstractBatchlet
import javax.batch.runtime.BatchStatus
import javax.batch.runtime.context.JobContext
import javax.batch.runtime.context.StepContext
import javax.enterprise.context.Dependent
import javax.inject.Inject
import javax.inject.Named
import javax.servlet.jsp.jstl.sql.SQLExecutionTag
import javax.sql.DataSource
import kotlin.platform.platformStatic
import kotlin.properties.Delegates

@Dependent
@Named
public class StoppableBatchlet : AbstractBatchlet() {

  companion object {
    private val LOGGER: Logger = LoggerFactory.getLogger(javaClass<StoppableBatchlet>())
  }

  @Resource(lookup = "java:/jbatch")
  private var dataSource: DataSource? = null

  @Inject
  private var jobContext: JobContext? = null

  @Inject
  private var stepContext: StepContext? = null

  private var statement: Statement by Delegates.notNull()

  fun DataSource.use(body: (Connection) -> Unit) {
    val connection = getConnection()
    try {
      body(connection)
    } catch (e: SQLException) {
      if (!isJobStop(e)) {
        throw e
      }
      LOGGER.info("ステップの処理がキャンセルされました。 step={}, status={}",
          stepContext!!.getStepName(), stepContext!!.getBatchStatus())
    } finally {
      try {
        connection.close()
      } catch(e: SQLException) {
        LOGGER.warn("failed to connection close.", e)
      }
    }
  }

  override fun process(): String {
    // 処理の長いSQL文を実行する
    dataSource!!.use {
      statement = it.createStatement()
      statement.execute("select pg_sleep(30)")
    }
    return "OK"
  }

  override fun stop() {
    LOGGER.info("ジョブを停止します..... execution id={}, step={}, status={}",
        jobContext!!.getExecutionId(), stepContext!!.getStepName(), stepContext!!.getBatchStatus())
    statement.cancel()
  }

  /**
   * ジョブのストップ要求かどうかを判定する。
   *
   * ジョブのステータスがストップ処理中で、発生したSQL例外が処理キャンセルの場合は、
   * ジョブのストップ要求と判定する。
   */
  private fun isJobStop(e: SQLException): Boolean {
    return jobContext!!.getBatchStatus() == BatchStatus.STOPPING && e.getSQLState() == "57014"
  }
}
