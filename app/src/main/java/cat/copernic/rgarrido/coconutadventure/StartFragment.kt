package cat.copernic.rgarrido.coconutadventure

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import cat.copernic.rgarrido.coconutadventure.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btOptions.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_settingsFragment)

            loadSettings()
        }
    }

    //Cargar en el viewModel compartido los datos del root preferences
    private fun loadSettings() {

        PreferenceManager.setDefaultValues(requireContext(), R.xml.root_preferences, true)
        val sp:SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        sharedViewModel.name = sp.getString("name", "")
        sharedViewModel.lang = sp.getString("lang", "")
        sharedViewModel.music = sp.getBoolean("music", true)
        sharedViewModel.volume = sp.getInt("volume", 0)


    }

    companion object {

    }
}