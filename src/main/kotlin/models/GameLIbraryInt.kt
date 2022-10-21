package models

interface GameLIbraryInt {
        fun findAll(): List<Game>
        fun findByID(id: Int): Game?
        fun findByPrice(price: Float): ArrayList<Game>
        fun findByCategory(category: Category): ArrayList<Game>
        fun findByName(name: String): Game?
        fun create(game: Game)
        fun remove(game:Game)
        fun clear()
        fun updateName(id: Int)
        fun updatePrice(id: Int)
        fun updateCategory(id: Int)
        fun chooseCategory(): Category
        fun listCategories()
        fun sortById(): List<Game>
        fun sortByPrice(): List<Game>

}