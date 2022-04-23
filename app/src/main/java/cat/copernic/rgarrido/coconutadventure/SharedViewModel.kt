package cat.copernic.rgarrido.coconutadventure

import android.animation.ObjectAnimator
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    var name = MutableLiveData<String>("Name")

   var score = MutableLiveData<String>("000000")

    var lang = MutableLiveData<String>("English")

    var music = MutableLiveData<Boolean>(false)

    var volume = MutableLiveData<Float>(50.0f)


     fun progressBar(progress:ProgressBar) {
        viewModelScope.launch{
            progress.max = 100
            ObjectAnimator.ofInt(progress, "progress", 100)
                .setDuration(5000)
                .start()
        }
    }
}