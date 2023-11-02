package com.application.sallus_app.view.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.sallus_app.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet(private val image: Int) : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    private lateinit var binding: BottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetBinding.inflate(inflater, container, false)

        val bitmapImage = BitmapFactory.decodeResource(context?.resources, image)
        binding.imageviewBottomsheet.setImageBitmap(bitmapImage)

//        binding.buttonBottomSheet.setOnClickListener {
//            dismiss()
//        }

        return binding.root
    }

}
