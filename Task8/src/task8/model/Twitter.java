package task8.model;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import task8.databeans.TopicBean;
import task8.databeans.UserBean;

import com.google.gson.Gson;

public class Twitter {

	private String twitter_consumer_key = "9znAEceZGmfAgrQ06EkG9g";
	private String twitter_consumer_secret = "CjZQf67JfvvzSiXWiXNPxLdw61Ei3KNnvaGigwC1Cc";

	private static OAuthService service = null;

	private static Twitter twitter = new Twitter();

	private Twitter() {
		service = new ServiceBuilder().provider(TwitterApi.SSL.class)
				.apiKey(twitter_consumer_key)
				.apiSecret(twitter_consumer_secret)
				.callback("http://localhost:8080/Task8/twitterCallback.do")
				.build();
	}

	public static Twitter getTwitter() {
		return twitter;
	}

	public Token getRequestToken() {
		Token requestToken = service.getRequestToken();
		return requestToken;
	}

	public String getURL(Token requestToken) {
		String URL = service.getAuthorizationUrl(requestToken);
		return URL;
	}

	public Token getAccessToken(Token requestToken, Verifier verifier) {
		Token accessToken = service.getAccessToken(requestToken, verifier);
		return accessToken;
	}

	public String getTwittersByKeywords(Token accessToken, List<String> keywords)
			throws UnsupportedEncodingException {
		String count = "5";
		String resultType = "recent"; // "recent", "popular", "mixed"

		if (keywords == null || keywords.size() == 0) {
			return null;
		}

		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/search/tweets.json?q="
				+ TwitterEncoder.encode(keywords.get(0)));
		for (int i = 1; i < keywords.size(); i++) {
			query.append("%20" + TwitterEncoder.encode(keywords.get(i)));
		}
		query.append("&count=" + count);
		query.append("&result_type=" + resultType);

		OAuthRequest request = new OAuthRequest(Verb.GET, query.toString());
		service.signRequest(accessToken, request);
		Response response = request.send();

		return response.getBody();
	}

	public String getUsername(Token accessToken) {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/account/settings.json");

		OAuthRequest request = new OAuthRequest(Verb.POST, query.toString());
		service.signRequest(accessToken, request);
		Response response = request.send();

		// System.out.println(response.getBody());
		Gson gson = new Gson();
		UserBean userBean = gson.fromJson(response.getBody(), UserBean.class);
		return userBean.getScreen_name();
	}

	public void sendTwitter(Token accessToken, String text)
			throws UnsupportedEncodingException {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/statuses/update.json?status=");
		query.append(text);

		// System.out.println(query.toString());
		OAuthRequest request = new OAuthRequest(Verb.POST, query.toString());
		service.signRequest(accessToken, request);
		request.send();
	}

	public String getPopularTags(Token accessToken) {
		StringBuilder query = new StringBuilder();
		query.append("https://api.twitter.com/1.1/trends/place.json?id=1");

		OAuthRequest request = new OAuthRequest(Verb.GET, query.toString());
		service.signRequest(accessToken, request);
		Response response = request.send();

		Gson gson = new Gson();
		TopicBean[] topicBeans = gson.fromJson(response.getBody(), TopicBean[].class);
		return topicBeans[0].getTrends().get(0).getName();
	}
}