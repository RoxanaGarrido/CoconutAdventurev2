package cat.copernic.rgarrido.coconutadventure

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import cat.copernic.rgarrido.coconutadventure.databinding.FragmentL11Binding
import cat.copernic.rgarrido.coconutadventure.databinding.FragmentL2Binding

class L2Fragment : Fragment() {
    private lateinit var binding: FragmentL2Binding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentL2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.globalData = sharedViewModel

        sharedViewModel.persistLifes(binding.include.ivFresaLive1, binding.include.ivFresaLive2, binding.include.ivFresaLive3)

        sharedViewModel.typewriter(binding.tvInfo, getString(R.string.construction))
    }

}