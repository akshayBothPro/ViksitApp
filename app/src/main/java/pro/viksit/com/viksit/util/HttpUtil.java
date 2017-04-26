package pro.viksit.com.viksit.util;

import android.util.Base64;

import com.google.gson.JsonSyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Feroz on 18-04-2017.
 */

public class HttpUtil {
    private String url;
    private String type;
    private HashMap<String,String> param;
    private String postrequest;
    public HttpUtil(){}
    private int socketTimeOut=0, connectionTimeOut=0;

    private static final String ALGORITHM = "AES";
    private static final byte[] PRIVATE_KEY = "OXY IS IN ISTAR ".getBytes();

    public HttpUtil(String url, String type, HashMap<String, String> param,String postrequest) {
        this.url = url;
        this.type = type;
        this.param = param;
        this.postrequest = postrequest;
    }


    public String getStringResponse(){
        String jsonresponse="";
        try {
            System.out.println("url "+url);
            System.out.println("type "+type);

            HttpResponse httpResponse = getHttpResponse();
            if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK) {
                HttpEntity httpEntity = httpResponse.getEntity();
                jsonresponse = EntityUtils.toString(httpEntity);

                jsonresponse = decrypt(jsonresponse);// decrypting encrypted response

                if(jsonresponse.equalsIgnoreCase("[]")){
                    jsonresponse="";
                }
                System.out.println("HttpUtil Response is .... " + jsonresponse);
            } else {
                return "null";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonresponse;
    }
    public void getVoidResponse(){

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, String> getParam() {
        return param;
    }

    public void setParam(HashMap<String, String> param) {
        this.param = param;
    }

    private HttpResponse getHttpResponse(){
        HttpResponse httpResponse = null;
        HttpClient httpclient = new DefaultHttpClient();
        try{
        switch(type){
            case "GET":
                if(socketTimeOut != 0 && connectionTimeOut != 0){
                    HttpParams httpParameters = new BasicHttpParams();
                    HttpConnectionParams.setConnectionTimeout(httpParameters, connectionTimeOut);
                    HttpConnectionParams.setSoTimeout(httpParameters, socketTimeOut);
                    httpclient = new DefaultHttpClient(httpParameters);
                }
                httpResponse = httpclient.execute(new HttpGet(url));

                break;
            case "POST":
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                if(param != null) {
                    for (String key : param.keySet()) {
                        nameValuePairs.add(new BasicNameValuePair(key, param.get(key)));
                    }

                }
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                httpPost.setHeader("Authorization","" );

                httpResponse = httpclient.execute(httpPost);

                break;
            case "PUT":
                HttpPut httpPut = new HttpPut(url);
                if(postrequest != null){
                    StringEntity se = new StringEntity(postrequest);
                    se.setContentType("application/json;charset=UTF-8");//text/plain;charset=UTF-8
                    se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json;charset=UTF-8"));
                    httpPut.setEntity(se);
                    httpPut.setHeader("Accept", "application/json");
                    httpPut.setHeader("Content-type", "application/json");
                    httpPut.setHeader("Authorization","" );
                }

                httpResponse = httpclient.execute(httpPut);
                break;
            default:
                httpResponse = httpclient.execute(new HttpGet(url));
                break;
        } }catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }catch (JsonSyntaxException jse) {
            jse.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

        return httpResponse;
    }

    public String getPostrequest() {
        return postrequest;
    }

    public void setPostrequest(String postrequest) throws Exception {

        this.postrequest = encrypt(postrequest);
        //this.postrequest = postrequest;
    }

    public int getSocketTimeOut() {
        return socketTimeOut;
    }

    public void setSocketTimeOut(int socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }

    public int getConnectionTimeOut() {
        return connectionTimeOut;
    }

    public void setConnectionTimeOut(int connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    public String encrypt(String valueToEnc) throws Exception {

        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encValue = cipher.doFinal(valueToEnc.getBytes());
        byte[] encryptedBytes = Base64.encode(encValue, Base64.DEFAULT);
        String encryptedValue = new String(encryptedBytes);

        return encryptedValue;
    }

    public String decrypt(String encryptedValue) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decodedBytes = Base64.decode(encryptedValue.getBytes(), Base64.DEFAULT);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        String decryptedValue = new String(decryptedBytes);
        return decryptedValue;
    }

    private Key generateKey() throws Exception {
        Key key = new SecretKeySpec(PRIVATE_KEY, ALGORITHM);
        return key;
    }
}
