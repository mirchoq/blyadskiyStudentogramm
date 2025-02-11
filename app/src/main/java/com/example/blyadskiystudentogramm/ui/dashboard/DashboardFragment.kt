package com.example.blyadskiystudentogramm.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.blyadskiystudentogramm.R
import com.example.blyadskiystudentogramm.databinding.FragmentDashboardBinding
import com.example.blyadskiystudentogramm.register
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {



        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        return root
    }

    fun Por(view: View) {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(context, register::class.java))
        requireActivity().finish()
    }
}