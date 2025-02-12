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
        // Инициализация разметки через ViewBinding
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Проверяем, нужно ли показывать приветственное сообщение
        val sharedPreferences = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isFirstLogin = sharedPreferences.getBoolean("is_first_login", true)

        if (isFirstLogin) {
            // Показываем приветственное сообщение


            // Сохраняем информацию о том, что пользователь уже вошёл
            sharedPreferences.edit().putBoolean("is_first_login", false).apply()
        } else {
            // Если это не первый вход, скрываем фрагмент
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}