package gameofphones.gatech.stumble;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class NetworkConnection {

    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";

    public static final String CREATE_USER = "https://thawing-cove-12717.herokuapp.com/user/create";
    public static final String FIND_USER =
            "https://thawing-cove-12717.herokuapp.com/user/findbyemail";

    public static String addECToUser(EmergencyContact ec, User user) throws IOException {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("thawing-cove-12717.herokuapp.com")
                .appendPath("user")
                .appendPath("addEmergencyContactToUser")
                .appendPath(user.getEmail());
        String body = new Gson().toJson(ec);
        Log.d("InitialEC", body);
        HashMap<String, String> params = new HashMap<>();
//        params.put("id", user.getEmail());
        String url = builder.build().toString();
        Log.d("InitialEC", url);
        URL endpoint = new URL(url);
        return connect(endpoint, PUT, body, params);
    }

    public static String connect(
            URL url, String restVerb, String body, @Nullable Map<String, String> params) throws IOException {
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            // Timeout for reading InputStream arbitrarily set to 3000ms.
            connection.setReadTimeout(3000);
            // Timeout for connection.connect() arbitrarily set to 3000ms.
            connection.setConnectTimeout(3000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod(restVerb);

            if (params != null) {
                for (String key : params.keySet()) {
                    connection.addRequestProperty(key, params.get(key));
                }
            }

            if (restVerb.equals(POST) || restVerb.equals(PUT)) {
                connection.setDoOutput(false);
                connection.setRequestProperty("Content-Type","application/json");
                OutputStream os = connection.getOutputStream();
                os.write(body.getBytes("UTF-8"));
                os.close();
            }

            if (restVerb.equals(GET)) {
                connection.setDoInput(true);
            }

            // Open communications link (network traffic occurs here).
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            stream = connection.getInputStream();
            if (stream != null) {
                // Converts Stream to String with max length of 500.
                result = readStream(stream, 500);
            }
        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }


    /**
     * Given a URL, sets up a connection and gets the HTTP response body from the server.
     * If the network request is successful, it returns the response body in String form. Otherwise,
     * it will throw an IOException.
     */
    public static String connect(URL url, String restVerb, String body) throws IOException {
        return connect(url, restVerb, body, null);
    }

    /**
     * Converts the contents of an InputStream to a String.
     */
    private static String readStream(InputStream stream, int maxReadSize)
            throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] rawBuffer = new char[maxReadSize];
        int readSize;
        StringBuffer buffer = new StringBuffer();
        while (((readSize = reader.read(rawBuffer)) != -1) && maxReadSize > 0) {
            if (readSize > maxReadSize) {
                readSize = maxReadSize;
            }
            buffer.append(rawBuffer, 0, readSize);
            maxReadSize -= readSize;
        }
        return buffer.toString();
    }
}
