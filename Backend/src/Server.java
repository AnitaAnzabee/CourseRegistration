import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Provides data fields and methods to create a Java data-type, representing a 
 * server in a Java application.
 * The server communicates with the client through a socket to receive and send data.
 * @author Anita, Nolan, Ben
  */
public class Server {

	/**
	 * a socket to allow communication between server and client
	 */
	private Socket aSocket; 
	/**
	 * this socket accepts connections
	 */
	private ServerSocket serverSocket; 
	/**
	 * this object is used to write to the socket
	 */
	private PrintWriter socketOut;
	/**
	 * this object is used to read from the socket
	 */
	private BufferedReader socketIn;
	/**
	 * a DataBaseController object used for getting information from the database
	 */
	private DataBaseController dataBase;
	/**
	 * this object is used for creating a thread pool and
	 * executing a thread pool
	 */
	private ExecutorService pool;
	
	/**
	 * constructs a Server object with the specified value for port
	 * creates a thread pool using ExecutorService.
	 * @param port number of the host
	 */
	public Server(int port) {
		try {
			serverSocket=new ServerSocket(port);
			dataBase=new DataBaseController();
			pool=Executors.newFixedThreadPool(1);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * creates a serverController object to receive and process commands from the client
	 * @throws IOException
	 */
	public void communicateWithClient() throws IOException{
		try {
			
			while(true) {
				ServerController serverController=new ServerController(serverSocket.accept(),dataBase);
				pool.execute(serverController);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		closeConnection();
	}
	
	/**
	 * Closes all streams.
	 */
	private void closeConnection() {
		try {
			socketIn.close();
			socketOut.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Run the server
	 * @param args
	 * @throws IOException
	 */
	public static void main(String [] args)throws IOException{
		Server server= new Server (8099);
		System.out.println("Server is now running.");
		server.communicateWithClient();
	}
}
