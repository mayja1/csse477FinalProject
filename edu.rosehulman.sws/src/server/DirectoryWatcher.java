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
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import protocol.Protocol;
import protocol.RequestHandler;

public class DirectoryWatcher implements Runnable {
	HashMap<String, RequestHandler> handlers;
	WatchService watcher;
	Path dir;
	DirectoryWatcher() {
		handlers = new HashMap<String, RequestHandler>();
		try {
			watcher = FileSystems.getDefault().newWatchService();
			dir = (new File("./plugins")).toPath();
		    WatchKey key = dir.register(watcher,
		    		StandardWatchEventKinds.ENTRY_CREATE,
		    		StandardWatchEventKinds.ENTRY_DELETE,
		    		StandardWatchEventKinds.ENTRY_MODIFY);
		    loadInitialPlugins();
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
	        WatchEvent<Path> ev = (WatchEvent<Path>)event;
	        Path filename = ev.context();
	        System.out.println(filename);
	        Class c = loadClass(dir + "/" + filename);
	        if(kind == StandardWatchEventKinds.ENTRY_CREATE) {
	        		try {
						RequestHandler h = (RequestHandler) c.newInstance();
						for(String s: h.getCommand()){
							handlers.put(s, h);
						}
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
	        	System.out.println("deleted");
	        } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
	        	System.out.println("modified");
	        } else {
	        	continue;
	        }
	        // The filename is the
	        // context of the event.
	        
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
	
	public Class loadClass(String pathToJar) {
		try {
		JarFile jarFile = new JarFile(pathToJar);
		Enumeration<JarEntry> e = jarFile.entries();

		URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);

		    while (e.hasMoreElements()) {
		        JarEntry je = (JarEntry) e.nextElement();
		        if(je.isDirectory() || !je.getName().endsWith(".class")){
		            continue;
		        }
		    // -6 because of .class
		    String className = je.getName().substring(0,je.getName().length()-6);
		    className = className.replace('/', '.');
		    Class c = cl.loadClass(className);
		    return c;

		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void loadInitialPlugins() {
		File f = new File(dir.toString());
		for(File file: f.listFiles()){
			String path = "./plugins/" + file.getName();
			System.out.println("file path" + path);
			Class c = loadClass(path);
			if(c != null) {
	        		try {
						RequestHandler h = (RequestHandler) c.newInstance();
						for(String s: h.getCommand()){
							System.out.println("adding: " + s);
							handlers.put(s, h);
						}
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} else {
				System.out.println("Class was null");
			}
		}
	}
}
