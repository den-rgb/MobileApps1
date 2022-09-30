package models

import mu.KotlinLogging
import java.util.Random

private val logger = KotlinLogging.logger {}
var rnd = Random()

internal fun getId(): Int {
    return rnd.nextInt(10000)
}
class LibMemStore: GameLIbraryInt {
    val games = ArrayList<Game>()

    override fun findAll(): List<Game> {
        return games
    }

    override fun findByID(id: Int): Game? {
        return games.find { p -> p.id == id }
    }

    override fun findByName(name: String): Game? {
        return games.find { p -> p.name == name }
    }

    override fun create(game: Game) {
        game.id = getId()
        games.add(game)
        logAll()
    }

    override fun update(game: Game) {
        val foundGame = findByID(game.id!!)
        if (foundGame != null) {
            foundGame.name = game.name
            foundGame.price = game.price
        }
    }

    internal fun logAll() {
        games.forEach { logger.info("$it") }
    }
}