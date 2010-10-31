package server;

import org.apache.thrift.TException;

class TimeServerImpl implements TimeServer.Iface {

   @Override
   public long time() throws TException {
      long time = System.currentTimeMillis();
      System.out.println("time() called: " + time);
      return time;
   }
}

