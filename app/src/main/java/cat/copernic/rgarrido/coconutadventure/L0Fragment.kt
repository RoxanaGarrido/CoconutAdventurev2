package cat.copernic.rgarrido.coconutadventure

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import cat.copernic.rgarrido.coconutadventure.databinding.FragmentL0Binding


class L0Fragment : Fragment() {

    private lateinit var binding:FragmentL0Binding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentL0Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.globalData = sharedViewModel

        sharedViewModel.typewriter(binding.tvL0, getString(R.string.level0))

        binding.btNext.setOnClickListener{
            findNavController().navigate(R.id.action_l0Fragment_to_l01Fragment)
        }

    }
}