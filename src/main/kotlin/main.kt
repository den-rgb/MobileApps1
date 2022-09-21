package org.setu.placemark

import mu.KotlinLogging
import java.util.*

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
            2 ->  updateGame(gameList)
            3 ->  listGames()
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

        print("Name: ")
        val name = readLine()!!
        if (games.containsValue(Game(name))){
            println("This game already exists here!!")
            return addGame(gameList)
        }

        print("Price: ")
        val price = readLine()!!

        val rnd = Random()
        var id: Int = rnd.nextInt(10000)
        println("Game Id: " + id)
        println()
        games[id.toString()] = Game(name,id,price.toFloat())
    }

    fun listGames(){
        println("---------- Your Games --------------")
        println()
        for (g in gameList){
            println("-------> "+g.value.name)
        }
    }

    fun updateGame(games: MutableMap<String, Game>){
        for (g in gameList){
            println("-------> "+g.value.name + " " + g.key)
        }
        println(" Choose a game to update ")
        print( "---Game Id: ")
        val input = readLine()!!
        if (input.toIntOrNull() != null && input.isNotEmpty()) {
            input.toInt()
        }
        if (games.containsKey(input)){
            println(" What would you like to update?? ")
            println( " ---> 1: Name")
            println( " ---> 2: Price")
            val choice = readLine()!!
            if (choice.toIntOrNull() != null && choice.isNotEmpty()) {
                when (choice.toInt()) {
                    1 -> updateName(games, input.toInt())
                    2 -> updatePrice(games,input.toInt())
                }
            }
    }

    }

    fun updateName(games: MutableMap<String, Game>,input : Int){
        print( "New Game name --> ")
        val name = readLine()!!
        games[input.toString()] = Game(name,input,games.getValue("$input").price)
    }

    fun updatePrice(games: MutableMap<String, Game>,input : Int){
        print( "New Game price--> ")
        val price = readLine()!!
        games[input.toString()] = Game(games.getValue("$input").name,input,price.toFloat())
    }