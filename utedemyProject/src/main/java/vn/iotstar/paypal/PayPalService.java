package vn.iotstar.paypal;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PayPalService {
    private static final String PAYPAL_API_BASE = "https://api-m.sandbox.paypal.com"; // Sửa lại đường dẫn API PayPal Sandbox
    private static final String CLIENT_ID = "AR4XYwJqBRoIzLZKn6W0uySLxWW8E1IPCCVVpObBSOh2NAsXOiCw6R2M3mNHuuFX77VtDcTFU21sjQPS";
    private static final String SECRET = "EPADlBWeT1XdowR2h43bHiHh5KgQ3TDR09-7IcabUViKY6eFD_Esm_7GahdFES7gLuiFpcJ5NTaCYz4F";

    // Tạo thanh toán PayPal
    public static String createPayment(float amount, String currency, String returnURL, String cancelURL) {
        try {
            OkHttpClient client = new OkHttpClient();

            JSONObject payload = new JSONObject();
            payload.put("intent", "CAPTURE");
            payload.put("purchase_units", new JSONArray()
                .put(new JSONObject()
                    .put("amount", new JSONObject()
                        .put("currency_code", currency)
                        .put("value", String.format("%.2f", amount)))));

            payload.put("application_context", new JSONObject()
                .put("return_url", returnURL)
                .put("cancel_url", cancelURL));

            RequestBody body = RequestBody.create(payload.toString(), MediaType.parse("application/json"));

            // Gửi yêu cầu API
            String credentials = Credentials.basic(CLIENT_ID, SECRET);
            Request request = new Request.Builder()
                .url(PAYPAL_API_BASE + "/v2/checkout/orders")
                .addHeader("Authorization", credentials)
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                JSONArray links = jsonResponse.getJSONArray("links");
                for (int i = 0; i < links.length(); i++) {
                    JSONObject link = links.getJSONObject(i);
                    if ("approve".equals(link.getString("rel"))) {
                        return link.getString("href"); // Trả về URL để người dùng thanh toán
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean capturePayment(String orderID) {
        try {
            OkHttpClient client = new OkHttpClient();

            String credentials = Credentials.basic(CLIENT_ID, SECRET);
            Request request = new Request.Builder()
                .url(PAYPAL_API_BASE + "/v2/checkout/orders/" + orderID + "/capture")
                .addHeader("Authorization", credentials)
                .addHeader("Content-Type", "application/json") // Thêm Content-Type đúng
                .post(RequestBody.create("", MediaType.parse("application/json"))) // Payload rỗng
                .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                return true;
            } else {
                String errorResponse = response.body().string();
                System.err.println("Capture Payment Failed:");
                System.err.println("Response Code: " + response.code());
                System.err.println("Response Body: " + errorResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}

