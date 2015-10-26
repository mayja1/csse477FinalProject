/*
 * BaseSuperHandler.java
 * Oct 25, 2015
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
 
package BaseRequestHandler;

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
public class BaseSuperHandler extends RequestHandler{

	/* (non-Javadoc)
	 * @see protocol.RequestHandler#file_exists(java.lang.String, java.lang.String, java.io.File, protocol.HttpRequest)
	 */
	@Override
	protected HttpResponse file_exists(String rootDirectory, String uri,
			File f, HttpRequest hr) {
		switch(hr.getMethod().toUpperCase()){
		case "GET":
			return GetRequestHandler.file_exists(rootDirectory, uri, f, hr);
		case "PUT":
			return PutRequestHandler.file_exists(rootDirectory, uri, f, hr);
		case "POST":
			return PostRequestHandler.file_exists(rootDirectory, uri, f, hr);
		case "DELETE":
			return DeleteRequestHandler.file_exists(rootDirectory, uri, f, hr);
		}
		return HttpResponseFactory.create400BadRequest(Protocol.CLOSE);
	}

	/* (non-Javadoc)
	 * @see protocol.RequestHandler#file_no_exist(java.lang.String, java.lang.String, java.io.File, protocol.HttpRequest)
	 */
	@Override
	protected HttpResponse file_no_exist(String rootDirectory, String uri,
			File f, HttpRequest hr) {
		switch(hr.getMethod().toUpperCase()){
		case "GET":
			return GetRequestHandler.file_no_exist(rootDirectory, uri, f, hr);
		case "PUT":
			return PutRequestHandler.file_no_exist(rootDirectory, uri, f, hr);
		case "POST":
			return PostRequestHandler.file_no_exist(rootDirectory, uri, f, hr);
		case "DELETE":
			return DeleteRequestHandler.file_no_exist(rootDirectory, uri, f, hr);
		}
		return HttpResponseFactory.create400BadRequest(Protocol.CLOSE);
	}

	/* (non-Javadoc)
	 * @see protocol.RequestHandler#getCommand()
	 */
	@Override
	public List<String> getCommand() {
		// TODO Auto-generated method stub
		List<String> s = new ArrayList<String>();
		s.add(Protocol.GET);
		s.add(Protocol.PUT);
		s.add(Protocol.POST);
		s.add(Protocol.DELETE);
		return s;
	}

	/* (non-Javadoc)
	 * @see protocol.RequestHandler#getURI()
	 */
	@Override
	public String getURI() {
		// TODO Auto-generated method stub
		return "";
	}
	

}