package com.example.androidg2epc2.mulitipleimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ImageView UploadPositions;
    TextView positionImage, main;
    String listString = "";
    ArrayList<String> IPath = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UploadPositions = (ImageView)findViewById(R.id.upload);
        positionImage = (TextView)findViewById(R.id.noImage);

        UploadPositions.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, AlbumSelectActivity.class);
                intent.putExtra(Constants.INTENT_EXTRA_LIMIT, 50);
                startActivityForResult(intent, Constants.REQUEST_CODE);


                positionImage.setText(listString);
                Log.e("filepath", "listString==" + listString);

            }


        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == Constants.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<Image> images = data.getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
            StringBuffer stringBuffer = new StringBuffer();
            int cnt = 0;
            String selectImages = "";

            IPath.clear();
            for (int i = 0, l = images.size(); i < l; i++) {

                cnt++;
                selectImages = images.get(i).path;
                IPath.add(selectImages);
            }

            if (cnt == 0) {
                Toast.makeText( MainActivity.this,
                        "Please select at least one image",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText( MainActivity.this,
                        "You've selected Total " + cnt + " image(s).",
                        Toast.LENGTH_LONG).show();
                Log.d("SelectedImages", selectImages);

            }

            if (IPath != null) {

                Log.e("IPath", "IPath==" + IPath);

                for (int i = 0; i < IPath.size(); i++) {
                   // map.add(IPath.get(i).toString());


                    for (String s : IPath) {
                        listString += s + "\t";
                    }


                }


            } else {

            }

            positionImage.setText(listString);
            Log.e("filepath", "listString==" + listString);

        }

    }




}



