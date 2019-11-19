package com.ignaciosiel.theforkchallenge.domain.restaurant.list

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RestaurantListRepositoryTest {

    lateinit var repository: RestaurantListRepository

    @Before
    fun before() {
        repository = RestaurantListRepository()
    }

    @Test
    fun testGetRestaurants_shouldGetAllRestaurants() {
        val returnedRestaurants = repository.getRestaurants()

        assertEquals(4, returnedRestaurants.size)
        assertEquals(6861L, returnedRestaurants[0].id)
        assertEquals("Kathmandu Coffee" , returnedRestaurants[0].name)
        assertEquals("Indian", returnedRestaurants[0].specialty)
    }
}