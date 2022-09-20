package org.setu.placemark

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
val red = "\u001b[31m"
val box = "\u001b[52m"
var gameList: MutableMap<String, Game> = mutableMapOf()
fun main(args: Array<String>){

    var input: Int

    do {
        input = menu()
        when(input) {
            1 ->  addGame(gameList)
            2 ->  listGames(gameList)
            3 ->  listGames(gameList)
            -1 -> println(" Exiting App ")
            else -> println(" Invalid Option ")
        }
        println()
    } while (input != -1)
    logger.info { " Shutting Down Game Library " }
}

    fun menu() : Int {

        var option : Int
        var input: String? = null

        println(red + "  Welcome to the Game Library" + "\n"  )
        println(box  + "          Main Menu")
        println("-------------------------------")
        println(" 1. Add a Game to your library")
        println(" 2. Update a Game")
        println(" 3. List All Games")
        println("-1. Exit")
        println()
        print("----->")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -1
        return option
    }


    fun addGame(games: MutableMap<String, Game>){
        println(box +"Enter game details...." )
        print("Id: ")
        val id = readLine()!!
        if (games.keys.contains(id)){
            println("Oh Oh maybe try a different game id?")
            return addGame(gameList)
        }
        print("Name: ")
        val name = readLine()!!
        games[id] = Game(name,id.toInt())
    }

    fun listGames(games: MutableMap<String, Game>){

    }



