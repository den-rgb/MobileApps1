package org.setu.placemark

import controller.GameController
import models.Game
import models.LibMemStore
import mu.KotlinLogging
import java.util.*
import kotlin.collections.ArrayList

private val logger = KotlinLogging.logger {}
val red = "\u001b[31m"
val box = "\u001b[52m"

val games = LibMemStore()
val controller = GameController()
fun main(args: Array<String>){
    controller.menu()
}








//
//fun updateName(games: MutableMap<String, Game>, input : Int){
//    print( "New Game name --> ")
//    val name = readLine()!!
//    games[input.toString()] = Game(name,input,games.getValue("$input").price)
//}
//
//fun updatePrice(games: MutableMap<String, Game>, input : Int){
//    print( "New Game price--> ")
//    val price = readLine()!!
//    games[input.toString()] = Game(games.getValue("$input").name,input,price.toFloat())
//}
//
//fun removeGame(games: MutableMap<String, Game>){
//    println("Which game would you like to remove")
//    listGames()
//    var input = readLine()!!
//    print("------> ")
//    for (g in games){
//        if( g.value.name.equals(input) or (g.value.id.toString() == input)){
//            gameList.remove(g.key)
//        }else {
//            println(" Game id or Game name doesn't exist!")
//            removeGame(gameList)
//        }
//    }
//}
//
//fun clearLib(){
//    gameList.clear()
//}
//
//fun searchGame(games: MutableMap<String, Game>){
//    println()
//    println(" Search by: ")
//    println("----1. ID ")
//    println("----2. Name ")
//    println("----3. Price ")
//    println("----4. Category ")
//    println("----5. Back ")
//    val choice = readLine()!!
//    for (g in games){
//        if (choice.toIntOrNull() != null && choice.isNotEmpty()) {
//            when (choice.toInt()) {
//                1 -> searchId(gameList)
//                2 -> searchName(gameList)
//                3 -> searchPrice(gameList)
//                5 -> menu()
//            }
//        }
//    }
//}
//
//fun searchName(games: MutableMap<String, Game>){
//    println()
//    println("-----Name: ")
//    val input = readLine()!!
//    var i = 0
//    var x = 0
//    for (g in games){
//        if (input.isNotEmpty()) {
//            if (g.value.name.equals(input)) {
//                println("----Name: " + g.value.name + " ----Id: " + g.value.id + " ----Price: " + g.value.price)
//                i++
//                x++
//            } else {
//                i++
//            }
//
//            if (i == games.size && x == 0) {
//                println()
//                println("--- No Matches Found ---")
//                searchGame(gameList)
//            }
//        }else {
//            println( "--- Try writing something ---")
//            searchName(gameList)
//        }
//    }
//    searchGame(gameList)
//}
//
//fun searchId(games: MutableMap<String, Game>){
//    println()
//    println(" -----Id: ")
//    val input = readLine()!!
//    var i = 0
//    var x = 0
//    for (g in games){
//        if (input.toIntOrNull() != null && input.isNotEmpty()) {
//            if (g.value.id == (input.toInt())) {
//                println("----Name: " + g.value.name + " ----Id: " + g.value.id + " ----Price: " + g.value.price)
//                i++
//                x++
//            } else {
//                i++
//            }
//
//            if (i == games.size && x == 0) {
//                println()
//                println("--- No Matches Found ---")
//                searchGame(gameList)
//            }
//        }else{
//            println( "--- Try writing a number ---")
//            searchId(gameList)
//        }
//    }
//    searchGame(gameList)
//}
//
//fun searchPrice(games: MutableMap<String, Game>){
//    println()
//    println("-----Price: ")
//    val input = readLine()!!
//    var i = 0
//    var x = 0
//    for (g in games){
//        if (input.toFloatOrNull() != null && input.isNotEmpty()) {
//            if (g.value.price == (input.toFloat())) {
//                println("----Name: " + g.value.name + " ----Id: " + g.value.id + " ----Price: " + g.value.price)
//                i++
//                x++
//            } else {
//                i++
//            }
//
//            if (i == games.size && x == 0) {
//                println()
//                println("--- No Matches Found ---")
//                searchGame(gameList)
//            }
//        }else{
//            println( "--- Try writing a number ---")
//            searchPrice(gameList)
//        }
//    }
//    searchGame(gameList)
//}