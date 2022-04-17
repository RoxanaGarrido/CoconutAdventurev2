package cat.copernic.rgarrido.coconutadventure

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    var name:String? = ""
    var _name: MutableLiveData<String> = MutableLiveData("Your Name")
    var lang:String? = "English"
    var music:Boolean? = true
    var volume:Int? = 50
}