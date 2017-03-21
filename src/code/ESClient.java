package code;

import java.io.IOException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import code.domain.User;

public class ESClient {
	
	private Client client;

	public void init() {
		client = new TransportClient().addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
	}

	public void close() {
		client.close();
	}

	/**
	 * index
	 */
	public void createIndex() {
		for (int i = 0; i < 1000; i++) {
			User user = new User();
			user.setId(new Long(i));
			user.setName("Yoho HE " + i);
			user.setAge(i % 100);
			client.prepareIndex("users", "user").setSource(generateJson(user)).execute().actionGet();
		}
	}

	/**
	 * convert to JSON object
	 *
	 * @param user
	 * @return
	 */
	private String generateJson(User user) {
		String json = "";
		try {
			XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject();
			contentBuilder.field("id", user.getId() + "");
			contentBuilder.field("name", user.getName());
			contentBuilder.field("age", user.getAge() + "");
			json = contentBuilder.endObject().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static void main(String[] args) {
		ESClient client = new ESClient();
		client.init();
		client.createIndex();
		client.close();
	}

}
