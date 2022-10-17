
package views

import controller.GameController
import javafx.scene.control.Button
import javafx.scene.layout.Background
import javafx.scene.layout.VBox
import tornadofx.*
import javafx.scene.paint.*
import javafx.scene.shape.ArcType
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import models.Category
import models.Game
import models.JsonMemStore
import tornadofx.Stylesheet.Companion.customcolorControlsBackground
import tornadofx.Stylesheet.Companion.left
import java.awt.event.ActionListener
import java.io.File


class MasterView: View() {
    val games = GameController()
    val view = games.gameView
    var textA= textarea()
    var inName = textfield()
    var inPrice = textfield()
    var inCategory = textfield()
    var inId = textfield()
    var submit = button()
    var nam = vbox()
    var pri = vbox()
    var cat = vbox()
    var idBox = vbox()
    val modGame = Game()
    var lastPressed = 0


   override val root = borderpane {

       left = vbox {
           vboxConstraints {
               paddingTop = 20.0
               spacing = 15.0

               button("Add Game") {
                   useMaxWidth = true
                   setPrefSize(150.0, 30.0)
                   textFill = Color.YELLOW
                   style{
                       backgroundImage+=File("src/black.jpg").toURI()
                   }

                   action {
                       lastPressed = 1
                       listCat()
                        show()
                   }
               }
               button("Update Game") {
                   useMaxWidth = true
                   setPrefSize(150.0, 30.0)
                   textFill = Color.YELLOW
                   style{
                       backgroundImage+=File("src/black.jpg").toURI()
                   }
                   action {
                       lastPressed = 3
                       idBox.isVisible = true
                       submit.isVisible = true
                       show()
                       listAll()
                   }
               }
               button("List Collection") {
                   useMaxWidth = true
                   setPrefSize(150.0, 30.0)
                   textFill = Color.YELLOW
                   style{
                       backgroundImage+=File("src/black.jpg").toURI()
                   }
                   action {
                       listAll()
                   }
               }
               button("Remove Game") {
                   useMaxWidth = true
                   setPrefSize(150.0, 30.0)
                   textFill = Color.YELLOW
                   style{
                       backgroundImage+=File("src/black.jpg").toURI()
                   }
                   action {
                        lastPressed = 2
                        idBox.isVisible = true
                       submit.isVisible = true
                       listAll()
                   }
               }
               button("Clear library") {
                   useMaxWidth = true
                   setPrefSize(150.0, 30.0)
                   textFill = Color.YELLOW
                   style{
                       backgroundImage+=File("src/black.jpg").toURI()
                   }

                   action {
                       games.clearLib()
                       textA.text = "Library is now empty...."
                   }
               }
               button("Search Library") {
                   useMaxWidth = true
                   setPrefSize(150.0, 30.0)
                   textFill = Color.YELLOW
                   style{
                       backgroundImage+=File("src/black.jpg").toURI()
                   }
               }


           }
       }

       top = vbox {

           arc {
               centerX = 200.0
               centerY = 200.0
               radiusX = 50.0
               radiusY = 50.0
               startAngle = 45.0
               fill = Color.YELLOW
               length = 280.0
               type = ArcType.ROUND
           }
       }


       center = vbox {
           circle {
               centerX = 100.0
               centerY = 100.0
               radius = 10.0
               paddingLeft = -30
               paddingTop = -65
               fill = Color.PURPLE
           }

            vbox {
                circle {
                    centerX = 100.0
                    centerY = 100.0
                    radius = 10.0
                    paddingLeft = 40
                    paddingTop = -20
                    fill = Color.PURPLE
                }
            }



            right = gridpane {
                gridpaneConstraints {

                    setPrefSize(400.0, 100.0)
                    textA = textarea {
                        setPrefSize(350.0,150.0)
                    }
                    label {
                        text = "Pac Games"
                        textFill = Color.YELLOW
                        //fontWeight += FontWeight.BOLD
                        font = Font(40.0)
                        paddingTop = -255
                        paddingLeft = 50.0
                    }



                    }



           }

           bottom = gridpane {
               vbox  {
                   vboxConstraints {
                        paddingLeft = 200
                        paddingBottom = 100
                       nam = vbox {
                           vboxConstraints {
                               paddingRight = 50
                               isVisible = false
                               label {
                                   text = "Name:"
                                   textFill = Color.YELLOW
                               }

                               inName = textfield() {
                                   textProperty().addListener { obs, old, new -> println("You typed: " + new) }
                               }
                           }
                       }

                       pri = vbox {
                           vboxConstraints {
                               paddingLeft = 50
                               isVisible = false
                               label {
                                   text = "Price:"
                                   textFill = Color.YELLOW
                               }

                               inPrice = textfield() {
                                   textProperty().addListener { obs, old, new -> println("You typed: " + new) }
                               }
                           }
                       }

                       cat = vbox {
                           vboxConstraints {
                               isVisible = false
                               paddingLeft = 100
                                paddingRight = -100
                               label {
                                   text = "Category:"
                                   textFill = Color.YELLOW
                               }

                               inCategory = textfield() {
                                   textProperty().addListener { obs, old, new -> println("You typed: " + new) }
                               }


                           }
                       }
                       vbox {
                           vboxConstraints {

                               paddingLeft = 100
                               paddingRight = -100

                               submit = button("Submit") {
                                   isVisible = false
                                   useMaxWidth = true
                                   setPrefSize(150.0, 30.0)
                                   textFill = Color.YELLOW
                                   style{
                                       backgroundImage+=File("src/black.jpg").toURI()
                                   }
                                   action {
                                       if(lastPressed ==1) {
                                           modGame.name = inName.text
                                           modGame.price = inPrice.text.toFloat()
                                           modGame.category = getCat()
                                           games.games.create(modGame)
                                           textA.appendText("Successfully added " + modGame.name)
                                       }
                                       else if(lastPressed ==2){
                                           games.games.findByID(inId.text.toInt())?.let { games.games.remove(it) }
                                           textA.appendText("Successfully deleted selected game")
                                       }
                                       else if(lastPressed==3){
                                           val updated = games.games.findByID(inId.text.toInt())
                                           if (updated != null) {
                                               updated.name = inName.text
                                           }
                                           if (updated != null) {
                                               updated.price = inPrice.text.toFloat()
                                           }
                                           if (updated != null) {
                                               updated.category = getCat()
                                           }

                                           if (updated != null) {
                                               textA.appendText("Successfully updated " + updated.name)
                                           }
                                       }
                                       hide()
                                   }
                               }


                           }
                       }
                       idBox = vbox {
                           vboxConstraints {
                               isVisible = false
                               paddingLeft = 100
                               paddingRight = -105
                               paddingTop = 25
                               label {
                                   text = "Id:"
                                   textFill = Color.YELLOW
                               }


                               inId = textfield() {
                                   textProperty().addListener { obs, old, new -> println("You typed: " + new) }
                               }


                           }
                       }
                   }
               }
           }


       }


       setPrefSize(500.0, 500.0)

       style{
           backgroundImage+= File("src/black.jpg").toURI()

       }

   }
    fun show(){

        textA.text = ""
        nam.isVisible = true
        pri.isVisible = true
        cat.isVisible = true
        submit.isVisible = true
    }

    fun hide(){
        nam.isVisible = false
        pri.isVisible = false
        cat.isVisible = false
        submit.isVisible = false
        idBox.isVisible = false
    }

    fun listAll(){
        textA.text = ""
        for (g in games.games.games){
            textA.appendText(g.name  +" --- $" + g.price + " --- " + g.id + " --- " + g.category + "\n")
        }
    }

    fun listCat(){
        textA.appendText("--- Categories ---\n")
        val size = Category.values().sortedArray()
        var index = 1
        for(c in size) {
            textA.appendText("----" +index+ ": " + c + "\n")
            index++
        }
    }
    fun getCat(): Category {
        var cat = Category.None
        when(inCategory.text.toString().toInt()){
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
}

