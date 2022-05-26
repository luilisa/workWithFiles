package com.example.workwithfiles;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void openImage(View view) {
        final ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        EditText editText = (EditText)findViewById(R.id.editText);
        String file = editText.getText().toString();
        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().toString() + "/" + file + ".jpg");
        imageView2.setImageBitmap(bitmap);
    }

    public void saveImage(View view) {
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        Drawable drawable = getDrawable(R.drawable.kit);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        File file;
        String path = Environment.getExternalStorageDirectory().toString();

        System.out.println(Environment.getExternalStorageDirectory().getFreeSpace()/(1024*1024) + " MB available " + Environment.getExternalStorageDirectory().getTotalSpace()/(1024*1024) + " MB total");

        file = new File(path, "newKit"+".jpg");
        if (file.exists()) {
            file.delete();
        }
        try{
            OutputStream stream = null;
            stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();
            Toast.makeText(this, "Изображение сохранено", Toast.LENGTH_SHORT).show();

        }catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
