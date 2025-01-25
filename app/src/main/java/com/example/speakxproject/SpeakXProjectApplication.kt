package com.example.speakxproject

import android.app.Application
import com.example.speakxproject.data.api.MockApi
import com.example.speakxproject.data.repository.ItemRepositoryImpl
import com.example.speakxproject.domain.repository.ItemRepository

class SpeakXProjectApplication : Application() {

    lateinit var repository: ItemRepository
        private set

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        val api: MockApi = MockApi()
        repository = ItemRepositoryImpl(api)
    }
}
