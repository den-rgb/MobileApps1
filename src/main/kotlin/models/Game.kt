package models

data class Game(
    var name : String? = null,
    var id  : Int? = null,
    var price : Float? = null,
    var category: Category? = Category.None,
)

enum class Category{
    Action,
    Shooter,
    RPG,
    Sandbox,
    Puzzle,
    Sport,
    None
}