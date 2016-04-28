package siosio

import org.h2.jdbcx.*
import siosio.sql.*
import javax.batch.runtime.context.*
import javax.enterprise.context.*
import javax.enterprise.inject.*
import javax.inject.*

@Dependent
class DatabaseProducer {

  @Inject
  lateinit var jobContext: JobContext

  @Produces
  fun create(): Database {
    val properties = jobContext.properties

    val jdbcDataSource = JdbcDataSource()
    jdbcDataSource.setURL(properties.getProperty("database.url"))
    jdbcDataSource.user = properties.getProperty("database.user")
    jdbcDataSource.password = properties.getProperty("database.password")

    return Database(jdbcDataSource)
  }
}