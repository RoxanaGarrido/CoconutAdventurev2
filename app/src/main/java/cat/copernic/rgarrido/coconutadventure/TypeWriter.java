package cat.copernic.rgarrido.coconutadventure;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import android.os.Handler;
import java.util.logging.LogRecord;

public class TypeWriter extends androidx.appcompat.widget.AppCompatTextView {

    private CharSequence text;
    private int index;
    private long delay = 150;

    public TypeWriter(Context context) {
        super(context);
    }

    public TypeWriter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private Handler myHandler = new Handler();


    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(text.subSequence(0, index++));
            if(index<=text.length()){
                myHandler.postDelayed(characterAdder, delay);
            }
        }
    };

    public void animateText(CharSequence myTxt){
        text = myTxt;
        index = 0;

        setText("");

        myHandler.removeCallbacks(characterAdder);

        myHandler.postDelayed(characterAdder, delay);

    }

    public void setCharacterDelay(long m){
        delay = m;
    }
}
