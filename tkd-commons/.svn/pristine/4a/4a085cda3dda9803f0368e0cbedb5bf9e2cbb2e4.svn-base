package com.lc.zy.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class PostRequest {

    public static String postText(String url, String... args) throws Exception {
        HttpClient httpClient = new HttpClient();
        PostMethod post = new PostMethod(url);
        post.getParams().setContentCharset("UTF-8");
        if (args != null)
            for (int i = 0; i < args.length; i += 2) {
                String k = args[i];
                String v = args[i + 1];
                NameValuePair nvp = new NameValuePair();
                nvp.setName(k);
                nvp.setValue(v);
                post.addParameter(nvp);
            }
        httpClient.executeMethod(post);
        return post.getResponseBodyAsString();
    }

    public String https(final URL validationUrl) {
        HttpURLConnection connection = null;
        try {
            trustAllHttpsCertificates();
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
            connection = (HttpURLConnection) validationUrl.openConnection();
            final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            final StringBuffer stringBuffer = new StringBuffer(255);

            synchronized (stringBuffer) {
                while ((line = in.readLine()) != null) {
                    stringBuffer.append(line);
                    stringBuffer.append("\n");
                }
                return stringBuffer.toString();
            }

        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        } catch (final Exception e1) {
            e1.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                    + session.getPeerHost());
            return true;
        }
    };

    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }

    static class miTM implements javax.net.ssl.TrustManager,
            javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }
}