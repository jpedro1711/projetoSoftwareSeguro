package utils;

import java.io.*;
import java.time.LocalDateTime;

public class ExceptionsLogger {

    public static void log(Exception e) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("logs.txt", true))) {
            String logMessage = LocalDateTime.now() + " - " + e.getMessage();
            bw.write(logMessage);
            bw.newLine();
        } catch (IOException ex) {
            System.err.println("Erro ao escrever no arquivo de log: " + ex.getMessage());
        }
    }


}
