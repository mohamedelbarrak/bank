package com.indatacore.indatacoreindatacore.repositorys

import androidx.lifecycle.MutableLiveData
import com.indatacore.indatacoreindatacore.data.SliderItem
import com.indatacore.indatacoreindatacore.services.PackService

class PackRepository {
    val packService = PackService()

    fun getSliderItemList(): MutableLiveData<ArrayList<SliderItem>> {
        return packService.getSliderItemList()
    }

}