package models

interface GameLIbraryInt {
        fun findAll(): List<Game>
        fun findByID(id: Int): Game?
        fun findByPrice(price: Float): Game?
        fun findByCategory(category: Category): Game?
        fun findByName(name: String): Game?
        fun create(game: Game)
        fun update(game: Game)
}