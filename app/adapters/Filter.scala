package adapters

trait Filter {
  def process(query: String): String
}
