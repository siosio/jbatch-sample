package siosio

import org.slf4j.*
import javax.enterprise.context.*
import javax.inject.*
import javax.persistence.*
import javax.transaction.*

@Dependent
class UserService {
  companion object {
    val logger = LoggerFactory.getLogger(UserService::class.java)
  }

  fun register(): Unit {

    Persistence.createEntityManagerFactory("batch").use { em ->
      (1..5).forEach {
        val user = User("name_$it")
        em.persist(user)
      }
    }
  }

  fun printUser(): Unit {
    Persistence.createEntityManagerFactory("batch").use { em ->
      val query = em.createNamedQuery("findAllUser", User::class.java)
      query.resultList.forEach { user ->
        logger.info(user.toString())
      }
    }
  }

}