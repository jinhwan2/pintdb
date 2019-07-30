package dilcheck.pintdb.domain.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.springframework.stereotype.Component;

@Component
public class ConnectionServer {
  private ServerSocket serverSocket;

  /**
   * start connection server.
   * @param port health check port
   */
  public void start(int port) throws IOException {
    serverSocket = new ServerSocket(port);
    while (true) {
      new ClientHandler(serverSocket.accept()).start();
    }
  }

  private static class ClientHandler extends Thread {
    private Socket clientSocket;
    private OutputStream out;
    private InputStream in;

    public ClientHandler(Socket socket) {
      this.clientSocket = socket;
    }

    public void run() {
      try {
        out = this.clientSocket.getOutputStream();
        in = this.clientSocket.getInputStream();
        getClientMessage(in, out);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    private void getClientMessage(InputStream in, OutputStream out) throws IOException {
      PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
      BufferedReader br = new BufferedReader(new InputStreamReader(in));

      while ((br.readLine()) != null) {
        pw.println("fine");
        pw.flush();
      }
    }

  }
}
