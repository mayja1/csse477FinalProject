package edu.rosehulman.balancer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MAIN {
	public static void main(String[] args) {
		ServerSocket welcomeSocket = null;
		try {
			welcomeSocket = new ServerSocket(8080);
			while (true) {
				// Listen for incoming socket connection
				// This method block until somebody makes a request
				Socket connectionSocket;
				connectionSocket = welcomeSocket.accept();
				// Create a handler for this incoming connection and start the
				// handler in a new thread
				Balancer handler = new Balancer(connectionSocket);
				new Thread(handler).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
