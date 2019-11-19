package com.ignaciosiel.theforkchallenge.domain.restaurant.utils

import org.mockito.Mockito


object TestUtils {

    /**
     * Method to receive a value when calling the eq method in Mockito
     */
    fun <T> eq(value : T): T {
        Mockito.eq(value)
        return uninitialized()
    }

    /**
     * Method to not receive a class when calling the any method in Mockito
     */
    fun <T> any(type : Class<T>): T {
        Mockito.any(type)
        return uninitialized()
    }

    fun <T> uninitialized(): T = null as T
}

