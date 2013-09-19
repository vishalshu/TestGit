import static org.elasticsearch.index.query.QueryBuilders.boolQuery;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.map.module.SimpleModule;
import org.elasticsearch.ElasticSearchException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.delete.DeleteMappingResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.ClusterState;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TextQueryBuilder;
import org.elasticsearch.search.SearchHit;

public class ContactIndexTest {

	private static String INDEX_NAME = "es-session-test";
	private static String CONTACT_TYPE = "contact_type";
	private static TransportClient client;

	static {
		client = new TransportClient(ImmutableSettings.settingsBuilder()/*
																		 * .put(
																		 * "cluster.name"
																		 * ,
																		 * "tokioo-demo"
																		 * )
																		 */
		.build());
		/*
		 * client.addTransportAddress(new InetSocketTransportAddress(
		 * "10.10.0.144", 9300));
		 */
		client.addTransportAddress(new InetSocketTransportAddress("localhost",
				9300));
	}

	public static void main(String[] args) throws ElasticSearchException,
			IOException {

		// client.admin().indices().create()
		ContactIndex contactIndex = new ContactIndex();
		contactIndex.setContactId("11111");
		contactIndex.setFamilyName("Borad");
		contactIndex.setGivenName("Dharmesh");
		contactIndex.addCategoryName(Category.CONTACT);
		// friendIndex.addCollectionName("UpdatedCollectionName");
		contactIndex.setNickname("D");
		contactIndex.setPrimaryEmail("tirthalp@cybage.com");
		contactIndex.setOrganization("Cybage Software Pvt Ltd");
		//findEntries();
		addContactIndexEntry(contactIndex);
		//updateFriendIndexEntry("c76a767c-adde-4046-ab8b-e00f6bfa38c4",
				//friendIndex);
	}

	private static void findEntries() throws JsonGenerationException, JsonMappingException, IOException {
		TextQueryBuilder fav = new TextQueryBuilder("organization", "Cybage Software Pvt Ltd");
		//fav.type(Type.PHRASE_PREFIX);
		QueryBuilder boolQuery = boolQuery().must(fav);
		
		SearchResponse scrollResp = client.prepareSearch(INDEX_NAME).setTypes(CONTACT_TYPE)
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(boolQuery)
                .setExplain(true).setSize(20).execute().actionGet();
		
		for(SearchHit hit:scrollResp.getHits()){
			//System.out.println(hit.getSource().toString());
			System.out.println("----------------------------------------------");
			ObjectMapper mapper = new ObjectMapper();
			SimpleModule restModule = new SimpleModule("RestModule", new Version(1,
					0, 0, null));
			// assuming serializer
			// declares correct 
			// class to bind to
			mapper.registerModule(restModule);
			ContactIndex friendIndex = mapper.readValue(hit.sourceAsString(), ContactIndex.class);
			ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
			String jsonString = writer.writeValueAsString(friendIndex);
			System.out.println("Index entry : "+jsonString);
		}
		
	}

	private static void updateFriendIndexEntry(String id,
			ContactIndex friendIndex) throws JsonGenerationException,
			JsonMappingException, IOException {

		/*
		 * ObjectMapper mapper = new ObjectMapper(); SimpleModule restModule =
		 * new SimpleModule("RestModule", new Version(1, 0, 0, null)); //
		 * assuming serializer // declares correct // class to bind to
		 * mapper.registerModule(restModule); String jsonString =
		 * mapper.writeValueAsString(friendIndex);
		 * 
		 * System.out.println(jsonString); UpdateResponse response = client
		 * .prepareUpdate(INDEX_NAME, FRIEND_MAPPING_NAME, id) //
		 * .setScript("ctx._source=\""+jsonString+"\"").execute().actionGet();
		 */
		indexEntry(id, friendIndex);
	}

	private static void indexEntry(String id, ContactIndex friendIndex) throws JsonGenerationException, JsonMappingException, IOException {
		if (!isIndexExist(client, INDEX_NAME)) {
			boolean created = createIndex(INDEX_NAME, client);
			System.out.println("New index -  " + INDEX_NAME
					+ " created - status is : " + created);
		}
		createContactMapping(client, INDEX_NAME, CONTACT_TYPE);
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule restModule = new SimpleModule("RestModule", new Version(1,
				0, 0, null));
		// assuming serializer
		// declares correct
		// class to bind to
		mapper.registerModule(restModule);
		
		ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
		
		String jsonString = writer.writeValueAsString(friendIndex);
		
		System.out.println("Index entry : "+jsonString);
		IndexResponse response = client
				.prepareIndex(INDEX_NAME, CONTACT_TYPE,id).setSource(jsonString.getBytes()).execute().actionGet();
		
	}

	private static Boolean createContactMapping(Client client,
			String indexName, String mappingType)
			throws ElasticSearchException, IOException {
		boolean overwrite = false;
		boolean createMapping = false;
		boolean mappingExists = isMappingExist(client, INDEX_NAME,
				CONTACT_TYPE);

		if (mappingExists && overwrite) {
			DeleteMappingResponse resp = client.admin().indices()
					.prepareDeleteMapping(indexName).setType(mappingType)
					.execute().actionGet();
			createMapping = true;
		}

		if (!mappingExists) {
			createMapping = true;
		}

		if (createMapping) {
			PutMappingResponse response = client.admin().indices()
					.preparePutMapping().setIndices(indexName)
					.setType(mappingType).setSource(getMappingSource())
					.execute().actionGet();
			System.out.println("New mapping - " + CONTACT_TYPE
					+ " created - status is : " + response.acknowledged());
			return response.acknowledged();
		}
		return false;
	}

	private static boolean isMappingExist(Client client, String index,
			String type) {

		ClusterState cs = client.admin().cluster().prepareState()
				.setFilterIndices(index).execute().actionGet().getState();

		IndexMetaData imd = cs.getMetaData().index(index);

		if (imd == null)
			return false;

		MappingMetaData mdd = imd.mapping(type);

		if (mdd != null)
			return true;

		return false;

	}

	private static boolean isIndexExist(Client client, String indexName) {
		ClusterState cs = client.admin().cluster().prepareState()
				.setFilterIndices(indexName).execute().actionGet().getState();

		IndexMetaData imd = cs.getMetaData().index(indexName);

		if (imd == null)
			return false;
		else
			return true;
	}

	private static String getMappingSource() throws IOException {
		return IOUtils.toString(ContactIndexTest.class
				.getResourceAsStream("mapping-contact_entry.txt"));
	}

	private static boolean createIndex(String indexName, Client client) {
		CreateIndexResponse createIndexResponse = new CreateIndexRequestBuilder(
				client.admin().indices(), indexName).execute().actionGet();
		return createIndexResponse.acknowledged();
	}

	private static ContactIndex addContactIndexEntry(ContactIndex indexEntry)
			throws JsonGenerationException, JsonMappingException, IOException {
		indexEntry(UUID.randomUUID().toString(), indexEntry); //
		return indexEntry;
	}
}
