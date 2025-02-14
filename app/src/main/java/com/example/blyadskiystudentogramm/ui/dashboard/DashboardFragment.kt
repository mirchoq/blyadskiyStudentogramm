package com.example.blyadskiystudentogramm

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.blyadskiystudentogramm.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    // Обязательно добавьте пустой конструктор
    constructor() : super()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isFirstLogin = sharedPreferences.getBoolean("is_first_login", true)

        if (isFirstLogin) {

            // Вместо удаления фрагмента просто скрываем его содержимое
            binding.root.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}