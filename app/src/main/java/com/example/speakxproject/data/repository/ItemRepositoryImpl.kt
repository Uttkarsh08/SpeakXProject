package com.example.speakxproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.speakxproject.data.api.MockApi
import com.example.speakxproject.data.model.Item
import com.example.speakxproject.domain.repository.ItemRepository
import com.example.twowaypagination.data.paging.ItemPagingSource

class ItemRepositoryImpl(private  val api: MockApi): ItemRepository {
    override fun getItemsPager(): Pager<Int, Item> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ItemPagingSource(api) }
        )
    }
}
