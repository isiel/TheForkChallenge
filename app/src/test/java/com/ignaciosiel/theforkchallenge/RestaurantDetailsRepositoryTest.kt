package com.ignaciosiel.theforkchallenge

import com.ignaciosiel.theforkchallenge.data.model.RestaurantDetails
import com.ignaciosiel.theforkchallenge.data.model.RestaurantResult
import com.ignaciosiel.theforkchallenge.domain.restaurant.details.RestaurantDetailsRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Repository unit test.
 * I tried to test the api call, but I couldn't mock the api (because it was lazy)
 * I tried reflection with robolectric to set the field inside the repository spy, but it didn't work :(
 */
class RestaurantDetailsRepositoryTest {

    lateinit var repository: RestaurantDetailsRepository

    @Mock
    lateinit var listener: DetailsListenerStub

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        repository = spy(RestaurantDetailsRepository())
    }

    @Test
    fun testSuccessResult_shouldCallSuccessListener() {
        val expectedResult = RestaurantResult(RestaurantDetails(id = 0L, name = ""))

        repository.handleResponse(expectedResult, listener)
        verify(listener).onSuccess(expectedResult.data)
    }

    @Test
    fun testErrorResult_shouldCallErrorListener() {
        repository.handleError(listener)

        verify(listener).onError()
    }

    class DetailsListenerStub : RestaurantDetailsRepository.DetailsListener {
        override fun onError() {
            //Stub implementation
        }

        override fun onSuccess(restaurantDetails: RestaurantDetails) {
            //Stub implementation
        }

    }
}