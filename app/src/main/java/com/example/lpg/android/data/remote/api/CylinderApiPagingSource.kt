package com.example.lpg.android.data.remote.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.lpg.android.data.model.GasItem

class CylinderApiPagingSource(
    private val cylinderApiService: CylinderApiService,
) : PagingSource<Int, GasItem>() {

    override fun getRefreshKey(state: PagingState<Int, GasItem>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1) ?: state.closestPageToPosition(
                position
            )?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GasItem> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val cylinders = cylinderApiService.getCylinders(page, pageSize)
            LoadResult.Page(
                data = cylinders,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (cylinders.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


}