/*
 * RequestHandler.java
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
public abstract class RequestHandler {
	public HttpResponse Process(HttpRequest h, Server s){
		HttpResponse response = null;
		// Handling GET request here
		// Get relative URI path from request
		String uri = h.getUri();
		// Get root directory path from server
		String rootDirectory = s.getRootDirectory();
		// Combine them together to form absolute file path
		File file = new File(rootDirectory + uri);
		// Check if the file exists
		if(file.exists()) {
			response = file_exists(rootDirectory, uri, file, h);
		}
		else {
			response = file_no_exist(rootDirectory, uri, file, h);
		}
		return response;
	}
	abstract HttpResponse file_exists(String rootDirectory,String uri, File f, HttpRequest hr);
	abstract HttpResponse file_no_exist(String rootDirectory,String uri, File f, HttpRequest hr);
	abstract String getCommand();
}
