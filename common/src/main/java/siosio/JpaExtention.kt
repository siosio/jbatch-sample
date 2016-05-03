package siosio

import javax.persistence.*

fun EntityManagerFactory.use(block: (EntityManager) -> Unit): Unit {
  try {
    this.createEntityManager().use(block)
  } finally {
    this.close()
  }
}

fun EntityManager.use(block: (EntityManager) -> Unit): Unit {
  try {
    this.transaction.begin()
    block(this)
    this.transaction.commit()
  } finally {
    if (this.transaction.isActive) {
      this.transaction.rollback()
    }
    this.close()
  }
}

