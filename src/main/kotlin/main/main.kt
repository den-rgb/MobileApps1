package org.setu.placemark

import controller.GameController
import models.Game
//import models.LibMemStore
import mu.KotlinLogging
import java.util.*
import kotlin.collections.ArrayList

private val logger = KotlinLogging.logger {}
val red = "\u001b[31m"
val box = "\u001b[52m"

//val games = LibMemStore()
val controller = GameController()
fun main(args: Array<String>){
    controller.menu()
}
