/*
 * DirectoryWatcher.java
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
 
package server;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import protocol.DeleteRequestHandler;
import protocol.GetRequestHandler;
import protocol.PostRequestHandler;
import protocol.Protocol;
import protocol.PutRequestHandler;
import protocol.RequestHandler;

public class DirectoryWatcher implements Runnable {
	HashMap<String, RequestHandler> handlers;
	WatchService watcher;
	Path dir;
	DirectoryWatcher() {
		handlers = new HashMap<String, RequestHandler>();
		handlers.put(Protocol.GET, new GetRequestHandler());
		handlers.put(Protocol.POST, new PostRequestHandler());
		handlers.put(Protocol.PUT, new PutRequestHandler());
		handlers.put(Protocol.DELETE, new DeleteRequestHandler());
		try {
			watcher = FileSystems.getDefault().newWatchService();
			dir = (new File("./plugins")).toPath();
		    WatchKey key = dir.register(watcher,
		    		StandardWatchEventKinds.ENTRY_CREATE,
		    		StandardWatchEventKinds.ENTRY_DELETE,
		    		StandardWatchEventKinds.ENTRY_MODIFY);
		} catch (IOException x) {
		    System.err.println(x);
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while(true) {
		WatchKey key;
	    try {
	        key = watcher.take();
	    } catch (InterruptedException x) {
	        return;
	    }

	    for (WatchEvent<?> event: key.pollEvents()) {
	        WatchEvent.Kind<?> kind = event.kind();

	        if(kind == StandardWatchEventKinds.ENTRY_CREATE) {
	        	System.out.println("created");
	        } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
	        	System.out.println("deleted");
	        } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
	        	System.out.println("modified");
	        } else {
	        	continue;
	        }
	        // The filename is the
	        // context of the event.
	        WatchEvent<Path> ev = (WatchEvent<Path>)event;
	        Path filename = ev.context();
	        System.out.println(filename);
	    }

	    // Reset the key -- this step is critical if you want to
	    // receive further watch events.
	    key.reset();
		}
		
	}
	/**
	 * @return
	 */
	public HashMap<String, RequestHandler> getMap() {
		return handlers;
	}
}
