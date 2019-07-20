package metrolink;
import java.math.*;

public class Working{
   
    Working(){};
    private static Working instance = new Working();

    public static Working getInstance() {
        return instance;
    }


    private String lastLine = "";

    public void print(String line) {
        //clear the last line if longer
        if (lastLine.length() > line.length()) {
            String temp = "";
            for (int i = 0; i < lastLine.length(); i++) {
                temp += " ";
            }
            if (temp.length() > 1)
                System.out.print("\r" + temp);
        }
        System.out.print("\r" + line);
        lastLine = line;
    }
    private byte anim;
    public void animation(String line) {
        switch (anim) {
            case 1:
                print("[ | ] " + line + " . . . " );
                break;
            case 2:
                print("[ / ] " + line + " . ." );
                break;
            case 3:
                print("[ | ] " + line + " . ");
                break;
            default:
                anim = 0;
                print("[ \\ ] " + line + " "  );
                break;
                
        }
        anim++;
    }
    public byte getAnim(){
    return anim;
    }
}