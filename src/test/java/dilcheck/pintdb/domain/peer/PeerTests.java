package dilcheck.pintdb.domain.peer;

import static org.junit.Assert.assertTrue;

import dilcheck.pintdb.AbstractRunnerConfig;
import dilcheck.pintdb.domain.p2p.Peer;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class PeerTests extends AbstractRunnerConfig {
  
  /*
   * test works in condition that PintDB is running.
   * need to find another way.
   */
  @Test
  public void liveTest() throws UnknownHostException, IOException {
    Socket socket = Peer.connect2peer("localhost", 10000);
    assertTrue(Peer.isAlive(socket));
  }
}
