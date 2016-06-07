package edu.ilstu.business.era.utilities;

import java.net.URI;

import javax.annotation.Nullable;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.HttpClient;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class AuthHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

	protected HttpHost host;
	@Nullable
	protected String username;
	@Nullable
	protected String password;

	public AuthHttpComponentsClientHttpRequestFactory(HttpHost host) {
		this(host, null, null);
	}

	public AuthHttpComponentsClientHttpRequestFactory(HttpHost host, @Nullable String userName,
			@Nullable String password) {
		super();
		this.host = host;
		this.username = userName;
		this.password = password;
	}

	public AuthHttpComponentsClientHttpRequestFactory(HttpClient httpClient, HttpHost host) {
		this(httpClient, host, null, null);
	}

	public AuthHttpComponentsClientHttpRequestFactory(HttpClient httpClient, HttpHost host, @Nullable String userName,
			@Nullable String password) {
		super(httpClient);
		this.host = host;
		this.username = userName;
		this.password = password;
	}

	@Override
	protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
		AuthCache authCache = new BasicAuthCache();
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(host, basicAuth);
		HttpClientContext localcontext = HttpClientContext.create();
		localcontext.setAuthCache(authCache);
		if (username != null) {
			BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(new AuthScope(host), new UsernamePasswordCredentials(username, password));
			localcontext.setCredentialsProvider(credsProvider);
		}
		return localcontext;
	}

}