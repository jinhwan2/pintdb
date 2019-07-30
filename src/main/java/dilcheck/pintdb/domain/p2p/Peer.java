package dilcheck.pintdb.domain.p2p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Peer {
  public static Socket connect2peer(String endpoint, int port)
      throws UnknownHostException, IOException {
    return new Socket(endpoint, port);
  }

  /**
   * check peer is alive.
   * @param socket socket.
   */
  public static boolean isAlive(Socket socket) throws IOException {
    OutputStream out = socket.getOutputStream();
    InputStream in = socket.getInputStream();
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
    BufferedReader br = new BufferedReader(new InputStreamReader(in));

    pw.println("nock");
    pw.flush();

    return br.readLine().equals("fine");
  }
}
