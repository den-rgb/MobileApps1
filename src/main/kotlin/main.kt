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
            4 -> removeGame(gameList)
            5 -> clearLib()
            6 -> searchGame(gameList)
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
        println(" 2. Update Game Info")
        println(" 3. List All Games")
        println(" 4. Remove Game")
        println(" 5. Clear library")
        println(" 6. Search")
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
        if(price.toFloatOrNull()!=null && price.isNotEmpty()) {
            val rnd = Random()
            var id: Int = rnd.nextInt(10000)
            println("Game Id: " + id)
            println()
            games[id.toString()] = Game(name, id, price.toFloat())
        }else{
            println(" Invalid price ")
            addGame(gameList)
        }
    }

    fun listGames(){
        println("---------- Your Games --------------")
        println()
        for (g in gameList){
            println("-------> "+g.value.name  +" --- $" + g.value.price)
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
            println( " ---> 3: Back")
            val choice = readLine()!!
            if (choice.toIntOrNull() != null && choice.isNotEmpty()) {
                when (choice.toInt()) {
                    1 -> updateName(games, input.toInt())
                    2 -> updatePrice(games,input.toInt())
                    3 -> menu()
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

    fun removeGame(games: MutableMap<String, Game>){
        println("Which game would you like to remove")
        listGames()
        var input = readLine()!!
        print("------> ")
        for (g in games){
            if( g.value.name.equals(input) or (g.value.id.toString() == input)){
                gameList.remove(g.key)
            }else {
                println(" Game id or Game name doesn't exist!")
                removeGame(gameList)
            }
        }
    }

    fun clearLib(){
        gameList.clear()
    }

    fun searchGame(games: MutableMap<String, Game>){
        println()
        println(" Search by: ")
        println("----1. ID ")
        println("----2. Name ")
        println("----3. Price ")
        println("----4. Category ")
        println("----5. Back ")
        val choice = readLine()!!
        for (g in games){
            if (choice.toIntOrNull() != null && choice.isNotEmpty()) {
                when (choice.toInt()) {
                    1 -> searchId(gameList)
                    2 -> searchName(gameList)
                    3 -> searchPrice(gameList)
                    5 -> menu()
                }
            }
        }
    }

    fun searchName(games: MutableMap<String, Game>){
        println()
        println("-----Name: ")
        val input = readLine()!!
        var i = 0
        var x = 0
        for (g in games){
            if (input.isNotEmpty()) {
                if (g.value.name.equals(input)) {
                    println("----Name: " + g.value.name + " ----Id: " + g.value.id + " ----Price: " + g.value.price)
                    i++
                    x++
                } else {
                    i++
                }

                if (i == games.size && x == 0) {
                    println()
                    println("--- No Matches Found ---")
                    searchGame(gameList)
                }
            }else {
                println( "--- Try writing something ---")
                searchName(gameList)
            }
        }
        searchGame(gameList)
    }

    fun searchId(games: MutableMap<String, Game>){
        println()
        println(" -----Id: ")
        val input = readLine()!!
        var i = 0
        var x = 0
        for (g in games){
            if (input.toIntOrNull() != null && input.isNotEmpty()) {
                if (g.value.id == (input.toInt())) {
                    println("----Name: " + g.value.name + " ----Id: " + g.value.id + " ----Price: " + g.value.price)
                    i++
                    x++
                } else {
                    i++
                }

                if (i == games.size && x == 0) {
                    println()
                    println("--- No Matches Found ---")
                    searchGame(gameList)
                }
            }else{
                println( "--- Try writing a number ---")
                searchId(gameList)
            }
        }
        searchGame(gameList)
    }

    fun searchPrice(games: MutableMap<String, Game>){
        println()
        println("-----Price: ")
        val input = readLine()!!
        var i = 0
        var x = 0
        for (g in games){
            if (input.toFloatOrNull() != null && input.isNotEmpty()) {
                if (g.value.price == (input.toFloat())) {
                    println("----Name: " + g.value.name + " ----Id: " + g.value.id + " ----Price: " + g.value.price)
                    i++
                    x++
                } else {
                    i++
                }

                if (i == games.size && x == 0) {
                    println()
                    println("--- No Matches Found ---")
                    searchGame(gameList)
                }
            }else{
                println( "--- Try writing a number ---")
                searchPrice(gameList)
            }
        }
        searchGame(gameList)
    }