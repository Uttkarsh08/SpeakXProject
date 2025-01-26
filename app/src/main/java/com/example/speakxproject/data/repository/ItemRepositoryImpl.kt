package com.example.speakxproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.speakxproject.data.api.MockApi
import com.example.speakxproject.data.model.Item
import com.example.speakxproject.data.paging.SearchItemPagingSource
import com.example.speakxproject.domain.repository.ItemRepository
import com.example.twowaypagination.data.paging.ItemPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

class ItemRepositoryImpl(private val api: MockApi) : ItemRepository {

    private val _hasMore = MutableStateFlow(true)
    private val _SearchhasMore = MutableStateFlow(true)

    override fun getItemsPager(): Pager<Int, Item> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ItemPagingSource(api, _hasMore) }
        )
    }

    override fun searchItems(query: String): Pager<Int, Item> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { SearchItemPagingSource(api, query, _SearchhasMore) }
        )
    }

    override fun gethasMore(): StateFlow<Boolean> {
        return _hasMore
    }

    override fun getSearchHasMore(): StateFlow<Boolean> {
        return _SearchhasMore
    }

}
