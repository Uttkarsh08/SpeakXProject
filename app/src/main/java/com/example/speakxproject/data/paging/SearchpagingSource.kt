package com.example.speakxproject.data.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.speakxproject.data.api.MockApi
import com.example.speakxproject.data.model.Item
import kotlinx.coroutines.flow.MutableStateFlow

class SearchItemPagingSource(
    private val mockApi: MockApi,
    private val query: String,
    private val _SearchhasMore: MutableStateFlow<Boolean>,
) : PagingSource<Int, Item>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {

            val id = params.key ?: 1
            val response = mockApi.searchItems(id, query)
            val items = response.data
            val searchHasMore = response.hasMore

            _SearchhasMore.value = searchHasMore

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