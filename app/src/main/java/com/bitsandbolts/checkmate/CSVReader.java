package com.bitsandbolts.checkmate;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    InputStream inputStream;

    public CSVReader(InputStream is) {
        this.inputStream = is;
    }

    public List<Happening> read() {
        List<Happening> resultList = new ArrayList<Happening>();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        List<List<String>> data = new ArrayList<>();
        try {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                if (!line.equals("")) {
                    String[] temp = line.split(",");
                    String location = temp[0] + ", " + temp[1];
                    String date = temp[2] + "/" + temp[3] + "/" + temp[4];
                    String time = temp[5] + ":" + temp[6];
                    resultList.add(new Happening(location, date, time));
                }
            }
        } catch(Exception e) {
            throw new RuntimeException("Error in reading CSV file: " + e);
        } finally {
            try {
                inputStream.close();
            } catch(IOException ex) {
                throw new RuntimeException("Error while closing input stream: " + ex);
            }
        }
        return resultList;
    }
}
