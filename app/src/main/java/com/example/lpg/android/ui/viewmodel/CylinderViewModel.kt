package com.example.lpg.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.lpg.android.data.remote.api.CylinderApiService
import com.example.lpg.android.data.remote.repo.CylinderRepository
import com.example.lpg.android.domain.repository.CylinderRepo

class CylinderViewModel(
    cylinderRepo: CylinderRepo
) : ViewModel() {

    val gasItems = cylinderRepo.getCylinders().flow.cachedIn(viewModelScope)

    @Suppress("UNCHECKED_CAST")
    class Factory(private val cylinderApiService: CylinderApiService): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val cylinderRepo = CylinderRepository(cylinderApiService)
            return CylinderViewModel(cylinderRepo) as T
        }
    }
}