package TestRequestHandler;

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
		File f = new File("Testing");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			String s = "We found a Test Object. In the Thingy";
			bw.write(s.toCharArray());
			bw.close();
			response = HttpResponseFactory.create200OK(f, Protocol.CLOSE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	/* (non-Javadoc)
	 * @see protocol.RequestHandler#file_no_exist()
	 */
	public static HttpResponse file_no_exist(String rootDirectory,String uri, File file, HttpRequest hr) {
		HttpResponse response = null;
		File f = new File("Testing");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			String s = "We found a Test Object. In the Thingy";
			bw.write(s.toCharArray());
			bw.close();
			response = HttpResponseFactory.create200OK(f, Protocol.CLOSE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}
}
