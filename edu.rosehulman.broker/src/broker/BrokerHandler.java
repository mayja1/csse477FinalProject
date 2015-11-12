package broker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import protocol.HttpRequest;
import protocol.HttpResponse;
import protocol.Protocol;
import protocol.RequestHandler;

public class BrokerHandler extends RequestHandler {

	@Override
	protected HttpResponse file_exists(String rootDirectory, String uri,
			File f, HttpRequest hr) {
		// TODO Auto-generated method stub
		f = new File("web/servers.txt");
		return GetRequestHandler.file_exists(rootDirectory, uri, f, hr);
	}

	@Override
	protected HttpResponse file_no_exist(String rootDirectory, String uri,
			File f, HttpRequest hr) {
		// TODO Auto-generated method stub
		f = new File("web/servers.txt");
		return GetRequestHandler.file_no_exist(rootDirectory, uri, f, hr);
	}

	@Override
	public List<String> getCommand() {
		// TODO Auto-generated method stub
		List<String> s = new ArrayList<String>();
		s.add(Protocol.GET);
		return s;
	}

	@Override
	public String getURI() {
		// TODO Auto-generated method stub
		return "/broker";
	}

	@Override
	public String getGUID() {
		// TODO Auto-generated method stub
		return "0845bc29-a300-480e-a839-34672c76f84e";
	}

}
