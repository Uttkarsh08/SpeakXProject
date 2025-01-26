package com.example.speakxproject.domain.repository

import androidx.paging.Pager
import com.example.speakxproject.data.model.Item
import kotlinx.coroutines.flow.StateFlow

interface ItemRepository {

    fun getItemsPager(): Pager<Int, Item>

    fun searchItems(query: String): Pager<Int, Item>

    fun gethasMore(): StateFlow<Boolean>

    fun getSearchHasMore(): StateFlow<Boolean>
}