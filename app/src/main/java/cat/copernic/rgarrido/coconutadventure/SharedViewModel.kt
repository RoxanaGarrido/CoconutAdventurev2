package cat.copernic.rgarrido.coconutadventure

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Application
import android.content.Context
import android.icu.number.IntegerWidth
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    //Profile name
    var name = MutableLiveData<String>("Name")

    //Modo de juego (easy 10 min/ medium 5 min/ hard 1 min)
    var mode = MutableLiveData<String>("Easy")

    //Puntos
    var score = MutableLiveData<Int>(0)
    set(value){
        if(score.value == 0){

        }else{
            field = value
        }
    }

    //temporizador
    var timer = MutableLiveData<Long>(600)

    //idioma
    var lang = MutableLiveData<String>("English")

    //Musica on off
    var music = MutableLiveData<Boolean>(false)

    var volume = MutableLiveData<Float>(50.0f)

    //Desactivar botón atrás
    var backPressDisable = false

    //vidas
    var lives: Int = 3

    var gameMiliTime = 600000L

    //lateinit var mediaPlayer: MediaPlayer


    /**
     * Coroutine para animar progressBar de levels
     */
     fun progressBar(progress:ProgressBar) {
        viewModelScope.launch{
            progress.max = 100
            ObjectAnimator.ofInt(progress, "progress", 100)
                .setDuration(3000)
                .start()
        }
    }

    /**
     * Animación de rotación que suma puntos
     */
    fun rotation(view: View){
        view.animate().apply {
            duration = 1000
            rotationYBy(360f)
        }.start()

        score.value = score.value?.plus(10)
    }

    /**
     * Función para animar texto con la clase typewriter
     */
    fun typewriter(text:TypeWriter, texto:String){
        text.setText("")
        text.setCharacterDelay(10)
        text.animateText(texto)
    }

    /**
     * Función para animar giros y restar puntos
     */
    fun spinner(view: View){
        val valueAnimator = ValueAnimator.ofFloat(0f, 360f)

        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            // 2
            view.rotation = value
        }

        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 2500L
        valueAnimator.start()

        if(score.value!! > 0){
            score.value = score.value?.minus(10)
        }
    }

    /**
     * Animación para aparecer
     */
    fun appear(view: View) {
        val objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        objectAnimator.setDuration(2500);
                // We wanna set the view to VISIBLE, but with alpha 0. So it appear invisible in the layout.
                view.setVisibility(View.VISIBLE)
                view.setAlpha(0f)

        objectAnimator.start()
            }

    /**
     * Función para establecer nuevo valor de vidas
     */
    fun lifeLoss(view: View, newValue: Int){
        lives = newValue
        view.visibility = View.INVISIBLE
    }

    /**
     * Comprobar vidas para aparecer o no las imagenes que representan cada vida
     */
    fun checkLifes(view1: View, view2: View, view3: View){
        when(lives){
            3 -> lifeLoss(view1, 2)
            2 -> lifeLoss(view2, 1)
            1 -> lifeLoss(view3, 0)
        }
    }

    fun persistLifes(view1: View, view2: View, view3: View){
        when(lives){
            2 -> view1.visibility = View.INVISIBLE
            1 -> {view1.visibility = View.INVISIBLE
                  view2.visibility = View.INVISIBLE}
            0 -> {view1.visibility = View.INVISIBLE
                  view2.visibility = View.INVISIBLE
                   view3.visibility = View.INVISIBLE}
        }
    }

    /**
     * Comprobar modo de juego para establecer timer
     */
    fun checkGameMode(){
        var modo: String? = mode.value
        when(modo){
            "ea" -> {
                gameMiliTime = 600000L
                timer.value = 600
            }
            "me" -> {
                gameMiliTime = 300000L
                timer.value = 300
            }
            "ha" -> {
                gameMiliTime = 60000L
                timer.value = 60
            }
        }
    }

}