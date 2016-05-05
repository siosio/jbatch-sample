package siosio

import javax.batch.api.chunk.*
import javax.enterprise.context.*
import javax.inject.*

@Named
@Dependent
class Processor : ItemProcessor {
  override fun processItem(item: Any?): Any? {
    return if (item is User) {
      toUpper(item)
    } else {
      null
    }
  }

  fun toUpper(user: User): User {
    return User(user.id, user.name?.toUpperCase())
  }
}