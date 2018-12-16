// In memory of Adam Voss, July 11, 1991 - July 11, 2018
// https://github.com/adamvoss
// http://schluterbalikfuneralhome.com/obituary/adam-voss
package org.languagetool.lsp;

import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LSPServer {
  public static void main(String[] args) {
    System.out.println("App.main");

    String port = args[0];
    try (Socket socket = new Socket("localhost", Integer.parseInt(port))) {
      InputStream in = socket.getInputStream();
      OutputStream out = socket.getOutputStream();

      LanguageToolLanguageServer server = new LanguageToolLanguageServer();
      Launcher<LanguageClient> launcher = LSPLauncher.createServerLauncher(server, in, out);

      LanguageClient client = launcher.getRemoteProxy();
      server.connect(client);

      launcher.startListening();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
