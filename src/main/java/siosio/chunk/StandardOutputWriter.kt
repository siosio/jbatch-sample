package siosio.chunk

import javax.batch.api.chunk.AbstractItemWriter
import javax.enterprise.context.Dependent
import javax.inject.Named

@Named
@Dependent
public class StandardOutputWriter : AbstractItemWriter() {
  override fun writeItems(items: MutableList<Any>) {
    items.forEach {
      println("it = ${it}")
    }
  }
}