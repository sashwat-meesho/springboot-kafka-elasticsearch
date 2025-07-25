package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

@Configuration
@Profile("!test")  // Don't load this configuration during tests
public class ElasticsearchConfig extends ElasticsearchConfiguration {

    @Value("${spring.elasticsearch.username}")
    private String username;

    @Value("${spring.elasticsearch.password}")
    private String password;

    @Override
    public ClientConfiguration clientConfiguration() {
        try {
            // Create a trust manager that trusts all certificates
            TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                }
            };

            // Create SSL context with the trust-all manager
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            return ClientConfiguration.builder()
                    .connectedTo("localhost:9200")
                    .usingSsl(sslContext)
                    .withBasicAuth(username, password)
                    .withSocketTimeout(30000)
                    .withConnectTimeout(20000)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to configure Elasticsearch SSL", e);
        }
    }
} 