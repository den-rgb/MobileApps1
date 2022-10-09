package models

import org.junit.jupiter.api.Assertions.*


internal class LibMemStoreTest {
    var memStore = LibMemStore()
    var game1: Game? = null
    var game2: Game? = null
    var game3: Game? = null
    var samePrice: ArrayList<Game> = arrayListOf()
    var sameCategory: ArrayList<Game> = arrayListOf()
    var newGame=Game()
    @org.junit.jupiter.api.BeforeEach
    fun setUp() {
         game1 = Game("COD",1,20f,Category.Action)
         game2 = Game("Minecraft",2,40f,Category.Action)
         game3 = Game("FIFA",3,40f,Category.Sport)
         newGame = Game("Mario",0,25f,Category.Sandbox)
        memStore.games.add(game1!!)
        memStore.games.add(game2!!)
        memStore.games.add(game3!!)

        samePrice.add(game2!!)
        samePrice.add(game3!!)

        sameCategory.add(game1!!)
        sameCategory.add(game2!!)
    }

    @org.junit.jupiter.api.AfterEach
    fun tearDown() {
        game1  = null
        game3 = null
        game2 = null
        memStore.games.clear()
        samePrice.clear()
    }

    @org.junit.jupiter.api.Test
    fun findAll() {
        assertEquals(memStore.findAll().size,3)
        memStore.games.remove(game2)
        assertEquals(memStore.findAll().size,2)
    }

    @org.junit.jupiter.api.Test
    fun findByID() {
        assertEquals(memStore.findByID(game1?.id!!),game1)
        assertNotEquals(memStore.findByID(game2?.id!!),game3)
        assertEquals(memStore.findByID(0), null)
    }

    @org.junit.jupiter.api.Test
    fun findByPrice() {
        assertEquals(memStore.findByPrice(40f),samePrice)
        samePrice.clear()
        assertEquals(memStore.findByPrice(2f),samePrice)
    }

    @org.junit.jupiter.api.Test
    fun findByCategory() {
        assertEquals(memStore.findByCategory(Category.Action),sameCategory)
        sameCategory.clear()
        assertEquals(memStore.findByCategory(Category.Sandbox),sameCategory)
    }

    @org.junit.jupiter.api.Test
    fun findByName() {
        assertEquals(memStore.findByName("COD"),game1)
        assertEquals(memStore.findByName("cod"),game1)
    }

    @org.junit.jupiter.api.Test
    fun create() {
        memStore.create(newGame)
        assertTrue(memStore.games.contains(newGame))
    }
}