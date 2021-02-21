package com.wd.crawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WebPagesPrinter {

    private static final String FILE_NAME = "output.txt";

    public void print(WebPage webPage){
        String content = webPage.summary();
        System.out.print(content);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME,true))){
            bw.write(content);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
