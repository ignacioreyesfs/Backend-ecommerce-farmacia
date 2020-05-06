package Utilities.APIUtility;

import Utilities.APIUtility.Exceptions.*;
import okhttp3.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class APIUtility {
    /**
     * Send a get request to a certain server
     * @param requestURL: the URL of the remote server
     * @param headers:    headers to be set
     * @return Response okhttp3 class after sending the request to server
     * @throws IOException
     */
    public Response sendGetRequest(String requestURL, Map<String, String> headers) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builderRequest = new Request.Builder().url(requestURL);
        this.setHeaders(headers, builderRequest);
        Request request = builderRequest.build();

        Response response = client.newCall(request).execute();

        return response;
    }

    /**
     * Send a post request to a certain server
     * @param requestURL: the URL of the remote server
     * @param headers:    headers to be set
     * @param params:     params needed in the request
     * @return Response okhttp3 class after sending the request to server
     * @throws IOException
     */

    public Response sendPostRequest(String requestURL, Map<String, String> params,
                                          Map<String, String> headers) throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = this.makeRequestBody(params);
        Request.Builder builderRequest = new Request.Builder().url(requestURL);
        this.setHeaders(headers, builderRequest);
        Request request = builderRequest.post(formBody).build();

        Response response = client.newCall(request).execute();

        return response;
    }

    /**
     * Set the headers of the request
     * @param headers: headers to be set
     * @param builder: Request Builder with the url setted
     */

    private void setHeaders(Map<String, String> headers, Request.Builder builder){
        if(headers != null && headers.size() > 0){
            Iterator<String> headerIterator = headers.keySet().iterator();
            while (headerIterator.hasNext()) {
                String key = headerIterator.next();
                String value = headers.get(key);
                builder.addHeader(key, value);
            }
        }
    }

    /**
     * Make the request body adding the params
     * @param params: params needed in the request
     * @return the request body created
     */

    private RequestBody makeRequestBody(Map<String, String> params){

        if(params == null || params.size() == 0)
            return RequestBody.create("", null);

        FormBody.Builder builder = new FormBody.Builder();
        Iterator<String> paramIterator = params.keySet().iterator();
        while(paramIterator.hasNext()){
            String key = paramIterator.next();
            String value = params.get(key);
            builder.add(key, value);
        }

        return builder.build();
    }

    // BOTH OPTIONS METHODS
    /**
     * @return a JsonObject with the content of the body
     * @throws IOException
     */
    public JSONObject getJsonObjectResponse(String stringResponse) {

        JSONParser parse = new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) parse.parse(stringResponse);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new ParseJsonException();
        }

        return jsonObject;
    }

}
