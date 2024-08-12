package com.seiyodev.horoscapp.ui.luck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.seiyodev.horoscapp.databinding.FragmentLuckBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LuckFragment : Fragment() {



    private var _binding: FragmentLuckBinding? = null

    // Esta propiedad es solo para lectura del de arriba. Se crea para que se pueda acceder al binding sin que se pueda modificar
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

}