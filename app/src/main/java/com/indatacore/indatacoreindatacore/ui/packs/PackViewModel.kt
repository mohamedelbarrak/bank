package com.indatacore.indatacoreindatacore.ui.packs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.indatacore.indatacoreindatacore.data.SliderItem
import com.indatacore.indatacoreindatacore.repositorys.PackRepository

class PackViewModel: ViewModel() {
    val packRepository = PackRepository()

    fun getSliderItemList(): MutableLiveData<ArrayList<SliderItem>> {
        return packRepository.getSliderItemList()
    }


}