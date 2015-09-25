package siosio.chunk

import java.util.concurrent.TimeUnit
import javax.batch.api.chunk.AbstractItemReader
import javax.enterprise.context.Dependent
import javax.inject.Named

@Named
@Dependent
internal class EndlessReader : AbstractItemReader() {

  var counter: Int = 0
  override fun readItem(): Any? {
    counter += 1
    return counter
  }
}