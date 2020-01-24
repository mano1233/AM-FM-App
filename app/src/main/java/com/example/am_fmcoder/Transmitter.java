package com.example.am_fmcoder;


import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.File;
import java.util.Map;


public class Transmitter {

    private String transmitMethod;
    private Map<String,String> encriptionDict;
    private Map<String,String> binaryDict;
    private Context mContext;

    /**
     * Constractor for transmiting class.
     * @param myContext - app context
     * @param encriptionDict - encription Map. א->ב, ...
     * @param BinaryDict binary Map. א->00001...
     * @param TransmitMethod - "AM" for AM and FM for FM
     */
    public Transmitter(Context myContext, Map<String,String> encriptionDict, Map<String,String> BinaryDict, String TransmitMethod) {
        this.transmitMethod = TransmitMethod;
        this.encriptionDict = encriptionDict;
        this.binaryDict = BinaryDict;
        this.mContext = myContext;
    }

    /**
     * create a raw sound file in app's internal memory using a CreateSine object.
     * @param message - this message is the message you want to send.
     */
    private String createWAV(String message) {
        // create encripted message and byesString
        StringBuilder sbm = new StringBuilder();
        StringBuilder sbb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            String letter = this.encriptionDict.get(String.valueOf(message.charAt(i)));
            sbm.append(letter);
            sbb.append(this.binaryDict.get(letter+" "));
        }

        String encriptedMsg = sbm.toString();
        String binaryString = sbb.toString();

        String time = String.valueOf(0.5 * binaryString.length());

        // create WAV file
        String[] args = new String[5];
        args[0] = "880";
        args[1] = time;
        args[2] = message;
        args[3] = binaryString;
        args[4] = this.transmitMethod;

        String filePath = com.example.am_fmcoder.CreateSine.main(args, this.mContext);
        return filePath;

    }

    /**
     * play the message you entered.
     * @param message - message to play.
     */
    public MediaPlayer Transmit(String message) {
        Log.d("debug", this.binaryDict.toString());
        Log.d("debug", this.encriptionDict.toString());
        Log.d("debug", this.transmitMethod);

        // check if WAV file exists
        String path = this.mContext.getFilesDir() + "/"+ message.concat(this.transmitMethod) + ".wav";
        System.out.println("path = " + path);
        File file = new File(path);
        if (!file.exists()){
            String filepath = this.createWAV(message);
            System.out.println("path = " + filepath);
            MediaPlayer player = new MediaPlayer();
            File newFile = new File(filepath);
            try {
                player.setDataSource(filepath);
                player.prepare();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Exception of type : " + e.toString());
                e.printStackTrace();
            }

            player.start();
            return player;
        }

        else {
            MediaPlayer player = new MediaPlayer();

            try {
                player.setDataSource(path);
                player.prepare();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("Exception", "Exception of type : " + e.toString());
                e.printStackTrace();
            }

            player.start();
            return player;
        }

    }
    public void Release(MediaPlayer player){
        player.release();
    }

}
