package com.example.am_fmcoder;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Timestamp;

/**
 * class creates a WAV file, sound is sinus wave.
 */
public class CreateSine
{
    static String fileNameString;
    static File file;
    static String filePath;

    static RandomAccessFile raw;

    static int byteCount = 0;

    static float freq;
    static int sRate = 44100;
    static int bitDepth = 16;
    static int nChannels = 1;
    static Float dur;

    static float changeRate;

    /**
     *
     * @param args - list of strings. args[0] is freq, args[1] is total time of message, args[2]
     *             is the message, args[3] is the binary string of the message and args[4] is FM/AM
     * @param context app context
     */
    public static String main(String[] args, Context context)
    {
        freq = Float.parseFloat(args[0]);
        changeRate = (float)((2.0 * Math.PI * freq) / sRate);

        dur = Float.parseFloat(args[1]) + 2;

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        fileNameString = (String)args[2].concat(args[4]) + ".wav";
        file = new File(context.getFilesDir(),fileNameString);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("couldnt create file");
                e.printStackTrace();
            }
        }
        filePath = file.getAbsolutePath();

        float[] bytes = new float[args[3].length()];
        for (int i = 0; i < args[3].length() ; i++) {
            bytes[i] = Float.parseFloat(String.valueOf(args[3].charAt(i)));
        }

        String AMFM = args[4];



        try
        {
            raw = new RandomAccessFile(filePath, "rw");

            raw.setLength(0); // Set file length to 0, to prevent unexpected behavior in case the file already existed
            raw.writeBytes("RIFF");
            raw.writeInt(0); // Final file size not known yet, write 0. This is = sample count + 36 bytes from header.
            raw.writeBytes("WAVE");
            raw.writeBytes("fmt ");
            raw.writeInt(Integer.reverseBytes(16)); // Sub-chunk size, 16 for PCM
            raw.writeShort(Short.reverseBytes((short) 1)); // AudioFormat, 1 for PCM
            raw.writeShort(Short.reverseBytes((short)nChannels));// Number of channels, 1 for mono, 2 for stereo
            raw.writeInt(Integer.reverseBytes(sRate)); // Sample rate
            raw.writeInt(Integer.reverseBytes(sRate*bitDepth*nChannels/8)); // Byte rate, SampleRate*NumberOfChannels*bitDepth/8
            raw.writeShort(Short.reverseBytes((short)(nChannels*bitDepth/8))); // Block align, NumberOfChannels*bitDepth/8
            raw.writeShort(Short.reverseBytes((short)bitDepth)); // Bit Depth
            raw.writeBytes("data");
            raw.writeInt(0); // Data chunk size not known yet, write 0. This is = sample count.
        }
        catch(IOException e) {
//            e.printStackTrace();
            System.out.println(e);
            System.out.println("I/O exception occured while writing data");
        }

        // write begin sound 1 sec
        for (int i = 0; i < sRate; i++) {
            writeSample( (float)Math.sin( 2*i * changeRate ));
        }
        // write to file
        int k = 0;
        if (AMFM.equals("FM")) {
            for (int i = 1; i < sRate*dur+1; i++) {
//            System.out.println(k);
                float b = bytes[k];
                if (b==0) {
                    writeSample( (float)Math.sin(0.5* i * changeRate ) );
                }
                else {
                    writeSample( (float)Math.sin( i * changeRate ) );
                }
                if (i%(sRate*dur/bytes.length)==0) {
                    k += 1;
                }
            }
        }
        else {
            for (int i = 1; i < sRate*dur+1; i++)
            {
//            System.out.println(k);
                float b = bytes[k];
                if (b==0) {
                    writeSample( (float)0.5*(float)Math.sin( i * changeRate ) );
                }
                else {
                    writeSample( (float)Math.sin( i * changeRate ) );
                }
                if (i%(sRate*dur/bytes.length)==0) {
                    k += 1;
                }

            }
        }
        // write end sound 1 sec
        for (int i = 0; i < sRate; i++) {
            writeSample( (float)Math.sin( 2*i * changeRate ));
        }

        closeFile();
        System.out.print("Finished");
        return filePath;
    }

    static void writeSample(float floatValue)
    {
        try
        {
            char shortSample = (char)( (floatValue)*0x7FFF );
            raw.writeShort(Character.reverseBytes(shortSample));
            byteCount += 2;
        }
        catch(IOException e)
        {
            System.out.println("I/O exception occured while writing data");
        }
    }

    static void closeFile()
    {
        try
        {
            raw.seek(4); // Write size to RIFF header
            raw.writeInt(Integer.reverseBytes(byteCount + 36));
            raw.seek(40); // Write size to Subchunk2Size field
            raw.writeInt(Integer.reverseBytes(byteCount));
            raw.close();
        }
        catch(IOException e)
        {
            System.out.println("I/O exception occured while closing output file");
        }
    }
}