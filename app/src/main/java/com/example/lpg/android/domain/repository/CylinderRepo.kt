package com.example.lpg.android.domain.repository

import androidx.paging.Pager
import com.example.lpg.android.data.model.GasItem

interface CylinderRepo {
    fun getCylinders(): Pager<Int, GasItem>
}