package cat.copernic.rgarrido.coconutadventure

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import cat.copernic.rgarrido.coconutadventure.databinding.FragmentGameOverBinding
import cat.copernic.rgarrido.coconutadventure.databinding.FragmentL0Binding

class GameOverFragment : Fragment() {

    private lateinit var binding: FragmentGameOverBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentGameOverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        //reset lives
        sharedViewModel.lives = 3
/*
        binding.btPlayA.setOnClickListener{
            findNavController().navigate(R.id.action_gameOverFragment_to_startFragment)
        }

        binding.btExit.setOnClickListener{
            requireActivity().finish()
            System.exit(0)
        }
        */
    }

}