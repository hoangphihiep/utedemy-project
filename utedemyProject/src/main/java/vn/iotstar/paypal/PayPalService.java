package vn.iotstar.paypal;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class PayPalService {
    private static final String PAYPAL_API_BASE = "https://api-m.sandbox.paypal.com";
    private static final String CLIENT_ID = "AR4XYwJqBRoIzLZKn6W0uySLxWW8E1IPCCVVpObBSOh2NAsXOiCw6R2M3mNHuuFX77VtDcTFU21sjQPS";
    private static final String SECRET = "EPADlBWeT1XdowR2h43bHiHh5KgQ3TDR09-7IcabUViKY6eFD_Esm_7GahdFES7gLuiFpcJ5NTaCYz4F";

    private static final OkHttpClient client = new OkHttpClient();

    // Lấy access token từ PayPal
    private static String getAccessToken() throws IOException {
        RequestBody body = new FormBody.Builder()
                .add("grant_type", "client_credentials")
                .build();

        String credentials = Credentials.basic(CLIENT_ID, SECRET);

        Request request = new Request.Builder()
                .url(PAYPAL_API_BASE + "/v1/oauth2/token")
                .addHeader("Authorization", credentials)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
            return json.getString("access_token");
        } else {
            System.err.println("Lỗi khi lấy access token: " + response.body().string());
        }
        return null;
    }

    // Tạo đơn hàng thanh toán
    public static String createPayment(float amount, String currency, String returnURL, String cancelURL) {
        try {
            String accessToken = getAccessToken();
            if (accessToken == null) {
                System.err.println("Không thể lấy access token.");
                return null;
            }

            JSONObject payload = new JSONObject();
            payload.put("intent", "CAPTURE");
            payload.put("purchase_units", new JSONArray()
                    .put(new JSONObject()
                            .put("amount", new JSONObject()
                                    .put("currency_code", currency)
                                    .put("value", String.format(java.util.Locale.US, "%.2f", amount)))));

            payload.put("application_context", new JSONObject()
                    .put("return_url", returnURL)
                    .put("cancel_url", cancelURL));

            RequestBody body = RequestBody.create(payload.toString(), MediaType.parse("application/json"));

            Request request = new Request.Builder()
                    .url(PAYPAL_API_BASE + "/v2/checkout/orders")
                    .addHeader("Authorization", "Bearer " + accessToken)
                    .addHeader("Content-Type", "application/json")
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            System.out.println("Phản hồi từ PayPal: " + responseBody);

            if (response.isSuccessful()) {
                JSONObject jsonResponse = new JSONObject(responseBody);
                JSONArray links = jsonResponse.getJSONArray("links");
                for (int i = 0; i < links.length(); i++) {
                    JSONObject link = links.getJSONObject(i);
                    if ("approve".equals(link.getString("rel"))) {
                        return link.getString("href");
                    }
                }
            } else {
                System.err.println("Tạo đơn hàng thất bại: " + responseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // Xác nhận thanh toán sau khi người dùng nhấn "Thanh toán"
    public static boolean capturePayment(String orderID) {
        try {
            String accessToken = getAccessToken();
            if (accessToken == null) {
                System.err.println("Không thể lấy access token.");
                return false;
            }

            Request request = new Request.Builder()
                    .url(PAYPAL_API_BASE + "/v2/checkout/orders/" + orderID + "/capture")
                    .addHeader("Authorization", "Bearer " + accessToken)
                    .addHeader("Content-Type", "application/json")
                    .post(RequestBody.create("", MediaType.parse("application/json")))
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();

            if (response.isSuccessful()) {
                System.out.println("Thanh toán thành công: " + responseBody);
                return true;
            } else {
                System.err.println("Xác nhận thanh toán thất bại: " + response.code());
                System.err.println("Nội dung: " + responseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
