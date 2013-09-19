package com.sample.elastic.docupload;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;

public class Main {

	
	public static void main(String[] args) throws IOException {
		String dataDirName="E:/Tutorial/JavaReference/introcs"; 
		/*String indexName="documents";
		String indexType="java_docs";
		String docTypeToIndex="java";*/
		String indexName="vishal_documents";
		String indexType="vishal_java_docs";
		String docTypeToIndex="java";
		ClientCreate clientCreate=new ClientCreate();
		TransportClient client= clientCreate.createClient();
		
		Indexer indexer=new Indexer();
		indexer.createIndex(indexName, client);
		indexer.putVishalMapping(client, indexType, indexName, "");
		clientCreate.closeClient();
	}
	
	

}
