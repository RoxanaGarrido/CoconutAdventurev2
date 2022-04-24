package cat.copernic.rgarrido.coconutadventure

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import cat.copernic.rgarrido.coconutadventure.databinding.FragmentStartBinding
import cat.copernic.rgarrido.coconutadventure.databinding.GlobalFragmentBinding
import kotlinx.coroutines.launch

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var mp : MediaPlayer
    val bundle = Bundle()

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

        binding.lifecycleOwner = viewLifecycleOwner
        binding.globalData = sharedViewModel
        mp = MediaPlayer.create(context, R.raw.theme1)

        loadSettings()
        configPreferences(mp)

        binding.btOptions.setOnClickListener{
            findNavController().navigate(R.id.action_startFragment_to_settingsFragment)
        }

        binding.btStart.setOnClickListener{
            bundle.putString("Level", "Level 0")
            findNavController().navigate(R.id.action_startFragment_to_levelFragment, bundle)
            timerCoroutine()
            sharedViewModel.backPressDisable = true
        }
    }

    private fun configPreferences(mp: MediaPlayer) {
        //Play music
        when(sharedViewModel.music.value){
            true -> {
                if(!mp.isPlaying){
                        mp.isLooping = true
                        mp.setVolume(sharedViewModel.volume.value!!, sharedViewModel.volume.value!!)
                        mp.start()
              }
            }
            false -> {
                if(mp != null){
                    mp.stop()
                    mp.release()
                }
            }
        }
    }

    //Cargar en el viewModel compartido los datos del root preferences
    private fun loadSettings() {

        PreferenceManager.setDefaultValues(requireContext(), R.xml.root_preferences, true)
        val sp:SharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        sharedViewModel.name.value = sp.getString("name", "")
        sharedViewModel.lang.value = sp.getString("lang", "")
        sharedViewModel.music.value = sp.getBoolean("music", false)
        sharedViewModel.volume.value = sp.getInt("volume", 50).toFloat()
    }


    fun timerCoroutine(){
        lifecycleScope.launch{
            object : CountDownTimer( 300000, 200) {
                override fun onTick(millisUntilFinished: Long) {
                    var milis = millisUntilFinished / 1000
                    sharedViewModel.timer.value = milis

                    if(milis == 60000L){
                        Toast.makeText(context, "Hurry Up! 1 minute left!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFinish() {
                    findNavController().navigate(R.id.gameOverFragment)
                }
            }.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.release()
    }
}