package cat.copernic.rgarrido.coconutadventure

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import cat.copernic.rgarrido.coconutadventure.databinding.FragmentL01Binding
import cat.copernic.rgarrido.coconutadventure.databinding.FragmentL1Binding

class L1Fragment : Fragment() {

    private lateinit var binding: FragmentL1Binding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentL1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.globalData = sharedViewModel

        sharedViewModel.spinner(binding.ivFresaDark1)
        sharedViewModel.spinner(binding.ivFresaDark2)
        sharedViewModel.spinner(binding.ivFresaDark3)

        binding.btHelp.setOnClickListener{
            sharedViewModel.typewriter(binding.tvL1, getString(R.string.level1))
        }

        binding.btDarkFresa.setOnClickListener{
            Toast.makeText(context, "They are poisoned! You lose 1 life", Toast.LENGTH_SHORT).show()

            if(sharedViewModel.lives > 0){
                sharedViewModel.checkLifes(
                    binding.include.ivFresaLive1,
                    binding.include.ivFresaLive2,
                    binding.include.ivFresaLive3)
            }else{
                findNavController().navigate(R.id.gameOverFragment)
            }
        }

        binding.btRedFresa.setOnClickListener{
            findNavController().navigate(R.id.action_l1Fragment_to_l1_1Fragment)
        }
    }

}