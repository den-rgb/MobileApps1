package controller

import models.Game
import models.LibMemStore
import org.setu.placemark.*
import views.GameLibView

class GameController {
    val games = LibMemStore()
    val gameView = GameLibView()

    fun menu(){
        do {
            val input = gameView.menuView()
            when(input) {
                1 ->  addGame()
                2 ->  updateGame()
                3 ->  listGames()
//                4 -> removeGame()
//                5 -> clearLib()
//                6 -> searchGame()
                -1 -> println(" Exiting App ")
                else -> println(" Invalid Option ")
            }
            println()
        } while (input != -1)
       println( " Shutting Down Game Library " )
    }

    fun addGame(){
        val newGame= Game()
        gameView.addGameView(newGame)
        games.create(newGame)

    }

    fun listGames(){
        gameView.listView()
        for (g in games.games){
            println("-------> "+g.name  +" --- $" + g.price + " --- id: " + g.id)
        }
    }

    fun updateGame(){
        listGames()
        val id =gameView.updateGameViewChoice()
        val update = gameView.updateGameView()
        if (games.findByID(id)!=null){
            if (update<4){
                when (update) {
                1 -> updateName(games.findByID(id)!!)
                2 -> updatePrice(games.findByID(id)!!)
                3 -> menu()
                }
            }
        }
    }

    fun updateName(game: Game){
        game.name = gameView.updateNameView()
        print( "Successfully Updated Game Name")
    }

    fun updatePrice(game: Game){
        game.price = gameView.updatePriceView()
        print( "Successfully Updated Game Price")
    }
}