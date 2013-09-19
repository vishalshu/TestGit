import static org.elasticsearch.client.Requests.refreshRequest;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.fieldQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryString;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TextQueryBuilder;
import org.elasticsearch.index.query.TextQueryBuilder.Type;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.elasticsearch.search.facet.terms.TermsFacet.Entry;
import org.elasticsearch.search.facet.terms.TermsFacetBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;

public class Test {
	final static String TIME_LOG = " ############## TIME TAKEN FOR - ";
	
	
	public static void main(String[] args) throws Exception {
		// esearch client creation
		// Node node = nodeBuilder().node();
		
		
		//System.out.println(d);
		TransportClient client = new TransportClient(ImmutableSettings
				.settingsBuilder()/*.put("cluster.name", "tokioo-demo")*/
				.build());
		/*client.addTransportAddress(new InetSocketTransportAddress(
						"10.10.0.144", 9300));*/
		client.addTransportAddress(new InetSocketTransportAddress(
				"10.100.0.100", 9300));
		// client.admin().indices().create()
		String indexName = "es-session-test-01";
		String indexType = "contact_type";
		
		//QueryBuilder recent = termQuery("recent", "true");
		
		
		/*
		TextQueryBuilder fav = new TextQueryBuilder("lyrics", "Here Is Your Paradise");
		fav.type(Type.PHRASE_PREFIX);
		QueryBuilder boolQuery = boolQuery().must(fav);
		
		SearchResponse scrollResp = client.prepareSearch(indexName).setTypes(indexType)
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(boolQuery)
                .setExplain(true).setSize(20).execute().actionGet();
		for(SearchHit hit:scrollResp.getHits()){
			//hit.getSource().get
		}*/
		
		//createIndex(indexName, client);
		System.out.println();
	/*	StopWatch watch = new StopWatch();
		watch.start("DeleteIndex");
		deleteIndex(indexName, client);
		watch.stop();
		
		watch.start("CreateIndex");
		createIndex(indexName, client);
		watch.stop();
		
		watch.start("CreateMapping");
		createStorageEntryMapping(indexName, entryType, client);
		createSubscriptionMapping(indexName, entryType, client);
		watch.stop();
		
		prepareSubscriptionIndex(indexName, subscriptionType, client,"1","4711DropboxSubscription","Db Subscription test");
		prepareSubscriptionIndex(indexName, subscriptionType, client,"2","Sve01DropboxSubscription","THis is Db Subscription test ");
		prepareSubscriptionIndex(indexName, subscriptionType, client,"3","Sve01MyDriveSubscription","Db Subscription test This is TEST here");
		
		watch.start("PrepareIndex1");
		prepareStorageEntryIndex(indexName, entryType, client, "1", "4711");
		watch.stop();
		
		
		watch.start("PrepareIndex2");
		prepareStorageEntryIndex(indexName, entryType, client, "2", "sven-test01");
		watch.stop();
		
		watch.start("PrepareIndex3");
		prepareStorageEntryIndex(indexName, entryType, client, "3", "sven-test01");
		watch.stop();*/
		
/*		
		
		
		
		
		
		*/
		
	/*	watch.start("PhotosQuery");
		searchAllPhotosByQuery(indexName, client);
		watch.stop();
		
		watch.start("PhotosQuery2");
		searchAllPhotosByQuery(indexName, client);
		watch.stop();
		
		watch.start("PhotosFilter");
		searchAllPhotosByFilter(indexName, client);
		watch.stop();*/
		
		/*
		//searchAllPhotos(indexName, client);
		//search("VariableBinding ", client);
		facetMimeTypes(indexName, client);
		*/
		//watch.start("bool search");
		/*Scanner sc = new Scanner(System.in);
		
		String queryStr = sc.next();*/
		//final QueryStringQueryBuilder query = new QueryStringQueryBuilder(queryStr);
		
		//SortBuilder sb1 = new FieldSortBuilder("subscription_id");
		SortBuilder sb2 = new FieldSortBuilder("title");
		
		//sb.missing("_first");
		QueryBuilder fqb = termQuery("category_names", "audio");
		/*QueryBuilder recent = termQuery("recent", "true");
		QueryBuilder fav = termQuery("favorite", "true");*/
		//QueryBuilder coll = fieldQuery("collection_names", "Sven Tests a Collection");
		//QueryBuilder qb = termQuery("recent", true);
		
	//	QueryBuilder prefixQ = ("title", "the");
		
	/*	QueryBuilder boolQuery = boolQuery().must(recent).must(fqb).must(fav); 
		SearchResponse scrollResp = client.prepareSearch(indexName)
                .setSearchType(SearchType.QUERY_AND_FETCH)
                .setQuery(boolQuery)
                .setExplain(true).execute().actionGet();
		watch.stop();*/
		/*SearchResponse searchResponse = client
				.prepareSearch()
				.setSearchType(SearchType.QUERY_AND_FETCH)
				.setQuery()
				.setFrom(0).setSize(2)
				.setExplain(true).execute().actionGet();*/
		//System.out.println(scrollResp.getHits().getHits().length + " - time : "+watch.prettyPrint());
		/*for(SearchHit hit: scrollResp.getHits()){
			
			
		}*/
		
		//System.out.println(searchResponse);
		/*for(SearchHit hit : searchResponse.hits()){
			System.out.println("entry name : "+hit.getFields().get("title").getValue());
		}*/
		//_log.info(TIME_LOG+ watch.prettyPrint());
	}

}
