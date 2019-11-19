package com.ignaciosiel.theforkchallenge.mvp

interface BaseView {
    fun attachPresenter()
    fun showLoading()
    fun hideLoading()
}