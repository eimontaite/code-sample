package adapters.postgresql

sealed trait Operator
object Operator {

  case object And extends Operator {
    override val toString: String = "AND"
  }

  case object Or extends Operator {
    override val toString: String = "OR"
  }

  case object Equal extends Operator {
    override val toString: String = "="
  }

  case object GreaterThan extends Operator {
    override val toString: String = ">"
  }

  case object LessThan extends Operator {
    override val toString: String = "<"
  }

}
