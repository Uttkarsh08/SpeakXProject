package com.example.speakxproject.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.speakxproject.data.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    fun getItemsPager(): Pager<Int, Item>
}