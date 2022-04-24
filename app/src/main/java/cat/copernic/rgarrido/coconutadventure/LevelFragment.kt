package cat.copernic.rgarrido.coconutadventure

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import cat.copernic.rgarrido.coconutadventure.databinding.FragmentLevelBinding

class LevelFragment : Fragment() {

    private lateinit var binding: FragmentLevelBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var level = requireArguments().getString("Level")
        binding.tvLevelTittle.text = level

        sharedViewModel.progressBar(binding.progressBar)

        object : CountDownTimer( 5000, 200) {
            override fun onTick(millisUntilFinished: Long) {
                var milis = millisUntilFinished / 1000
                when(milis){
                    4L -> binding.tvPorcentaje.setText("20%")
                    3L -> binding.tvPorcentaje.setText("40%")
                    2L -> binding.tvPorcentaje.setText("60%")
                    1L -> binding.tvPorcentaje.setText("80%")
                    0L -> binding.tvPorcentaje.setText("100%")
                }
            }

            override fun onFinish() {
                when(level){
                    "Level 0" -> findNavController().navigate(R.id.action_levelFragment_to_l0Fragment)
                    "Level 1" -> findNavController().navigate(R.id.action_levelFragment_to_l1Fragment)
                    "Level 2" -> findNavController().navigate(R.id.action_levelFragment_to_l2Fragment)
                }

            }
        }.start()
    }
}