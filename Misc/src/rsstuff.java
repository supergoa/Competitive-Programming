import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class rsstuff {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
		HttpResponse response = defaultHttpClient.execute(new HttpGet("https://api.dandelion.eu/datatxt/nex/v1/?lang=en&text=The%20doctor%20says%20an%20apple%20is%20better%20than%20an%20orange&include=types%2Cabstract%2Ccategories%2Clod&token=text").);
		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");
		System.out.println(responseString);
	}
}
