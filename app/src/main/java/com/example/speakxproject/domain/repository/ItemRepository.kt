package com.example.speakxproject.domain.repository

import androidx.paging.Pager
import com.example.speakxproject.data.model.Item

interface ItemRepository {

    fun getItemsPager(): Pager<Int, Item>
}