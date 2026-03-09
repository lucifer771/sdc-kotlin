package project.activity.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import project.activity.R

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val btn = view.findViewById<Button>(R.id.btnDetails)

        btn.setOnClickListener {

            findNavController().navigate(R.id.detailsFragment)

        }
    }
}