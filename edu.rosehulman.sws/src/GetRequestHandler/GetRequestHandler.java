package GetRequestHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import protocol.HttpRequest;
import protocol.HttpResponse;
import protocol.HttpResponseFactory;
import protocol.Protocol;
import protocol.RequestHandler;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public class GetRequestHandler extends RequestHandler {

	/* (non-Javadoc)
	 * @see protocol.RequestHandler#file_exists()
	 */
	protected HttpResponse file_exists(String rootDirectory,String uri, File file, HttpRequest hr) {
		HttpResponse response = null;
		//Exist_Fxn
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
	protected HttpResponse file_no_exist(String rootDirectory,String uri, File file, HttpRequest hr) {
		return HttpResponseFactory.create404NotFound(Protocol.CLOSE);
	}

	/* (non-Javadoc)
	 * @see protocol.RequestHandler#getCommand()
	 */
	public List<String> getCommand() {
		// TODO Auto-generated method stub
		List<String> s = new ArrayList<String>();
		s.add(Protocol.GET);
		return s;
	}

}
