/*
 * OkHttpResponse.java
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
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public class OkHttpResponse extends HttpResponse {


	/**
	 * @param version
	 * @param status
	 * @param phrase
	 * @param header
	 * @param file
	 * @param connection
	 */
	public OkHttpResponse(String version, int status, String phrase, Map<String, String> header, File file,
			String connection) {
		super(version, status, phrase, header, file, connection);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see protocol.HttpResponse#fillInRestOfHeader()
	 */
	@Override
	public void fillInRestOfHeader() {
		// Lets add last modified date for the file
				long timeSinceEpoch = getFile().lastModified();
				Date modifiedTime = new Date(timeSinceEpoch);
				put(Protocol.LAST_MODIFIED, modifiedTime.toString());
				
				// Lets get content length in bytes
				long length = getFile().length();
				put(Protocol.CONTENT_LENGTH, length + "");
				
				// Lets get MIME type for the file
				FileNameMap fileNameMap = URLConnection.getFileNameMap();
				String mime = fileNameMap.getContentTypeFor(getFile().getName());
				// The fileNameMap cannot find mime type for all of the documents, e.g. doc, odt, etc.
				// So we will not add this field if we cannot figure out what a mime type is for the file.
				// Let browser do this job by itself.
				if(mime != null) { 
					put(Protocol.CONTENT_TYPE, mime);
				}
		
	}
	
	


}
