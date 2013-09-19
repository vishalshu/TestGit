package com.sample.elastic.docupload;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;

public class Indexer {
	public static String readInputStream(InputStream is) throws IOException {
		if (is == null)
			throw new IllegalArgumentException("stream mustn't be null!");

		BufferedReader bufReader = new BufferedReader(new InputStreamReader(is,
				"UTF8"));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = bufReader.readLine()) != null) {
			sb.append(line);
			sb.append('\n');
		}
		bufReader.close();
		return sb.toString();
	}
	
	/**
	 * 
	 * @param indexName
	 * @param client
	 */
	public static void createIndex(String indexName, Client client) {

		CreateIndexResponse createIndexResponse = new CreateIndexRequestBuilder(
				client.admin().indices(), indexName).execute().actionGet();

		System.out.println("after index create call");

		if (createIndexResponse.acknowledged()) {
			System.out.println("created index");
		}
	}

	public void putVishalMapping(TransportClient client, String indexType,
			String indexName, String fileName) throws IOException {
		
		// GetRequestBuilder builder = new GetRequestBuilder(client);
		XContentBuilder contentBuilder = jsonBuilder().startObject()
				.field(indexType).startObject().field("_source").startObject().field("enabled",false).endObject()
				.field("properties").startObject()
				.field("subscriber_key").startObject().field("type", "string")
				.endObject()

				.field("subscription_id").startObject().field("type", "string").field("store", "yes")
				.endObject()

				.field("entry_name").startObject().field("type", "string").field("store", "yes")
				.endObject()

				.field("mime_type").startObject().field("type", "string")
				.endObject()

				.field("hash").startObject().field("type", "string")
				.endObject()

				.field("thumbnail").startObject()
					.field("term_vector", "with_positions_offsets")
					.field("store", "no").field("type", "attachment").endObject()
				.endObject().endObject().endObject();
		System.out.println("PutMapping content is : "
				+ (contentBuilder.prettyPrint()).string());
	
		PutMappingResponse putMappingResponse = new PutMappingRequestBuilder(
				client.admin().indices()).setIndices(indexName).setType(indexType)
				.setSource(contentBuilder).execute().actionGet();

		if (putMappingResponse.acknowledged()) {
			System.out.println("successfully defined mapping");
		}
		/*InputStream stream = new FileInputStream("mapping_doc_entry.txt");
		ClassLoader.getSystemClassLoader().getResourceAsStream(
				"mapping_doc_entry.txt");
		String mapping = IOUtils.toString(stream);
		PutMappingResponse putMappingResponse = new PutMappingRequestBuilder(
				client.admin().indices()).setIndices(indexName).setType(
				indexType).setSource(mapping).execute().actionGet();
		System.out.println(putMappingResponse.toString());*/
	}

	public void prepareIndex(String dataDirName, String indexName,
			String indexType, TransportClient client, String docTypeToIndex) {
		String docId = null;
		File dataDir = new File(dataDirName);
		final String physicalDocType = docTypeToIndex;

		/*
		 * new CreateIndexRequest(indexName).mapping(indexType, mapping);
		 */

		if (dataDir.exists() && dataDir.isDirectory()) {
			File[] files = dataDir.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(physicalDocType);
				}
			});

			File[] dirs = dataDir.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.contains(".");
				}
			});
			for (File dir : dirs) {
				prepareIndex(dir.getName(), indexName, indexType, client,
						docTypeToIndex);
			}
			for (File file : files) {
				try {
					if (file.isDirectory()) {
						prepareIndex(file.getName(), indexName, indexType,
								client, docTypeToIndex);
					} else {
						BufferedReader reader = new BufferedReader(
								new FileReader(file));
						String line;
						StringBuilder fileContent = new StringBuilder();
						while ((line = reader.readLine()) != null) {
							fileContent.append(line);
						}

						/*
						 * String mapping = IOUtils.toString(ElasticSearch.class
						 * .getResourceAsStream("mapping_doc_entry.txt")); new
						 * CreateIndexRequest(indexName).mapping(indexType,
						 * mapping); PutMappingResponse putMappingResponse = new
						 * PutMappingRequestBuilder(
						 * client.admin().indices()).setIndices(indexName).setType(indexType)
						 * .setSource(mapping).execute().actionGet();
						 */
						//System.out.println(putMappingResponse.toString());
						XContentBuilder jsonSource = jsonBuilder()
								.startObject().field("content", fileContent)
								.endObject();

						/*
						 * jsonBuilder() .startObject().field("type")
						 * .startObject().field("_source")
						 * .startObject().field("enabled",false).endObject()
						 * .field("properties")
						 * .startObject().field("id").startObject().field("type",
						 * "string").field("store", "yes") .endObject()
						 * 
						 * .field("subscription_name").startObject().field("type",
						 * "string").field("store", "yes")
						 * .field("keyword_test").startObject().field("type",
						 * "string").field("store",
						 * "yes").field("index","analyzed").field("_analyzer",
						 * "keyword") .endObject()
						 * .endObject().endObject().endObject();
						 */

						docId = file.getName();

						IndexResponse response = client.prepareIndex(indexName,
								indexType, docId).setSource(jsonSource)
								.execute().actionGet();

						System.out.println("toString is : "
								+ response.toString());
						System.out.println("Type is : " + response.type());
						System.out.println("Index is : " + response.index());
						System.out.println("ID is : " + response.id());
					}
				} catch (FileNotFoundException ex) {

					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
}
