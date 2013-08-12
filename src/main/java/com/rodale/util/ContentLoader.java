package com.rodale.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.XMLDocumentManager;

import com.marklogic.client.io.InputStreamHandle;

public class ContentLoader {
	
	public static void main(String[] args) throws IOException {

		System.out.println("Entering "+ContentLoader.class.getName());

		// create the client
		DatabaseClient client = DatabaseClientFactory.newClient(Config.host, Config.port, Config.user, Config.password, Config.authType);
		
		// acquire the content
		InputStream docStream = ContentLoader.class.getClassLoader().getResourceAsStream(
			"data"+File.separator+"RW10919-BRAIN-RW-SO_1_FOR_TEST.xml");

		// create a manager for XML documents
		XMLDocumentManager docMgr = client.newXMLDocumentManager();

		// create a handle on the content
		InputStreamHandle handle = new InputStreamHandle(docStream);
		
		//System.out.println ("Got stream handle: " + handle.toString());

		// write the document content
		docMgr.write("/root/content/articles/RW10919-BRAIN-RW-SO_1_FOR_TEST.xml", handle);

		System.out.println("Wrote /root/content/articles/RW10919-BRAIN-RW-SO_1_FOR_TEST.xml content");

		// release the client
		client.release();
	}

}
