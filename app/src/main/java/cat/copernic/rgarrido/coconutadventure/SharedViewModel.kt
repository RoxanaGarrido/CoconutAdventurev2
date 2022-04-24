package cat.copernic.rgarrido.coconutadventure

import android.animation.ObjectAnimator
import android.app.Application
import android.icu.number.IntegerWidth
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    var name = MutableLiveData<String>("Name")

    var score = MutableLiveData<Int>(0)

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

    fun rotation(view: View){
        view.animate().apply {
            duration = 1000
            rotationYBy(360f)
        }.start()

        score.value = score.value?.plus(10)
    }

    fun typewriter(text:TypeWriter, texto:String){
        text.setText("")
        text.setCharacterDelay(10)
        text.animateText(texto)

    }
}