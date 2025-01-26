package com.example.speakxproject.data.api

import com.example.speakxproject.data.model.ApiResponse
import com.example.speakxproject.data.model.Item
import kotlinx.coroutines.delay

class MockApi {

    private val MAX_ID = 50

    suspend fun fetchItems(id: Int): ApiResponse{
        delay(1200)

        val items = generateItems(id)

        val hasMore = items.isNotEmpty() && items.last().id < MAX_ID

        return ApiResponse(
            data = items,
            hasMore = hasMore
        )
    }


    private fun generateItems(id: Int): List<Item> {
        return List(10) { Item(id = id + it, title = "Item ${id + it}") }
    }

    suspend fun searchItems(id: Int, query: String): ApiResponse {
        delay(800)

        val all = (1..MAX_ID).map { Item(it, "Item $it") }
        val filteredItems = all.filter { it.title.contains(query, ignoreCase = true) }

        val items = filteredItems.filter { it.id> id }.take(10)

        val searchHasMore = filteredItems.isNotEmpty() && items.last().id <= MAX_ID

        return ApiResponse(items, searchHasMore)
    }

}