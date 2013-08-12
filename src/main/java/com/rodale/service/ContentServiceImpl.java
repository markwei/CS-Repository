package com.rodale.service;


import org.w3c.dom.Document;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DOMHandle;

import com.rodale.util.Config;
import java.io.*;


public class ContentServiceImpl implements ContentService {

	@Override
	public String getArticle() {
		// create the client
		System.out.println ("ContentServiceImpl.getArticle");
		try {
		System.out.println (Config.host);
		} catch (Exception e) {
			System.out.println ("## "+ e.toString());
		}
				DatabaseClient client = DatabaseClientFactory.newClient(Config.host, Config.port, Config.user, Config.password, Config.authType);
				System.out.println (" got client: " +client);
				// create a manager for XML documents
				XMLDocumentManager docMgr = client.newXMLDocumentManager();
				System.out.println("Got docMgr: " +docMgr.getContentFormat().name());

				// create a handle to receive the document content
				DOMHandle handle = null;

				try {				
					handle = new DOMHandle();
					if (handle != null) {
					System.out.println("Got  handle mime type " + handle.getMimetype());
					System.out.println("Got  handle byte length " + handle.getByteLength());
					} else {
						System.out.println("handle is null");
					}
				} catch (Exception e){
					System.out.println ("error at handle:" + e.toString());
				}
				// read the document content
				try {
					docMgr.read("/root/content/articles/RW10919-BRAIN-RW-SO_1_FOR_TEST.xml", handle);
				}catch (Exception e) {
					System.out.println ("error at read:" + e.toString());
				}
				// access the document content
				Document document = handle.get();
				System.out.println("Got document: " +document);
				System.out.println("begin to read");
				String rootName = document.getDocumentElement().getTagName();
				System.out.println("Read /root/content/articles/RW10919-BRAIN-RW-SO_1_FOR_TEST.xml content with the <"+rootName+"/> root element");
				
				
//				try {
//				File file = new File ("C:\\docs\\MarkLogic Syndicate\\MarkLogic Java API\\java-api-tutorial\\src\\main\\resources\\data\\flipper.xml");
//				BufferedReader reader = new BufferedReader(new FileReader(file));
//				String line = null;
//				while ((line = reader.readLine()) != null) {
//					System.out.println (line);
//				}
//				}catch (Exception e){
//					
//				}
				// release the client
				client.release();
		return null;
	}
	
	public static void main (String[] args) {
		ContentService cs = new ContentServiceImpl();
		cs.getArticle();
	}

}
