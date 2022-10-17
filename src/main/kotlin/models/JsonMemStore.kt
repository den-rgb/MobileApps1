package models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import controller.GameController
import mu.KotlinLogging

import helpers.*
import views.GameLibView
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "games.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<Game>>() {}.type

class JsonMemStore : GameLIbraryInt {

    var games = mutableListOf<Game>()
    val gameView = GameLibView()
    var rnd = Random()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): List<Game> {
        return games
    }

    override fun findByID(id: Int): Game? {
        return games.find { p -> p.id == id }
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
        val n = name.uppercase(Locale.getDefault())
        return games.find { p -> p.name == n }
    }

    override fun create(game: Game) {
        game.id = getId()
        games.add(game)
        logAll()
        serialize()
    }

    override fun remove(game:Game) {
        game.id = getId()
        games.remove(game)
        logAll()
        serialize()
    }

    override fun clear(){
        games.clear()
        serialize()
    }

    override fun updateName(id: Int){
        val game = findByID(id)
        if (game != null) {
            game.name = gameView.updateNameView().uppercase()
            serialize()
        }
    }

    override fun updatePrice(id: Int){
        val game = findByID(id)
        if (game != null) {
            game.price = gameView.updatePriceView()
            serialize()
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
            serialize()
        }
    }

    private fun getId(): Int {
        return rnd.nextInt(10000)
    }

    private fun logAll() {
        games.forEach { logger.info("$it") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(games, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        games = Gson().fromJson(jsonString, listType)
    }
}