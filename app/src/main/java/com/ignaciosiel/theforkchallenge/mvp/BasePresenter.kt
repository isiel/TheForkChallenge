package com.ignaciosiel.theforkchallenge.mvp

interface BasePresenter {

    fun detachView()
    fun attachView(view: BaseView)
}