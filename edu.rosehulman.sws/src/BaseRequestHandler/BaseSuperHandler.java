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
import protocol.Protocol;
import protocol.RequestHandler;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public class BaseSuperHandler extends RequestHandler{
	final static String guid = "0845bc29-a300-480e-a839-34672c76f84e";
	
	@Override
	public String getGUID(){
		return guid;
	}
	
	/* (non-Javadoc)
	 * @see protocol.RequestHandler#file_exists(java.lang.String, java.lang.String, java.io.File, protocol.HttpRequest)
	 */
	@Override
	protected HttpResponse file_exists(String rootDirectory, String uri,
			File f, HttpRequest hr) {
		switch(hr.getMethod().toUpperCase()){
		case Protocol.GET:
			return GetRequestHandler.file_exists(rootDirectory, uri, f, hr);
		case Protocol.PUT:
			return PutRequestHandler.file_exists(rootDirectory, uri, f, hr);
		case Protocol.POST:
			return PostRequestHandler.file_exists(rootDirectory, uri, f, hr);
		case Protocol.DELETE:
			return DeleteRequestHandler.file_exists(rootDirectory, uri, f, hr);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see protocol.RequestHandler#file_no_exist(java.lang.String, java.lang.String, java.io.File, protocol.HttpRequest)
	 */
	@Override
	protected HttpResponse file_no_exist(String rootDirectory, String uri,
			File f, HttpRequest hr) {
		HttpResponse hp = null;
		switch(hr.getMethod().toUpperCase()){
		case Protocol.GET:
			hp = GetRequestHandler.file_no_exist(rootDirectory, uri, f, hr);
			break;
		case Protocol.PUT:
			hp = PutRequestHandler.file_no_exist(rootDirectory, uri, f, hr);
			break;
		case Protocol.POST:
			hp = PostRequestHandler.file_no_exist(rootDirectory, uri, f, hr);
			break;
		case Protocol.DELETE:
			hp = DeleteRequestHandler.file_no_exist(rootDirectory, uri, f, hr);
			break;
		}
		return hp;
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
