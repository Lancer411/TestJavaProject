package com.example;

/**
 * Created by SIvantsov on 22.01.2015.
 */
import java.net.*;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;

public class CheckPort {

    public static boolean scan(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            return socket.isConnected();
        } catch (IOException e) {
            return false;
        }
    }

    public static String scanHosts(int port)
    {
        Logger logger = Logger.getLogger("Host Scanner");
        String result = "";
        for(int i = 0 ; i < 255; i ++)
            for(int j = 0; j < 255; j ++)
            {
                String host = "192.168." + Integer.toString(i) + "." + Integer.toString(j);
                if(scan(host, port))
                {
                    result += host +"\n";
                    logger.info(host + ":" + Integer.toString(port));
                }
            }
        return result;
    }
}