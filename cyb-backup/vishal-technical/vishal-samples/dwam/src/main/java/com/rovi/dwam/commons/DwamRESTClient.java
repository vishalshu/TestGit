package com.rovi.dwam.commons;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rovi.dwam.exception.DwamException;
import com.rovi.dwam.log.DWAMLogger;
import com.rovi.dwam.ws.DWAMWebServices;
import com.rovi.dwam.ws.ServiceType;
import com.rovi.dwam.ws.WSClientUtil;
import com.rovi.dwam.ws.WebServiceResource;

/**
 * DivX (O)pen (V)ideo (S)ystem
 * 
 * @author Rovi Media Solutions Engineering: bhaveshsh Created: Jun 12, 2013
 *         Copyright RoviCorp 2013
 * 
 *         Developer Comment(s):
 */
public class DwamRESTClient implements InitializingBean, IDwamRESTClient
{

	@Autowired
	DefaultHttpClient httpClient;

	@Autowired
	private RestTemplate restTemplate;

	private Properties webServiceProperties;

	private static DWAMLogger logger = DWAMLogger.getLogger(DwamRESTClient.class);

	@Autowired
	private WebServiceResource webServiceResource;

	public <R> R postForObject(Class<? extends R> responseClass, Map<String, String> headerParams, Object entity, DWAMWebServices services, ServiceType serviceType) throws DwamException
	{
		// Prepare web service URL
		String wsUrl = WSClientUtil.prepareWebServiceClientURL(webServiceProperties, services, serviceType);
		HttpEntity<?> httpEntity = prepareHttpEntity(headerParams, entity);

		logger.info("Executing POST request to: {}", wsUrl);
		R response = restTemplate.postForObject(wsUrl, httpEntity, responseClass);
		return response;
	}

	public URI postForLocation(Map<String, String> headerParams, Object entity, DWAMWebServices services, ServiceType serviceType) throws DwamException
	{
		// Prepare web service URL
		String wsUrl = WSClientUtil.prepareWebServiceClientURL(webServiceProperties, services, serviceType);
		HttpEntity<?> httpEntity = prepareHttpEntity(headerParams, entity);

		logger.info("Executing POST request to: {}", wsUrl);
		URI response = restTemplate.postForLocation(wsUrl, httpEntity);
		return response;
	}

	public String exchange(DWAMWebServices service, HttpMethod httpMethod, Map<String, String> headerParams, ServiceType serviceType, String... queryParams) throws DwamException
	{
		// Prepare web service URL
		String wsUrl = WSClientUtil.prepareWebServiceClientURL(webServiceProperties, service, serviceType, queryParams);
		logger.info("Executing GET request to: {}", wsUrl);
		HttpEntity<?> httpEntity = prepareHttpEntity(headerParams, null, queryParams);
		ResponseEntity<String> result = restTemplate.exchange(wsUrl, httpMethod, httpEntity, String.class);

		return result.getBody();
	}

	@SuppressWarnings("unchecked")
	public <R> R exchange(DWAMWebServices service, HttpMethod httpMethod, Class<? extends R> responseClass, Map<String, String> headerParams, ServiceType serviceType, String... queryParams) throws DwamException
	{
		// Prepare web service URL
		String wsUrl = WSClientUtil.prepareWebServiceClientURL(webServiceProperties, service, serviceType, queryParams);
		logger.info("Executing GET request to: {}", wsUrl);
		HttpEntity<?> httpEntity = prepareHttpEntity(headerParams, null, queryParams);
		ResponseEntity<R> result = (ResponseEntity<R>) restTemplate.exchange(wsUrl, httpMethod, httpEntity, responseClass);
		return result.getBody();
	}

	private HttpEntity<?> prepareHttpEntity(Map<String, String> headerParams, Object entity, String... queryParams) throws DwamException
	{
		// Set the Media Type Default.
		List<MediaType> mediaTypeList = new ArrayList<MediaType>();
		mediaTypeList.add(WSClientUtil.MEDIA_TYPE_DEFAULT);

		// Prepare HTTP Headers for call.
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(WSClientUtil.MEDIA_TYPE_DEFAULT);
		httpHeaders.setAccept(mediaTypeList);

		if (headerParams != null)
		{
			Set<String> keys = headerParams.keySet();
			for (String key : keys)
			{
				httpHeaders.add(key, headerParams.get(key));
			}
		}

		HttpEntity<Object> httpEntity = null;
		if (entity != null)
		{
			httpEntity = new HttpEntity<Object>(entity, httpHeaders);
		}
		else
		{
			httpEntity = new HttpEntity<Object>(httpHeaders);
		}

		return httpEntity;
	}

	public RestTemplate getRestTemplate()
	{
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}

	/**
	 * To enable SSL in HttpClient supplied to RESTTemplate, This will be called
	 * by spring container after initialization of this.
	 * 
	 * @throws Exception
	 */
	public void enableSSL() throws Exception
	{

		// Load stored SSL certificate and add to accepted issuers
		InputStream is = webServiceResource.getDwamCertificate().getInputStream();

		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		X509Certificate cert = (X509Certificate) cf.generateCertificate(is);

		// Need work on utilizing OVS cert here. It works for any certificate
		// right now.
		final X509Certificate[] _AcceptedIssuers = new X509Certificate[] {cert};
		try
		{
			SSLContext ctx = SSLContext.getInstance("SSL");
			X509TrustManager tm = new X509TrustManager()
			{
				@Override
				public X509Certificate[] getAcceptedIssuers()
				{
					return _AcceptedIssuers;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException
				{
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException
				{
				}
			};
			ctx.init(null, new TrustManager[] {tm}, new SecureRandom());
			SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = httpClient.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			sr.register(new Scheme("https", 443, ssf));
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public void afterPropertiesSet() throws Exception
	{
		try
		{
			webServiceProperties = PropertiesLoaderUtils.loadProperties(webServiceResource.getWebServiceConfig());

			// Add DWAM properties to configure DVS service endpoint(s)
			Properties dwamProperties = PropertiesLoaderUtils.loadProperties(webServiceResource.getDwamConfig());
			webServiceProperties.putAll(dwamProperties);
		}
		catch (IOException e)
		{
			throw new DwamException(e);
		}
	}

	public WebServiceResource getWebServiceResource()
	{
		return webServiceResource;
	}

	public void setWebServiceResource(WebServiceResource webServiceResource)
	{
		this.webServiceResource = webServiceResource;
	}

	public void put(DWAMWebServices service, Map<String, String> headerParams, ServiceType serviceType, Object entity) throws DwamException
	{
		String wsUrl = WSClientUtil.prepareWebServiceClientURL(webServiceProperties, service, serviceType);
		HttpEntity<?> httpEntity = prepareHttpEntity(headerParams, entity);
		restTemplate.put(wsUrl, httpEntity);

	}

}
