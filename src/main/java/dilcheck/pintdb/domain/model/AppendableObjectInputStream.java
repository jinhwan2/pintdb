package dilcheck.pintdb.domain.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

public class AppendableObjectInputStream extends ObjectInputStream {
  protected AppendableObjectInputStream() throws IOException, SecurityException {
    super();
  }

  public AppendableObjectInputStream(InputStream in) throws IOException {
    super(in);
  }

  @Override
  protected void readStreamHeader() throws IOException, StreamCorruptedException {}
}
