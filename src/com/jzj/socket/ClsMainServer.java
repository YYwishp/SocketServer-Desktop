package com.jzj.socket;

public class ClsMainServer {

	public static void main(String[] args) {
		int port = 1234;


		//
		TcpServer server = new TcpServer(port) {

			@Override
			public void onConnect(SocketTransceiver client) {
				printInfo(client, "Connect");
			}

			@Override
			public void onConnectFailed() {
				System.out.println("Client Connect Failed");
			}

			@Override
			public void onReceive(SocketTransceiver client, String s) {
				printInfo(client, "接收数据: " + s);
				client.send("服务端 message");
			}

			@Override
			public void onDisconnect(SocketTransceiver client) {
				printInfo(client, "Disconnect");
			}

			@Override
			public void onServerStop() {
				System.out.println("--------Server Stopped--------");
			}
		};
		System.out.println("--------Server Started--------");
		server.start();
	}

	static void printInfo(SocketTransceiver st, String msg) {
		System.out.println("Client " + st.getInetAddress().getHostAddress());
		System.out.println("  " + msg);
	}
}
