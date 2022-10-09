package controller

import models.Category
import models.Game
import models.JsonMemStore
import models.LibMemStore
import org.setu.placemark.*
import views.GameLibView
import kotlin.system.exitProcess

class GameController {
    val games = JsonMemStore()
    val gameView = GameLibView()

    fun menu(){
        do {
            val input = gameView.menuView()
            when(input) {
                1 ->  addGame()
                2 ->  updateGame()
                3 ->  listGames()
                4 -> removeGame()
                5 -> clearLib()
                6 -> searchGame()
                -1 -> println(" Exiting App ")
                else -> println(" Invalid Option ")
            }
            println()
        } while (input != -1)
        println( " Shutting Down Game Library " )
        exitProcess(0)
    }

    fun addGame(){
        val newGame= Game()
        gameView.addGameView(newGame)
        gameView.chosenCategoryView(games.chooseCategory(),newGame)
        games.create(newGame)

    }
    fun listGames(){
        gameView.listView()
        for (g in games.games){
            println("-------> "+g.name  +" --- $" + g.price + " --- id: " + g.id + " --- category: " + g.category)
        }
    }

    fun updateGame(){
        listGames()
        val id =gameView.updateGameViewChoice()
        val update = gameView.updateGameView()
        if (games.findByID(id)!=null){
            if (update<4){
                when (update) {
                1 -> games.updateName(id)
                2 -> games.updatePrice(id)
                3 -> games.updateCategory(id)
                4 -> menu()
                }
            }
        }
    }

    fun removeGame(){
        listGames()
        val id = gameView.removeGameView()
        if(games.findByID(id) != null) {
            games.remove(games.findByID(id)!!)
        }else{
            println(" -- Id or Name doesn't exist ?! --")
            removeGame()
        }
    }

    fun clearLib(){
        games.clear()
    }

    fun searchGame(){
        val input = gameView.searchGameView()
        if (input<6) {
            when (input) {
                1 -> println(games.findByID(gameView.searchIdView()))
                2 -> println(games.findByName(gameView.searchNameView()))
                3 -> for(g in games.findByPrice(gameView.searchPriceView())){println("Name: "+g.name + " --- Category: " + g.category + " --- id: " +g.id)}
                4 -> for(g in games.findByCategory(games.chooseCategory())){println("Name: "+g.name + " --- Price: " + g.price + " --- id: " +g.id)}
                5 -> menu()
            }
        }
    }


}


