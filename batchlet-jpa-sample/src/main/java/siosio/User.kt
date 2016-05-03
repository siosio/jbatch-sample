package siosio

import javax.persistence.*

@Entity
@NamedQueries(
    NamedQuery(name = "findAllUser",
        query = "select u from User u order by u.id"
    )
)
open class User(
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    var id: Long?, var name: String?) {

  constructor() : this(null, null)

  constructor(name: String) : this(null, name) {
    this.name = name
  }

  override fun toString(): String {
    return "User(id=$id, name=$name)"
  }
}