/*
 * FileDeleteRequestHandler.java
 * Nov 10, 2015
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
 
package FileRequestHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

import protocol.HttpRequest;
import protocol.HttpResponse;
import protocol.HttpResponseFactory;
import protocol.Protocol;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public class FileDeleteRequestHandler {
	/* (non-Javadoc)
	 * @see protocol.RequestHandler#file_exists(java.lang.String, java.lang.String, java.io.File, protocol.HttpRequest)
	 */
	public static HttpResponse file_exists(String rootDirectory, String uri, File f,
		HttpRequest hr) {
		try {
			File file = new File("web/" + f.getName());
			file.delete();
			//Need to update fileList because a file has been deleted
			File fileList = new File("web/" + "files.txt");
			BufferedReader br = new BufferedReader(new FileReader(fileList));
			fileList.delete();
			File newFileList = new File("web/" + "files.txt");
			Stream<String> lineStream = br.lines();
			Iterator<String> it = lineStream.iterator();
			while(it.hasNext()) {
				String next = it.next();
				if(!next.equals(f.getName())) {
					//add to newFileList
				}	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return HttpResponseFactory.create400BadRequest(Protocol.CLOSE);
		}
		return HttpResponseFactory.create200OK(f, Protocol.CLOSE);
	}

	/* (non-Javadoc)
	 * @see protocol.RequestHandler#file_no_exist(java.lang.String, java.lang.String, java.io.File, protocol.HttpRequest)
	 */
	public static HttpResponse file_no_exist(String rootDirectory, String uri, File f,
			HttpRequest hr) {
		System.out.println("File we are trying to delete: " + f.getName());
		return HttpResponseFactory.create404NotFound(Protocol.CLOSE);
	}
}
