package com.clienttracker.socket.protocols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;

/**
 * SignInProtocol sends the necessary information to the server to ensure the
 * user is valid.
 */
public class SignInProtocol {

  private static final int SIGNINPROTOCOL = 6;

  private final String username;
  private final String hashedPassword;
  PrintWriter out;
  BufferedReader in;

  public SignInProtocol(String username, String hashedPassword,
                        PrintWriter out, BufferedReader in) {
    this.username = username;
    this.hashedPassword = hashedPassword;
    this.out = out;
    this.in = in;
  }

  public int executeProtocol() {
    String fromClient;
    String fromServer;
    try {
      fromClient = Integer.toString(SIGNINPROTOCOL);
      System.out.println("Client: " + fromClient);
      out.println(fromClient);

      System.out.println("Client: " + username);
      out.println(username);

      System.out.println("Client: " + hashedPassword);
      out.println(hashedPassword);

      fromServer = in.readLine();
      System.out.println("Server: " + fromServer);

      //Did the server confirm this login?
      int counselorID = Integer.parseInt(fromServer);
      if(counselorID != -1) return counselorID;
      else return -1;

    } catch (UnknownHostException e) {
         System.err.println("Don't know about host.");
         System.exit(1);
    } catch (IOException e) {
         System.err.println("Couldn't get I/O for the connection.");
         System.exit(1);
    }

    return -1;
  }
}
