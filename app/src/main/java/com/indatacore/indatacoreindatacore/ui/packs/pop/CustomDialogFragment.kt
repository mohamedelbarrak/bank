package com.indatacore.indatacoreindatacore.ui.packs.pop

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.indatacore.indatacoreindatacore.R
import com.indatacore.indatacoreindatacore.data.SliderItem

class CustomDialogFragment(sliderItem: SliderItem) : DialogFragment() {
    var sliderItem = sliderItem
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //return super.onCreateView(inflater, container, savedInstanceState)
        var rootView: View = inflater.inflate(R.layout.fragment_dustem_dialog, container, false)
        //rootView.cancel.
        var cancel = rootView.findViewById<View>(R.id.cancel)
        cancel.setOnClickListener{
            dismiss()
        }

/*        var details: Button = rootView.findViewById<Button>(R.id.buttonDetails1)
        details.setOnClickListener{
            Log.e("addPanier", "addPanier")
            //dilog(sliderItemList[position - 1])
        }*/

        var addPanier: Button = rootView.findViewById<Button>(R.id.addPanier)
        addPanier.setOnClickListener{
            Log.e("addPanier", "addPanier")
            if(sliderItem.panier){
                addPanier.setTextColor(Color.parseColor("#FB8704"))
                addPanier.text = "Ajouter au panier"
                // TODO: Back up role pour que valeur mintien, modifier panier to true
                sliderItem.panier = false
            }else{
                addPanier.setTextColor(Color.parseColor("#D50000"))
                addPanier.text = "Retirer du panier"
                // TODO: Back up role, modifier panier to false
                sliderItem.panier = true
            }
        }

        var textViewPackName: TextView = rootView.findViewById<TextView>(R.id.textViewPackName)
        textViewPackName.text = sliderItem.packName

        var textViewType: TextView = rootView.findViewById<TextView>(R.id.textViewType)
        textViewType.text = sliderItem.typeCard

        var image: ImageView = rootView.findViewById<ImageView>(R.id.imageViewDetails)
        image.setImageResource(sliderItem.img)

        var textViewCharacteristicOn: TextView = rootView.findViewById<TextView>(R.id.textViewCharacteristicOn)
        textViewCharacteristicOn.text = sliderItem.characteristic[0]

        var textViewCharacteristicTwo: TextView = rootView.findViewById<TextView>(R.id.textViewCharacteristicTwo)
        textViewCharacteristicTwo.text = sliderItem.characteristic[1]

        var textViewCharacteristicTree: TextView = rootView.findViewById<TextView>(R.id.textViewCharacteristicTree)
        textViewCharacteristicTree.text = sliderItem.characteristic[2]

        var textViewCharacteristicFour: TextView = rootView.findViewById<TextView>(R.id.textViewCharacteristicFour)
        textViewCharacteristicFour.text = sliderItem.characteristic[3]


        if(sliderItem.panier){
            addPanier.setTextColor(Color.parseColor("#D50000"))
            addPanier.text = "Retirer du panier"
        }else{
            addPanier.setTextColor(Color.parseColor("#FB8704"))
            addPanier.text = "Ajouter au panier"
        }

        return rootView
    }
}