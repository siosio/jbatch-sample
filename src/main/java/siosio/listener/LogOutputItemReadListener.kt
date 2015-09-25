package siosio.listener

import javax.batch.api.chunk.listener.AbstractItemReadListener
import javax.batch.runtime.context.JobContext
import javax.enterprise.context.Dependent
import javax.inject.Inject
import javax.inject.Named

@Named
@Dependent
public class LogOutputItemReadListener : AbstractItemReadListener() {

  @Inject
  lateinit val jobContext:JobContext

  override fun afterRead(item: Any?) {
    item?.let {
      println("read item = ${item}, job status = ${jobContext.batchStatus}")
    }
  }
}