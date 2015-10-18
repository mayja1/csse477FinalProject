/*
 * GetRequestHandler.java
 * Oct 18, 2015
 *
 * Simple Web Server (SWS) for EE407/507 and CS455/555
 * 
 * Copyright (C) 2011 Chandan Raj Rupakheti, Clarkson University
 * 
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License 
 * as published by the Free Software Foundation, either 
 * version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/lgpl.html>.
 * 
 * Contact Us:
 * Chandan Raj Rupakheti (rupakhcr@clarkson.edu)
 * Department of Electrical and Computer Engineering
 * Clarkson University
 * Potsdam
 * NY 13699-5722
 * http://clarkson.edu/~rupakhcr
 */
 
package protocol;

import java.io.File;

import server.Server;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public class GetRequestHandler extends RequestHandler {

	/* (non-Javadoc)
	 * @see protocol.RequestHandler#file_exists()
	 */
	@Override
	HttpResponse file_exists(String rootDirectory,String uri, File file, HttpRequest hr) {
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
	@Override
	HttpResponse file_no_exist(String rootDirectory,String uri, File file, HttpRequest hr) {
		return HttpResponseFactory.create404NotFound(Protocol.CLOSE);
	}

}
