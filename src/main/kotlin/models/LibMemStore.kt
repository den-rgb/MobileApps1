package models

import controller.GameController
import mu.KotlinLogging
import views.GameLibView
import java.util.*
import kotlin.collections.ArrayList

private val logger = KotlinLogging.logger {}
var rnd = Random()

internal fun getId(): Int {
    return rnd.nextInt(10000)
}
class LibMemStore: GameLIbraryInt {
    val games = ArrayList<Game>()
    val gameView = GameLibView()
    override fun findAll(): List<Game> {
        return games
    }

    override fun sortById(): List<Game> {
        games.sortBy { it.id }
        return games
    }
    override fun findByID(id: Int): Game? {
        return games.find { p -> p.id == id }
    }

    override fun sortByPrice() : List<Game>{
       games.sortBy { it.price }
        return games
    }

    override fun findByPrice(price: Float): ArrayList<Game> {
        val pricedGames: ArrayList<Game> = arrayListOf()
        games.forEach{
            if(it.price == price)
                pricedGames.add(it)
        }
        return pricedGames
    }

    override fun findByCategory(category: Category): ArrayList<Game> {
        val categorizedGames: ArrayList<Game> = arrayListOf()
        games.forEach{
            if(it.category == category)
                categorizedGames.add(it)
        }
        return categorizedGames
    }

    override fun findByName(name: String): Game? {
        val n = name.uppercase()
        return games.find { p -> p.name == n }
    }

    override fun create(game: Game) {
        game.id = getId()
        games.add(game)
        logAll()
    }

    override fun updateName(id: Int){
        val game = findByID(id)
        if (game != null) {
            game.name = gameView.updateNameView().uppercase()
        }
    }

    override fun listCategories(){
        gameView.listCategoriesView()
        val size = Category.values().sortedArray()
        var index = 1
        for(c in size) {
            println("----" +index+ ": " + c)
            index++
        }
    }
    override fun chooseCategory(): Category {
        listCategories()
        val choice = gameView.chooseCategoryView()
        var cat = Category.None
        when(choice){
            1 -> cat = Category.Action
            2 -> cat  = Category.Shooter
            3 -> cat  = Category.RPG
            4 -> cat  = Category.Sandbox
            5 -> cat  = Category.Puzzle
            6 -> cat  = Category.Sport
            7 -> cat  = Category.None
        }
        return cat
    }

    override fun updateCategory(id: Int){
        val game = findByID(id)
        if (game != null) {
            game.category = chooseCategory()
        }
    }

    override fun updatePrice(id: Int){
        val game = findByID(id)
        if (game != null) {
            game.price = gameView.updatePriceView()
        }
    }

    override fun remove(game:Game) {
        game.id = getId()
        games.remove(game)
        logAll()
    }

    override fun clear() {
        games.clear()
    }


    internal fun logAll() {
        games.forEach { logger.info("$it") }
    }
}
