package com.indatacore.indatacoreindatacore.services

import androidx.lifecycle.MutableLiveData
import com.indatacore.indatacoreindatacore.R
import com.indatacore.indatacoreindatacore.data.SliderItem

class PackService {
    var sliderItemListMVVM : MutableLiveData<ArrayList<SliderItem>> = MutableLiveData()
    var sliderItemMVVM : MutableLiveData<SliderItem> = MutableLiveData()

    fun getSliderItemList(): MutableLiveData<ArrayList<SliderItem>> {
        var sliderItemList: ArrayList<SliderItem> = ArrayList()
        val list: ArrayList<String> = ArrayList()
        list.add("Retraits et paiements au Maroc et à l'étranger")
        list.add("E-paiement sur les sites nationaux et internationaux")

        list.add("Des plafonds ajustables selon vos besoins: Au Maroc, jisqu'à 5 000 Dhs de retraits par jour et jusqu'à 10 000Dhs de paiements par semaine et à l'étranger dans la limite de votre dotation à l'international")
        list.add("Vos retraits gratuits dans tous les Guichets automatiques bancaires BMCI")
        val sliderItemList_1: SliderItem = SliderItem(1,(R.drawable.image1), "PACK REFLEX 25", "PAYPAL", false, list)
        sliderItemList.add(sliderItemList_1)

        val list2: ArrayList<String> = ArrayList()
        list2.add("Retraits et paiements au Maroc et à l'étranger(France, UA, USA)")
        list2.add("E-paiement sur les sites internationaux")

        list2.add("Des plafonds ajustables selon vos besoins: Au Maroc, jisqu'à 5 000 Dhs de retraits par jour et jusqu'à 10 000Dhs de paiements par semaine et à l'étranger dans la limite de votre dotation à l'international")
        list2.add("Vos retraits gratuits dans tous les Guichets automatiques bancaires BMCI")
        val sliderItemList_2 = SliderItem(2, R.drawable.image2, "PACK REFLEX 35", "CAREDIT", false, list2)
        sliderItemList.add(sliderItemList_2)

        val list3: ArrayList<String> = ArrayList()
        list3.add("Retraits et paiements au France")
        list3.add("E-paiement sur les sites nationaux et internationaux(Maroc)")

        list3.add("Des plafonds ajustables selon vos besoins: Au Maroc, jisqu'à 5 000 Dhs de retraits par jour et jusqu'à 10 000Dhs de paiements par semaine et à l'étranger dans la limite de votre dotation à l'international")
        list3.add("Vos retraits gratuits dans tous les Guichets automatiques bancaires BMCI")
        val sliderItemList_3 = SliderItem(3, R.drawable.image3, "PACK REFLEX 45", "VIRTUAL CARD", true, list3)
        sliderItemList.add(sliderItemList_3)

        val list4: ArrayList<String> = ArrayList()
        list4.add("Retraits et paiements au Maroc")
        list4.add("E-paiement sur les sites nationaux")

        list4.add("Des plafonds ajustables selon vos besoins: Au Maroc, jisqu'à 5 000 Dhs de retraits par jour et jusqu'à 10 000Dhs de paiements par semaine et à l'étranger dans la limite de votre dotation à l'international")
        list4.add("Vos retraits gratuits dans tous les Guichets automatiques bancaires BMCI")
        val sliderItemList_4 = SliderItem(4, R.drawable.image4, "PACK REFLEX 55", "CIH BANK", false, list4)
        sliderItemList.add(sliderItemList_4)

        val list5: ArrayList<String> = ArrayList()
        list5.add("Retraits et paiements au Maroc")
        list5.add("E-paiement sur les sites nationaux et internationaux")

        list5.add("Des plafonds ajustables selon vos besoins: Au Maroc, jisqu'à 5 000 Dhs de retraits par jour et jusqu'à 10 000Dhs de paiements par semaine et à l'étranger dans la limite de votre dotation à l'international")
        list5.add("Vos retraits gratuits dans tous les Guichets automatiques bancaires BMCI")
        val sliderItemList_5 = SliderItem(5, R.drawable.image5, "PACK REFLEX 65", "CIH BANK", false, list5)
        sliderItemList.add(sliderItemList_5)

        val list6: ArrayList<String> = ArrayList()
        list6.add("Retraits et paiements au Maroc et à l'étranger")
        list6.add("E-paiement sur les sites nationaux et internationaux")

        list6.add("Des plafonds ajustables selon vos besoins: Au Maroc, jisqu'à 5 000 Dhs de retraits par jour et jusqu'à 10 000Dhs de paiements par semaine et à l'étranger dans la limite de votre dotation à l'international")
        list6.add("Vos retraits gratuits dans tous les Guichets automatiques bancaires BMCI")
        val sliderItemList_6 = SliderItem(6, R.drawable.image6, "PACK REFLEX 75", "CIH BANK", false, list6)
        sliderItemList.add(sliderItemList_6)

        sliderItemListMVVM.value = sliderItemList

        return sliderItemListMVVM

    }

}