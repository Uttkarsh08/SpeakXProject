package com.example.speakxproject.data.api

import com.example.speakxproject.data.model.ApiResponse
import com.example.speakxproject.data.model.Item
import kotlinx.coroutines.delay

class MockApi {

    private val MAX_ID = 200
    private val MIN_ID = 0

    suspend fun fetchItems(id: Int, direction: String): ApiResponse{
        delay(2000)

        val items = when (direction) {
            "up" -> generateUpwardItems(id)
            "down" -> generateDownwardItems(id)
            else -> emptyList()
        }

        val hasMore = when (direction) {
            "up" -> items.isNotEmpty() && items.first().id!! > MIN_ID
            "down" -> items.isNotEmpty() && items.last().id!! < MAX_ID
            else -> false
        }

        return ApiResponse(
            data = items,
            hasMore = hasMore
        )
    }

    private fun generateUpwardItems(id: Int?): List<Item> {
        val startId = id ?: 100
        return List(10) { Item(id = startId - it, title = "Item ${startId - it}") }
    }

    private fun generateDownwardItems(id: Int?): List<Item> {
        val startId = id ?: 1000
        return List(10) { Item(id = startId + it, title = "Item ${startId + it}") }
    }
}