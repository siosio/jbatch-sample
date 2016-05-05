package siosio

class User(var id: Int?, var name: String?) {
  constructor() : this(null, null) {
  }

  override fun toString(): String {
    return "User(id=$id, name=$name)"
  }
}
