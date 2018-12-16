// In memory of Adam Voss, July 11, 1991 - July 11, 2018
// https://github.com/adamvoss
// http://schluterbalikfuneralhome.com/obituary/adam-voss
package org.prosegrinder.languagetool.lsp;

import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@SuppressWarnings("unused")
public class App {
  public static void main(String[] args) {

    InputStream in;
    OutputStream out;

    if (false) {
      String port = args[0];
      try (Socket socket = new Socket("localhost", Integer.parseInt(port))) {
        in = socket.getInputStream();
        out = socket.getOutputStream();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    in = System.in;
    out = System.out;

    LanguageToolLanguageServer server = new LanguageToolLanguageServer();
    Launcher<LanguageClient> launcher = LSPLauncher.createServerLauncher(server, in, out);

    if (server instanceof LanguageClientAware) {
      LanguageClient client = launcher.getRemoteProxy();
      ((LanguageClientAware)server).connect(client);
    }

    launcher.startListening();

  }
}
