package com.example;

/**
 * Created by SIvantsov on 22.01.2015.
 */
import java.net.*;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckPort {

    public static boolean scan(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            return socket.isConnected();
        } catch (IOException e) {
            return false;
        }
    }
}