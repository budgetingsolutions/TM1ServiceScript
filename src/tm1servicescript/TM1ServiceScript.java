package tm1servicescript;

import java.awt.Frame;
import java.io.IOException;

/**
 * A program to create or remove a windows TM1 Server Service 
 * @author tom.saxton-howes
 */
public class TM1ServiceScript {
    
    private static GUI gui;

    /**
     * Main creates a new instance of GUI() and set's it to visible
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gui = new GUI();
        gui.setVisible(true);
    }
    
    /**
     * The TM1 server install executor
     * @param TM1loc the location of the TM1 install location
     * @param TM1Data the location of the data directory containing the
     * TM1s.cfg file
     * @param TM1name the name of your TM1 server process
     */
    public static void installServer(String TM1loc, String TM1Data,
            String TM1name){
        if(TM1loc == null || TM1Data == null || TM1name == null){
            /*use static referance of gui because we want to use the frames from
             * the interface which is running
             */
            Frame[] guiFrame = gui.getFrames();
                MsgBox msgbox = new MsgBox(guiFrame[0],"Process failed");
        }else{
            //the command we wish to run
            String CMD = TM1loc + "tm1sd.exe -install -n \"" + TM1name
                    + "\" -z\"" + TM1Data + "\"";
            try{
                Process process = Runtime.getRuntime().exec(CMD);
                Frame[] guiFrame = gui.getFrames();
                MsgBox msgbox = new MsgBox(guiFrame[0],"Process has executed"
                        + " successfully");
                msgbox.dispose();
            }catch(IOException IO){
                System.err.println(IO);
                Frame[] guiFrame = gui.getFrames();
                MsgBox msgbox = new MsgBox(guiFrame[0],"An Error has occurred"
                        + " please report the following " + IO);
                msgbox.dispose();
            }
        }
    }
    
    /**
     * The TM1 Server Removal executor
     * @param TM1Loc location of the TM1 install location
     * @param TM1Name the name of the TM1 server
     */
    public static void removeServer(String TM1Loc, String TM1Name){
        if(TM1Loc ==null){
            Frame[] guiFrame = gui.getFrames();
            MsgBox msgbox = new MsgBox(guiFrame[0],"Process failed");
        }else{
            //the command we wish to run
            String CMD = TM1Loc + "tm1sd.exe -remove -n" + TM1Name;
            try{
                Process process = Runtime.getRuntime().exec(CMD);
                Frame[] guiFrame = gui.getFrames();
                MsgBox msgbox = new MsgBox(guiFrame[0],"Process has executed"
                        + " successfully");
                msgbox.dispose();
            }catch(IOException IO){
                System.err.println(IO);
                Frame[] guiFrame = gui.getFrames();
                MsgBox msgbox = new MsgBox(guiFrame[0], "An Error has occured"
                        + " please report the following " + IO);
                msgbox.dispose();
            }
        }
    }
}
