package com.example.blyadskiystudentogramm

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.blyadskiystudentogramm.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Инициализация ViewBinding
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Проверяем, была ли нажата кнопка
        val sharedPreferences =
            requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isButtonClicked = sharedPreferences.getBoolean("is_button_clicked", false)
    }
}