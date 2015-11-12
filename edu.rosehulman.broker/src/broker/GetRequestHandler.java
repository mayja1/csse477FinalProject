package broker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import protocol.HttpRequest;
import protocol.HttpResponse;
import protocol.HttpResponseFactory;
import protocol.Protocol;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public class GetRequestHandler{
	static List<String> brokers;
	static int index = 0;
	static {
		brokers = new ArrayList<String>();
		brokers.add("137.112.231.233:8200");
		brokers.add("localhost:8120");
		
	};

	/* (non-Javadoc)
	 * @see protocol.RequestHandler#file_exists()
	 */
	public static HttpResponse file_exists(String rootDirectory,String uri, File file, HttpRequest hr) {
		HttpResponse response = null;
		//Exist_Fxn
		uri = "/servers.txt";
		PrintWriter writer;
		try {
			writer = new PrintWriter("web/servers.txt");
			writer.write(brokers.get(index % brokers.size()));
			index ++;
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(file.isDirectory()) {
			// Look for default index.html file in a directory
			String location = rootDirectory + uri + System.getProperty("file.separator") + Protocol.DEFAULT_FILE;
			file = new File(location);
			if(file.exists()) {
				// Lets create 200 OK response
				response = HttpResponseFactory.create200OK(file, Protocol.CLOSE);
			}
			else {
				// File does not exist so lets create 404 file not found code
				response = HttpResponseFactory.create404NotFound(Protocol.CLOSE);
			}
		}
		else { // Its a file
			// Lets create 200 OK response
			response = HttpResponseFactory.create200OK(file, Protocol.CLOSE);
		}
		return response;
	}

	/* (non-Javadoc)
	 * @see protocol.RequestHandler#file_no_exist()
	 */
	public static HttpResponse file_no_exist(String rootDirectory,String uri, File file, HttpRequest hr) {
		HttpResponse response = null;
		//Exist_Fxn
		uri = "/servers.txt";
		PrintWriter writer;
		try {
			writer = new PrintWriter("web/servers.txt");
			writer.write(brokers.get(index % brokers.size()));
			index ++;
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(file.isDirectory()) {
			// Look for default index.html file in a directory
			String location = rootDirectory + uri + System.getProperty("file.separator") + Protocol.DEFAULT_FILE;
			file = new File(location);
			if(file.exists()) {
				// Lets create 200 OK response
				response = HttpResponseFactory.create200OK(file, Protocol.CLOSE);
			}
			else {
				// File does not exist so lets create 404 file not found code
				response = HttpResponseFactory.create404NotFound(Protocol.CLOSE);
			}
		}
		else { // Its a file
			// Lets create 200 OK response
			response = HttpResponseFactory.create200OK(file, Protocol.CLOSE);
		}
		return response;
	}
}
