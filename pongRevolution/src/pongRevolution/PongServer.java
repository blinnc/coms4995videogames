package pongRevolution;

import java.util.Timer;
import java.util.TimerTask;

import network.TCommandServer;
import network.TGameState;
import network.TPlayer;
import network.TSettings;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

public class PongServer {
	
}