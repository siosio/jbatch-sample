package siosio

import org.supercsv.cellprocessor.*
import org.supercsv.cellprocessor.constraint.*
import org.supercsv.cellprocessor.ift.*
import org.supercsv.io.*
import org.supercsv.prefs.*
import java.io.*
import javax.batch.api.chunk.*
import javax.enterprise.context.*
import javax.inject.*
import kotlin.properties.*

@Named
@Dependent
class CsvReader : AbstractItemReader() {

  private var csvBeanReader: ICsvBeanReader by Delegates.notNull()

  private val nameMapping: Array<String> = arrayOf("id", "name")

  private val processors: Array<CellProcessor> = arrayOf(NotNull(ParseInt()), NotNull())

  override fun open(checkpoint: Serializable?) {
    csvBeanReader = CsvBeanReader(FileReader("input.csv"), CsvPreference.STANDARD_PREFERENCE)
  }

  override fun readItem(): Any? {
    val user = csvBeanReader.read(User::class.java, nameMapping, *processors)
    return user
  }

  override fun close() {
    csvBeanReader.close()
  }
}