package siosio

import javax.batch.api.chunk.*
import javax.enterprise.context.*
import javax.inject.*

@Named
@Dependent
class SampleItemReader : AbstractItemReader() {
  val item = (1..5).asIterable().iterator()
  val next: () -> Int? = {
    if (item.hasNext()) {
      item.next()
    } else {
      null
    }
  }

  override fun readItem(): Any? = next()
}
