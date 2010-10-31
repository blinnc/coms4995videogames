package server;

import java.io.IOException;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;


public class Server {
   private void start(){
      try {
         TServerSocket serverTransport = new TServerSocket(7911);
         TimeServer.Processor processor = new TimeServer.Processor(new TimeServerImpl());
         Factory protFactory = new TBinaryProtocol.Factory(true, true);
         TServer server = new TThreadPoolServer(processor, serverTransport, protFactory);
         System.out.println("Starting server on port 7911 ...");
         server.serve();
      } catch (TTransportException e) {
         e.printStackTrace();
//      } catch (IOException e) {
//         e.printStackTrace();
      }
   }
   public static void main(String args[]){
      Server srv = new Server();
      srv.start();
   }
}

