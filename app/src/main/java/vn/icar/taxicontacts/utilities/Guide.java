package vn.icar.taxicontacts.utilities;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Guide {
    private String lat = "", lon = "";
    private Document doc;
    private Context mContext;
    private String destination = "";
    private String TAG = Guide.class.getSimpleName();

    public void processAPI(final Context mContext, String url, final int cate, String destination) {
        this.mContext = mContext;
        if (destination.contains("quán ăn")) {
            this.destination = destination;
        } else {
            this.destination = destination.replaceAll("ăn ", "quán ");
        }
        new GetUrl().execute();
    }

    private class GetUrl extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                doc = Jsoup.connect("https://www.google.com/maps/search/" + destination).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; vi-VN; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get();
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (doc != null) {
                String regex = ",\\d{1,3}.\\d{6,20},\\d{2,3}.\\d{6,20}\\],\\\\";
                Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
                Matcher matcher = pattern.matcher(doc.getAllElements().toString());
                if (matcher.find()) {
                    regex = "\\d{1,3}.\\d+";
                    Pattern pattern2 = Pattern.compile(regex, Pattern.MULTILINE);
                    Matcher matcher2 = pattern2.matcher(matcher.group(0));
                    int count = 0;
                    while (matcher2.find()) {
                        int finalCount = count;
                        if (finalCount == 0) lat = matcher2.group(0);
                        else lon = matcher2.group(0);
                        count++;
                    }

                    String regex3 = ",\\d{1,3}.\\d{6,20},\\d{2,3}.\\d{6,20}\\],\\\\.{200}";
                    Pattern pattern3 = Pattern.compile(regex3, Pattern.MULTILINE);
                    Matcher matcher3 = pattern3.matcher(doc.getAllElements().toString());
                    if (matcher3.find()) {
                        Log.e("destination", matcher3.group());
                        String regex4 = "\\\"[ếÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\w,.-]{6,100}\\\\";
                        Pattern pattern4 = Pattern.compile(regex4, Pattern.MULTILINE);
                        Matcher matcher4 = pattern4.matcher(matcher3.group());
                        if (matcher4.find()) {
                            destination = matcher4.group().substring(1, matcher4.group().length() - 1);
                            Log.e("destination", destination);
                        }
                    }
                    executeDirect();
                }
            } else {
                Log.e("destination", "failure");
                ((Activity) mContext).finish();
            }
        }
    }


    private void executeDirect() {
        String regex = "[\\s.\\sa-zA-Z0-9_ếÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(destination);
        if (matcher.find()) {
            destination = matcher.group(0);
        }
        Toast.makeText(mContext, "Đang dẫn đường đến " + destination,Toast.LENGTH_SHORT).show();
        Log.e("destination", lat + "," + lon);
    }

}

