package com.example.speakxproject.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.speakxproject.data.api.MockApi
import com.example.speakxproject.data.model.Item

class SearchItemPagingSource(
    private val mockApi: MockApi,
    private val query: String,
) : PagingSource<Int, Item>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val id = params.key ?: 1

            val direction = if (id >= 1) "down" else "up"
            val response = mockApi.searchItems(id, query)
            val items = response.data

            val nextKey = if (response.hasMore) {
                val nextItem = items.lastOrNull()
                nextItem?.id?.plus(1)
            } else {
                null
            }

            val prevKey = if (id > 1) {
                val prevItem = items.firstOrNull()
                prevItem?.id?.minus(1)
            } else {
                null
            }

            Log.d("SearchPagingSource", "NextKey: $nextKey, PrevKey: $prevKey")

            LoadResult.Page(
                data = items,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return null
    }
}