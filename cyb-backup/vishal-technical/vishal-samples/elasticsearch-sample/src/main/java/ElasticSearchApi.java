import static org.elasticsearch.client.Requests.refreshRequest;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.fieldQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryString;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.codec.binary.Base64;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequestBuilder;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.elasticsearch.search.facet.terms.TermsFacet.Entry;
import org.elasticsearch.search.facet.terms.TermsFacetBuilder;

/**
 * 
 */

/**
 * @author vishalshu
 *
 */
public class ElasticSearchApi {

	private static void prepareContactIndex(String indexName,
			String entryType, TransportClient client, String subId, String subName, String keywordTest) {
				try {

					client.admin().indices().refresh(refreshRequest()).actionGet();
					String line;
					XContentBuilder jsonSource = jsonBuilder().startObject()
							.field("id", subId)
							.field("subscription_name", subName)
							.field("keyword_test", keywordTest)
							.endObject();
					System.out.println("JSON source is : " + jsonSource);
					
					IndexResponse response = client
							.prepareIndex(indexName, entryType, subId)
							.setSource(jsonSource
									)
							.execute().actionGet();

					System.out.println(response);
					/*IndexResponse response = client
							.prepareIndex(indexName, indexType, docId)
							.setSource(jsonSource).execute().actionGet();*/

					System.out.println("toString is : " + response.toString());
					System.out.println("Type is : " + response.type());
					System.out.println("Index is : " + response.index());
					System.out.println("ID is : " + response.id());
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		
	}

	private static void searchAllPhotosByQuery(String indexName, Client client) {
		client.admin().indices().refresh(refreshRequest()).actionGet();

		SearchResponse searchResponse = client.prepareSearch(indexName)
				.setSearchType(SearchType.QUERY_AND_FETCH)
				.setQuery(fieldQuery("mime_type", "jpg")).setFrom(0)
				.setSize(60).setExplain(true).execute().actionGet();
		
		//System.out.println(searchResponse);
	}
	
	private static void searchAllPhotosByFilter(String indexName, Client client) {
		client.admin().indices().refresh(refreshRequest()).actionGet();

		SearchResponse searchResponse = client.prepareSearch(indexName)
				.setSearchType(SearchType.QUERY_AND_FETCH)
				
				.setQuery(fieldQuery("mime_type", "jpg")).setFrom(0)
				.setSize(60).setExplain(true).execute().actionGet();
		//System.out.println(searchResponse);
	}
	
	private static void facetMimeTypes(String indexName, Client client) {
		client.admin().indices().refresh(refreshRequest()).actionGet();
		QueryBuilder queryBuilder = fieldQuery("mime_type", "jpg");
		
		TermsFacetBuilder termsFacetBuilder = new TermsFacetBuilder("category");
		termsFacetBuilder.exclude("content").field("category");
		/*TermsStatsFacetBuilder termsStatsFacetBuilder = new TermsStatsFacetBuilder("mimeCounts");
		termsStatsFacetBuilder.keyField("mime_type");
		termsStatsFacetBuilder.valueField("count");
		StatisticalFacetBuilder stFacetBuilder = new StatisticalFacetBuilder("stats");
		stFacetBuilder.field("mime_type");*/
		//termsFacetBuilder.global(true);
		
	//	FacetBuilders. queryFacet("mime_type", query)
		
		SearchResponse searchResponse = client.prepareSearch(indexName).addFields("")
				.setSearchType(SearchType.QUERY_AND_FETCH)
				
				.addFacet(termsFacetBuilder).setFrom(0)
				.setSize(60).setExplain(true).execute().actionGet();
		TermsFacet tf = searchResponse.facets().facet(TermsFacet.class, "category");
		System.out.println(tf.toString());
		for(Entry e : tf.getEntries()){
			System.out.println(e.getTerm());
			System.out.println(e.getCount());
		}
		System.out.println("Entries : "+tf.getEntries());
		System.out.println(searchResponse.facets().getFacets().get("category").getType());
		//_log.info("Faceted response is : " + searchResponse);
		//System.out.println("Faceted response is : " + searchResponse);
	}

	/**
	 * 
	 * @param indexName
	 * @param client
	 */
	private static void createIndex(String indexName, Client client) {

		CreateIndexResponse createIndexResponse = new CreateIndexRequestBuilder(
				client.admin().indices(), indexName).execute().actionGet();

		System.out.println("after index create call");

		if (createIndexResponse.acknowledged()) {
			System.out.println("created index");
		}
	}

	private static void deleteIndex(String indexName, Client client) {
		try {

			DeleteIndexResponse deleteIndexResponse = new DeleteIndexRequestBuilder(
					client.admin().indices(), indexName).execute().actionGet();

			if (deleteIndexResponse.acknowledged()) {
				System.out.println("Deleted index");
			}
		} catch (Exception e) {
			System.out.println("Index already deleted");
		}
	}

	private static void search(String keyword, TransportClient client) throws IOException {
		SearchRequest searchRequest = new SearchRequest();
		/*
		 * XContentBuilder searchSource = jsonBuilder().startObject()
		 * .field("content", fileContent).endObject();
		 */
		/*
		 * searchRequest.indices("docs3"); searchRequest.types("doc3");
		 * searchRequest.response =
		 */
		SearchResponse response = client.prepareSearch()
				// .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(queryString(keyword)).setFrom(0).setSize(60)
				.setExplain(true).execute().actionGet();
		// System.out.println("time : "+response.getTookInMillis());
		System.out.println("factes : " + response.getFacets());
		System.out.println("hits : " + response.getHits().getTotalHits());
		System.out.println("took : " + response.getTook());
		XContentBuilder builder = XContentFactory.jsonBuilder();
		builder.startObject();
		response.toXContent(builder, ToXContent.EMPTY_PARAMS).prettyPrint();
		builder.endObject();
		Iterator<SearchHit> hitsIterator = response.hits().iterator();
		while(hitsIterator.hasNext()){
			SearchHit hit = hitsIterator.next();
			//System.out.println(hit.getFields().keySet());
			System.out.println("source is : "+hit.getSource().get("entry_filename"));
			//System.out.println("Search Field is : "+searchField.getName() +" & Value is : "+searchField!=null?searchField.getValue():"null");
		}
		//System.out.println("Search response : "+builder.prettyPrint().string());
		/*System.out.println("Source : "+ response.getHits().getAt(0).source());
		Iterator<SearchHit> hitsIterator = response.hits().iterator();
		while(hitsIterator.hasNext()){
			SearchHit hit = hitsIterator.next();
			System.out.println(hit.getFields().keySet());
			//SearchHitField searchField = hit.field("entry_filename");
			//System.out.println("Search Field is : "+searchField.getName() +" & Value is : "+searchField!=null?searchField.getValue():"null");
		}*/
		
		// client.search(null);
	}

	private static void getMapping(String indexName, Client client) {
		GetRequestBuilder builder = new GetRequestBuilder(client);
		// builder.set
		/*
		 * Sear searchRequest = new SearchRequest(); XContentBuilder
		 * searchSource = jsonBuilder().startObject() .field("content",
		 * fileContent).endObject(); searchRequest.indices("docs3");
		 * searchRequest.types("doc3"); searchRequest.response = SearchResponse
		 * response = client.prepareSearch() //
		 * .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
		 * .setQuery(queryString(keyword)).setFrom(0).setSize(60)
		 * .setExplain(true).execute().actionGet();
		 * //System.out.println("time : "+response.getTookInMillis());
		 * System.out.println("factes : "+response.getFacets());
		 * System.out.println("hits : "+response.getHits().getTotalHits());
		 * System.out.println("took : "+response.getTook());
		 * System.out.println(response.toString());
		 */
		// client.search(null);
	}

	private static void createStorageEntryMapping(String indexName,
			String type, Client client) throws IOException {
		// GetRequestBuilder builder = new GetRequestBuilder(client);
		XContentBuilder contentBuilder = jsonBuilder().startObject()
				.field(type).startObject().field("_source").startObject().field("enabled",false).endObject()
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
				client.admin().indices()).setIndices(indexName).setType(type)
				.setSource(contentBuilder).execute().actionGet();

		if (putMappingResponse.acknowledged()) {
			System.out.println("successfully defined mapping");
		}

	}
	
	private static void createSubscriptionMapping(String indexName,
			String type, Client client) throws IOException {
		// GetRequestBuilder builder = new GetRequestBuilder(client);
		XContentBuilder contentBuilder = jsonBuilder().startObject()
				.field(type).startObject().field("_source").startObject().field("enabled",false).endObject()
				.field("properties").startObject()
				.field("id").startObject().field("type", "string").field("store", "yes")
				.endObject()

				.field("subscription_name").startObject().field("type", "string").field("store", "yes")
				.field("keyword_test").startObject().field("type", "string").field("store", "yes").field("index","analyzed").field("_analyzer", "keyword")
				.endObject()
				.endObject().endObject().endObject();
		System.out.println("PutMapping content is : "
				+ (contentBuilder.prettyPrint()).string());

		PutMappingResponse putMappingResponse = new PutMappingRequestBuilder(
				client.admin().indices()).setIndices(indexName).setType(type)
				.setSource(contentBuilder).execute().actionGet();

		if (putMappingResponse.acknowledged()) {
			System.out.println("successfully defined mapping");
		}

	}

	/*private static void prepareStorageEntryIndex(String indexName,
			String indexType, Client client, String subscriptionId, String subName) throws Exception {
		String docId = null;
		File dataDir = new File(dataDirName);
		if (dataDir.exists() && dataDir.isDirectory()) {
			File[] files = dataDir.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return true;
				}
			});
			for (File file : files) {
				try {

					docId = file.getName();

					client.admin().indices().refresh(refreshRequest()).actionGet();
					String line;
					System.out.println("doc id split"+docId.split("\\."));
					String fileContent = readContent(file);
					XContentBuilder jsonSource = jsonBuilder().startObject()
							.field("subscriber_key", subName)
							.field("subscription_id", subscriptionId)
							.field("entry_name", docId)
							.field("mime_type", getCategory(docId))
							.field("hash", "lkajsdlfkhalsmd")
							.field("thumbnail", fileContent).endObject();
					System.out.println("JSON source is : " + jsonSource);
					
					IndexResponse response = client
							.prepareIndex(indexName, indexType, docId)
							.setSource(jsonSource
									)
							.execute().actionGet();

					System.out.println(response);
					IndexResponse response = client
							.prepareIndex(indexName, indexType, docId)
							.setSource(jsonSource).execute().actionGet();

					System.out.println("toString is : " + response.toString());
					System.out.println("Type is : " + response.type());
					System.out.println("Index is : " + response.index());
					System.out.println("ID is : " + response.id());
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

	}
*/
	private static String getCategory(String docId) {
		if(docId.endsWith("jpg")){
			return "photos";
		}if(docId.endsWith("pdf")){
			return "document";
		}if(docId.endsWith("ppt")){
			return "presentations";
		}if(docId.endsWith("rar") || docId.endsWith("zip")){
			return "compressed";
		}else {
			return "unknown";
		}
		
	}

/*	private static void prepareIndex(String indexName, String indexType,
			Client client) {
		String docId = null;
		File dataDir = new File(dataDirName);
		if (dataDir.exists() && dataDir.isDirectory()) {
			File[] files = dataDir.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith("jpg");
				}
			});
			for (File file : files) {
				try {
					BufferedReader reader = new BufferedReader(new FileReader(
							file));
					String line;
					StringBuilder fileContent = new StringBuilder();
					while ((line = reader.readLine()) != null) {
						fileContent.append(line);
					}
					
					XContentBuilder jsonSource = jsonBuilder().startObject()
							.field("content", fileContent).endObject();
					System.out.println("JSON source is : " + jsonSource);
					docId = file.getName();
					IndexResponse response = client
							.prepareIndex(indexName, indexType, docId)
							.setSource(jsonSource).execute().actionGet();

					System.out.println("toString is : " + response.toString());
					System.out.println("Type is : " + response.type());
					System.out.println("Index is : " + response.index());
					System.out.println("ID is : " + response.id());
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}

	}
*/
	/*private static String readContent(File file) throws Exception {

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		InputStream input = new BufferedInputStream(new FileInputStream(file));

		int read = -1;

		while ((read = input.read()) != -1) {
			output.write(read);
		}

		input.close();
		
		return Base64.encodeBase64String(output.toByteArray());
	}*/
}
