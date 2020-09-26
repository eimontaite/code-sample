package adapters.postgresql

trait Filter {
  def process(query: String): String
}
