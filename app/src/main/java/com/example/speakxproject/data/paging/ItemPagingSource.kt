package com.example.twowaypagination.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.speakxproject.data.api.MockApi
import com.example.speakxproject.data.model.ApiResponse
import com.example.speakxproject.data.model.Item


class ItemPagingSource(
    private val api: MockApi
) : PagingSource<Int, Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {

        return try {

            val id = params.key?: 1

            val response: ApiResponse = api.fetchItems(id)
            val items = response.data
            val hasMore = response.hasMore

            val nextKey = if (hasMore) id+10 else null
            val prevKey = if (id >1) id-10 else null

            Log.d("SearchPagingSource", "Fetched items: ${items.size}, NextKey: ${items.lastOrNull()?.id}, PrevKey: ${items.firstOrNull()?.id}")

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
