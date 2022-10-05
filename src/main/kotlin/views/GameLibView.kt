package views

import models.Category
import models.Game
import org.setu.placemark.box
import org.setu.placemark.red

class GameLibView {

    fun menuView() :Int{
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
        return readLine()!!.toInt()
    }

    fun addGameView(game: Game){
        println(box +"Enter game details...." )
        print("Name: ")
        game.name = readln()

        print("Price: ")
        game.price = readLine()!!.toFloat()

    }

    fun chooseCategoryView()  :Int{
        print(" --------> ")
        return readLine()!!.toInt()
    }

    fun listCategoriesView(){
        println(" --- Categories --- ")
    }

    fun listView(){
        println("---------- Your Games --------------")
        println()
    }

    fun updateGameViewChoice():Int{
        println(" Choose a game to update ")
        print( "---Game Id: ")
        return readLine()!!.toInt()
    }

    fun updateGameView():Int{
        println(" What would you like to update?? ")
        println( " ---> 1: Name")
        println( " ---> 2: Price")
        println( " ---> 3: Category")
        println( " ---> 4: Back")
        return readLine()!!.toInt()
    }

    fun updateNameView(): String{
        print( "New Game name --> ")
        return readLine()!!
    }

    fun updatePriceView(): Float{
        print( "New Game price --> ")
        return readLine()!!.toFloat()
    }

    fun updateCategoryView(){
        print( "New Game Category --> ")
    }

    fun removeGameView(): Int {
        println("Which game would you like to remove [id]")
        print("------> ")
        return readLine()!!.toInt()
    }

    fun searchGameView(): Int{
        println()
        println(" Search by: ")
        println("----1. ID ")
        println("----2. Name ")
        println("----3. Price ")
        println("----4. Category ")
        println("----5. Back ")
        return readLine()!!.toInt()
    }

    fun searchNameView(): String{
        println()
        println("-----Name: ")
        return readLine()!!
    }

    fun searchIdView(): Int{
        println()
        println("-----Id: ")
        return readLine()!!.toInt()
    }

    fun searchPriceView(): Float{
        println()
        println("-----Price: ")
        return readLine()!!.toFloat()
    }

    fun searchCategoryView(): Int{
        println("Choose a Category")
        val input = readln()
        if (input.intOrString())
        return readLine()!!.toInt()
    }

    fun String.intOrString() = toIntOrNull() ?: this

}