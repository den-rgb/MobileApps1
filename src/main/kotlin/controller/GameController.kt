package controller

import models.Category
import models.Game
import models.LibMemStore
import org.setu.placemark.*
import views.GameLibView
import kotlin.system.exitProcess

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
        chosenCategory(chooseCategory(),newGame)
        games.create(newGame)

    }

    fun chooseCategory(): Category {
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

    fun chosenCategory(category: Category,game: Game){
        game.category = category
    }

    fun listCategories(){
        gameView.listCategoriesView()
        val size = Category.values().sortedArray()
        var index = 1
        for(c in size) {
            println("----" +index+ ": " + c)
            index++
        }
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
                1 -> updateName(games.findByID(id)!!)
                2 -> updatePrice(games.findByID(id)!!)
                3 -> updateCategory(games.findByID(id)!!)
                4 -> menu()
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

    fun updateCategory(game: Game){
        chooseCategory()                                //TODO
        print( "Successfully Updated Game Category")
    }

    fun removeGame(){
        listGames()
        val id = gameView.removeGameView()
        if(games.findByID(id) != null) {
            games.games.remove(games.findByID(id))
        }else{
            println(" -- Id or Name doesn't exist ?! --")
            removeGame()
        }
    }

    fun clearLib(){
        games.games.clear()
    }

    fun searchGame(){
        val input = gameView.searchGameView()
        if (input<6) {
            when (input) {
                1 -> println(games.findByID(gameView.searchIdView()))
                2 -> println(games.findByName(gameView.searchNameView()))
                3 -> println(games.findByPrice(gameView.searchPriceView()))
                4 -> searchByCategory()
                5 -> menu()
            }
        }
    }

    fun searchByCategory(){

        println(games.findByCategory(chooseCategory()))

    }

}


