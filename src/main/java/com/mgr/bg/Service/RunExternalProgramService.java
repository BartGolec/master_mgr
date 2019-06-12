package com.mgr.bg.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class RunExternalProgramService {

    private static final Logger log = LoggerFactory.getLogger(RunExternalProgramService.class);

    //String[] s = new String[] {"C:\\Users\\Bartek\\Desktop\\Praca magisterska\\OpenDSS\\x64\\OpenDSS.exe", "C:\\Praca Magisterska\\SOPJEE_model_v3.dss"};

    public void runOpenDssWithModel(String openDssExePath, String openDssModelPath){
        log.info("Run openDss with model");
        log.info("OpenDss model path : " + openDssModelPath);
        log.info("OpenDss exe path :  " + openDssExePath);

        Runtime runtime = Runtime.getRuntime();
        String [] runtimeArguments = new String[] {openDssExePath, openDssModelPath};
        Process process = null;
        try {
            process = runtime.exec(runtimeArguments);
            process.waitFor(5, TimeUnit.SECONDS);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            if (process != null) {
                process.destroy();
            }
        }
    }

    public void killNotepadProcess(){
        log.info("Kill notepad process");
        try {
            Runtime.getRuntime().exec("taskkill /F /IM notepad.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
