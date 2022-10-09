package models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "games.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<Game>>() {}.type




class JsonMemStore : GameLIbraryInt {

    var games = mutableListOf<Game>()


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