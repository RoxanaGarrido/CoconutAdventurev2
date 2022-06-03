package cat.copernic.rgarrido.coconutadventure

import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceManager

class MainActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()
    lateinit var mp : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Disable dark mode version
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //To keep immersive mode as it is while soft input enabled and typing. You need to set flags to your activity window. Not to Decor view.
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //  Configuración para crear app con modo inmersivo
        // Cuando los usuarios quieren que vuelvan a aparecer las barras del sistema,
        // deslizan el dedo desde cualquier borde en el que está oculta una barra del sistema.

//        val decorView:View = window.decorView
//        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                // Set the content to appear under the system bars so that the
//                // content doesn't resize when the system bars hide and show.
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                // Hide the nav bar and status bar
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_FULLSCREEN)


        //Configuración para que la app solo esté en modo landscape
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE


        //mp = MediaPlayer.create(this, R.raw.theme1)

    }

    /**
     * Después de empezar el juego, se deshabilita la opción de ir atrás
     */
    override fun onBackPressed() {
        if (sharedViewModel.backPressDisable) {

        } else {
            super.onBackPressed();
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //Resetear valores de settings a default
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, true)
        val sp:SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        sp.edit().clear().apply()
    }
}