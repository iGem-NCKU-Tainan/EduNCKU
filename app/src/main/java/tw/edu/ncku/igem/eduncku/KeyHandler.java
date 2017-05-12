package tw.edu.ncku.igem.eduncku;

/**
 * Created by ASUS on 2017/5/2.
 */
import java.util.HashMap;
import java.util.Map;

public class KeyHandler {
    public Map<Integer,Boolean> MapKeyDown=new HashMap<Integer, Boolean>();
    public KeyHandler(){

    }
    public void keyDown(int keyCode){
        MapKeyDown.put(keyCode, true);
    }
    public void keyUp (int keyCode){
        MapKeyDown.put(keyCode, false);
    }

    public boolean isKeyDown(int keyCode){
        Boolean isKeyUp=MapKeyDown.get(keyCode);
        if(isKeyUp!=null)
            return isKeyUp;
        else
            return false;
    }
    public boolean isKeyUp(int keyCode){
        Boolean isKeyUp=MapKeyDown.get(keyCode);
        if(isKeyUp!=null)
            return isKeyUp;
        else
            return true;
    }
}