package com.ignaciosiel.theforkchallenge.domain.restaurant.details

import com.ignaciosiel.theforkchallenge.domain.restaurant.utils.TestUtils
import com.ignaciosiel.theforkchallenge.data.model.RestaurantDetails
import com.ignaciosiel.theforkchallenge.domain.restaurant.details.RestaurantDetailsRepository.DetailsListener
import junit.framework.TestCase.assertNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class RestaurantDetailsPresenterTest {

    @Mock
    lateinit var repository: RestaurantDetailsRepository

    @Mock
    lateinit var view: RestaurantDetailsView

    lateinit var presenter: RestaurantDetailsPresenter

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        presenter = spy(RestaurantDetailsPresenter(view, repository))
    }

    @Test
    fun testGetRestaurantDetails_withLastSearchNull_shouldGoToRepository() {
        presenter.getRestaurantDetails("1234")

        verify(view)?.showLoading()
        verify(repository).getRestaurantDetails(
            TestUtils.eq("1234"),
            TestUtils.any(DetailsListener::class.java)
        )

    }

    @Test
    fun testGetRestaurantDetails_withLastSearchNotNull_shouldReturnLastSearch() {

        val expectedRestaurant = RestaurantDetails(1234L, "name")

        presenter.lastSearchedRestaurant = expectedRestaurant
        presenter.getRestaurantDetails("1234")

        verify(view)?.showLoading()
        verify(repository, never()).getRestaurantDetails(
            TestUtils.any(String::class.java),
            TestUtils.any(DetailsListener::class.java)
        )
        verify(presenter).returnRestaurantDetails(
            TestUtils.eq(
                expectedRestaurant
            )
        )
    }

    @Test
    fun testAttachView_viewShouldBeAttached() {
        presenter.attachView(view)

        assertEquals(presenter.detailsView, view)
    }

    @Test
    fun testDetachView_viewShouldBeNull() {
        presenter.detachView()

        assertNull(presenter.detailsView)
    }

    @Test
    fun testOnError_shouldCallViewOnError() {
        presenter.onError()

        verify(view).hideLoading()
        verify(view).showError()
    }

    @Test
    fun testOnSuccess_shouldSaveRestaurantCallViewSuccess() {
        val expectedRestaurant = RestaurantDetails(1234L, "name")

        presenter.onSuccess(expectedRestaurant)

        assertEquals(expectedRestaurant, presenter.lastSearchedRestaurant)
        verify(view).hideLoading()
    }

    @Test
    fun testReturnRestaurantDetails_shouldHideLoadingAndCallViewSuccess() {
        val expectedRestaurant = RestaurantDetails(1234L, "name")

        presenter.returnRestaurantDetails(expectedRestaurant)

        verify(view).hideLoading()
        verify(view).showRestaurantDetails(expectedRestaurant)
    }

}