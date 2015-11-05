package ManagementRequestHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import protocol.HttpRequest;
import protocol.HttpResponse;
import protocol.HttpResponseFactory;
import protocol.Protocol;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public class GetRequestHandler{

	/* (non-Javadoc)
	 * @see protocol.RequestHandler#file_exists()
	 */
	public static HttpResponse file_exists(String rootDirectory,String uri, File file, HttpRequest hr) {
		HttpResponse response = null;
		File f = new File("web/files.txt");
		response = HttpResponseFactory.create200OK(f, Protocol.CLOSE);
		return response;
	}

	/* (non-Javadoc)
	 * @see protocol.RequestHandler#file_no_exist()
	 */
	public static HttpResponse file_no_exist(String rootDirectory,String uri, File file, HttpRequest hr) {
		HttpResponse response = null;
		File f = new File("web/files.txt");
		response = HttpResponseFactory.create200OK(f, Protocol.CLOSE);
		return response;
	}
}
