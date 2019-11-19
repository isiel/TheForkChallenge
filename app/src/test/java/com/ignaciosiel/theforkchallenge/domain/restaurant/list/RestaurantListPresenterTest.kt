package com.ignaciosiel.theforkchallenge.domain.restaurant.list

import com.ignaciosiel.theforkchallenge.TestUtils
import com.ignaciosiel.theforkchallenge.data.model.RestaurantDetailsResumed
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.util.ReflectionHelpers

class RestaurantListPresenterTest {

    @Mock
    lateinit var repository: RestaurantListRepository

    @Mock
    lateinit var view: RestaurantListView

    lateinit var presenter: RestaurantListPresenter

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        presenter = Mockito.spy(RestaurantListPresenter(view, repository))
    }

    @Test
    fun testGetRestaurants_withRestaurantsNull_shouldCallRepository() {
        val expectedRestaurants = arrayListOf(RestaurantDetailsResumed(1234L, "name", "picture", "specialty"))

        Mockito.`when`(repository.getRestaurants()).thenReturn(expectedRestaurants)

        presenter.getRestaurants()

        verify(view).showLoading()
        verify(repository).getRestaurants()
        verify(view).hideLoading()
        verify(view).showRestaurants(TestUtils.eq(expectedRestaurants))
    }

    @Test
    fun testGetRestaurants_withRestaurantsNotNull_shouldNotCallRepository() {
        val expectedRestaurants = arrayListOf(RestaurantDetailsResumed(1234L, "name", "picture", "specialty"))
        ReflectionHelpers.setField(presenter, "lastSearchedRestaurants", expectedRestaurants)

        presenter.getRestaurants()

        verify(view).showLoading()
        verify(repository, times(0)).getRestaurants()
        verify(view).hideLoading()
    }

    @Test
    fun testAttachView_viewShouldBeAttached() {
        presenter.attachView(view)

        Assert.assertEquals(presenter.listView, view)
    }

    @Test
    fun testDetachView_viewShouldBeNull() {
        presenter.detachView()

        TestCase.assertNull(presenter.listView)
    }
}