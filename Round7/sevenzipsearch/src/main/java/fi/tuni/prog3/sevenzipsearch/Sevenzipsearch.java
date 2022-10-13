package fi.tuni.prog3.sevenzipsearch;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.sevenz.*;


public class Sevenzipsearch {
    public static void main(String[] args)
            throws IOException {

        try (SevenZFile sz = new SevenZFile(new File(args[0]))) {
            SevenZArchiveEntry entry;
            while ((entry = sz.getNextEntry()) != null) {
                if (entry.getName().contains(".txt")) {
                    System.out.println(entry.getName());
                    ByteArrayOutputStream contentBytes = new ByteArrayOutputStream();
                    byte[] buffer = new byte[2048];
                    int bytes;
                    while((bytes = sz.read(buffer)) != -1) {
                        contentBytes.write(buffer, 0, bytes);
                    }

                    String content = contentBytes.toString("UTF-8");
                    int index = 1;
                    for(String line : content.split("\\r?\\n"))
                    {
                        if (line.toLowerCase().contains(args[1].toLowerCase()))
                        {
                            System.out.println(index + ": " + line.replaceAll("(?i)"+args[1],
                                    args[1].toUpperCase()));
                        }
                        index++;
                    }

                    System.out.println();
                }
            }
        }
    }
}