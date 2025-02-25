package com.example.lpg.android.data.remote.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.lpg.android.data.model.GasItem
import com.example.lpg.android.data.remote.api.CylinderApiPagingSource
import com.example.lpg.android.data.remote.api.CylinderApiService
import com.example.lpg.android.domain.repository.CylinderRepo

class CylinderRepository(
    private val cylinderApiService: CylinderApiService,
) : CylinderRepo {

    override fun getCylinders(): Pager<Int, GasItem> {
        println("invoked")
        return Pager(
            config = PagingConfig(pageSize = 5, enablePlaceholders = false),
            pagingSourceFactory = { CylinderApiPagingSource(cylinderApiService) }
        )
    }

}