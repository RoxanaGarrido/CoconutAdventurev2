package cat.copernic.rgarrido.coconutadventure

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import cat.copernic.rgarrido.coconutadventure.databinding.FragmentL11Binding

class L1_1Fragment : Fragment() {

    private lateinit var binding: FragmentL11Binding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
      binding = FragmentL11Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.globalData = sharedViewModel

        sharedViewModel.appear(binding.ivRana)
        sharedViewModel.appear(binding.btQuestion)

        binding.btQuestion.setOnClickListener {
            sharedViewModel.typewriter(binding.tvL11, getString(R.string.question1))
            Handler().postDelayed({
                sharedViewModel.appear(binding.btThanks)
                sharedViewModel.appear(binding.btGifts)
                sharedViewModel.appear(binding.btChecks)
            }, 2000)
        }

        binding.btThanks.setOnClickListener {
            sharedViewModel.appear(binding.ivSuperFresa)
            sharedViewModel.typewriter(binding.tvL11, getString(R.string.exito1))
            sharedViewModel.appear(binding.btNext1)
        }

        binding.btChecks.setOnClickListener {
            onError()
        }

        binding.btGifts.setOnClickListener {
            onError()
        }

        binding.ivSuperFresa.setOnClickListener() {
            sharedViewModel.rotation(binding.ivSuperFresa)
            sharedViewModel.score.value = sharedViewModel.score.value?.plus(500)
        }

        binding.btNext1.setOnClickListener {
            bundle.putString("Level", "Level 2")
            findNavController().navigate(R.id.action_l1_1Fragment_to_levelFragment, bundle)

        }
    }

    fun onError(){
        sharedViewModel.typewriter(binding.tvL11, getString(R.string.fail1))
        if(sharedViewModel.lives > 0){
            sharedViewModel.checkLifes(
                binding.include.ivFresaLive1,
                binding.include.ivFresaLive2,
                binding.include.ivFresaLive3)
        }else{
            findNavController().navigate(R.id.gameOverFragment)
        }
    }

}