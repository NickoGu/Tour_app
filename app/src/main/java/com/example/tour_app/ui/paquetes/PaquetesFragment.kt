package com.example.tour_app.ui.paquetes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tour_app.databinding.FragmentPackagesBinding

class PaquetesFragment : Fragment() {

private var _binding: FragmentPackagesBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val paquetesViewModel =
            ViewModelProvider(this).get(PaquetesViewModel::class.java)

    _binding = FragmentPackagesBinding.inflate(inflater, container, false)
    val root: View = binding.root

    val textView: TextView = binding.textView3
    paquetesViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }
    return root
  }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}