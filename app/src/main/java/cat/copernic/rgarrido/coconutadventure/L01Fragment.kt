package cat.copernic.rgarrido.coconutadventure

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import cat.copernic.rgarrido.coconutadventure.databinding.FragmentL01Binding
import cat.copernic.rgarrido.coconutadventure.databinding.FragmentL0Binding

class L01Fragment : Fragment() {

    private lateinit var binding:FragmentL01Binding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentL01Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.globalData = sharedViewModel


        sharedViewModel.typewriter(binding.tvL01, getString(R.string.level01))

        binding.ivCoco.setOnClickListener{
            binding.ivCoco.animate().apply {
                duration = 2000
                translationY(50f)
                interpolator = OvershootInterpolator()
            }.start()

            sharedViewModel.score.value = sharedViewModel.score.value?.plus(100)
        }

        binding.btCave.setOnClickListener{
            bundle.putString("Level", "Level 2")
            findNavController().navigate(R.id.action_l01Fragment_to_levelFragment, bundle)
            Toast.makeText(context, "You skipped Level 1!", Toast.LENGTH_SHORT).show()
        }

        binding.btForest.setOnClickListener{
            bundle.putString("Level", "Level 1")
            findNavController().navigate(R.id.action_l01Fragment_to_levelFragment, bundle)
        }

    }

}