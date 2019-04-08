package com.mgr.bg.Service;

import java.io.*;

public class TextFileService {

    public static void main(String[] args) {

        String fileName = "C:\\Users\\Bartek\\Desktop\\mgr\\src\\main\\resources\\OneModel_v3.dss";

        String line;

        FileReader fileReader;

        {
            try {
                fileReader = new FileReader(new File(fileName));
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }

                bufferedReader.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
