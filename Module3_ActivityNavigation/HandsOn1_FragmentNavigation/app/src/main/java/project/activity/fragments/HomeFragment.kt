package project.activity.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import project.activity.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dashboardBtn = view.findViewById<Button>(R.id.btnDashboard)
        val profileBtn = view.findViewById<Button>(R.id.btnProfile)

        dashboardBtn.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_dashboard)
        }

        profileBtn.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_profile)
        }
    }
}