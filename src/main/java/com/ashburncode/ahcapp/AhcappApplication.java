package com.ashburncode.ahcapp;

import java.io.IOException;
import java.util.concurrent.Future;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.netty.handler.ssl.SslContext;

// import org.asynchttpclient.AsyncHttpClient;
// import org.asynchttpclient.AsyncHttpClientConfig;
// import org.asynchttpclient.DefaultAsyncHttpClientConfig;
// import org.asynchttpclient.Dsl;
// import org.asynchttpclient.Request;
// import org.asynchttpclient.Response;
// import org.asynchttpclient.RequestBuilder;
// import org.asynchttpclient.BoundRequestBuilder;
// import java.util.concurrent.*;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AhcappApplication {

  // https://www.baeldung.com/spring-boot-logging
  // -Dlogging.level.org.springframework=TRACE
  // -Dlogging.level.com.baeldung=TRACE

  static Logger log = LoggerFactory.getLogger(AhcappApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(AhcappApplication.class, args);

    // System.setProperty("javax.net.debug", "ssl:handshake");
    createClientAndMakeGetRequest();

    log.trace("A TRACE Message");
    log.debug("A DEBUG Message");
    log.info("An INFO Message");
    log.warn("A WARN Message");
    log.error("An ERROR Message");
  }

  private static void createClientAndMakeGetRequest() {
    // AsyncHttpClient client = Dsl.asyncHttpClient();
    // AsyncHttpClientConfig config = client.getConfig();
    // DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config();
    String[] protocolArray2 = { "TLSv1.2" };
    String[] protocolArray3 = { "TLSv1.3" };
    String[] protocolArray32 = { "TLSv1.3", "TLSv1.2" };
    // List<String> protocolList = Arrays.asList(protocolArray);
    String[] cipherSuiteArray1 = { "TLS_AES_256_GCM_SHA384" };
    // SslContext sslContext = null;
    DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config()
        // .setEnabledProtocols(protocolList)
        // .setEnabledProtocols(protocolArray3)
        // .setEnabledProtocols(cipherSuiteArray)
        // .setEnabledCipherSuites(cipherSuiteArray1)
        // .setSslContext(sslContext)
        // .setSslEngineFactory
        .setConnectTimeout(500);
    // .setProxyServer(new ProxyServer(...));
    AsyncHttpClient client = Dsl.asyncHttpClient(clientBuilder);
    AsyncHttpClientConfig config = client.getConfig();
    log.info("  config.getAhcVersion() = " + config.getAhcVersion());
    log.info("  config.getUserAgent() = " + config.getUserAgent());
    log.info("  config.getConnectTimeout() = " + config.getConnectTimeout());
    // log.info(" config.getEnabledCipherSuites() = " +
    // config.getEnabledCipherSuites());

    String[] enabledProtocols = config.getEnabledProtocols();
    if (enabledProtocols == null || enabledProtocols.length == 0) {
      log.info("  config.getEnabledProtocols() = " + enabledProtocols);
    } else {
      for (String enabledProtocol : enabledProtocols)
        log.info("  enabledProtocol = " + enabledProtocol);
    }
    String[] enabledCipherSuites = config.getEnabledCipherSuites();
    if (enabledCipherSuites == null || enabledCipherSuites.length == 0) {
      log.info("  config.getEnabledCipherSuites() = " + enabledCipherSuites);
    } else {
      for (String enabledCipherSuite : enabledCipherSuites)
        log.info("  enabledCipherSuite = " + enabledCipherSuite);
    }

    // BoundRequestBuilder getRequest =
    // client.prepareGet("http://www.baeldung.com");
    // Request getRequest = client.prepareGet("http://www.baeldung.com").build();
    // Request getRequest = client.prepareGet("https://www.baeldung.com").build();
    // Request getRequest = client.prepareGet("https://www.google.com").build();
    Request getRequest = client.prepareGet("https://start.spring.io/").build();

    // Request getRequest = new RequestBuilder(HttpConstants.Methods.GET)
    // .setUrl("http://www.baeldung.com")
    // .build();

    // Request getRequest = Dsl.get("http://www.baeldung.com").build();

    // Future<Response> responseFuture = getRequest.execute();
    // responseFuture.get();

    Future<Response> responseFuture = client.executeRequest(getRequest);

    try {
      Response response = responseFuture.get();
      int statusCode = response.getStatusCode();
      log.info("  statusCode = " + statusCode);
      String responseBody = response.getResponseBody();
      // System.out.println(" responseBody = " + responseBody);
      // log.info(" responseBody = " + responseBody);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
