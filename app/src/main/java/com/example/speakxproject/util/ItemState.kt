package com.example.speakxproject.util

import androidx.paging.PagingData
import com.example.speakxproject.data.model.Item
import kotlinx.coroutines.flow.Flow

sealed class ItemState {

    class Success(val data: Flow<PagingData<Item>>) : ItemState()
    class Error(val message: String) : ItemState()
    class Loading() : ItemState()
}