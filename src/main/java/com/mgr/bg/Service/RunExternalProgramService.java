package com.mgr.bg.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RunExternalProgramService {

    public static void main(String[] args) throws IOException {


        Runtime runtime = Runtime.getRuntime();     //getting Runtime object

        String[] s = new String[] {"C:\\Users\\Bartek\\Desktop\\Praca magisterska\\OpenDSS\\x64\\OpenDSS.exe", "C:\\Praca Magisterska\\SOPJEE_model_v3.dss"};
        Process process = null;
        try
        {
            process = runtime.exec(s);        //opens "https://javaconceptoftheday.com/" in chrome browser
            process.waitFor(10, TimeUnit.SECONDS);
            process.destroy();
        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
