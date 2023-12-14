package com.imagenprogramada.imageviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * La aplicacion es una receptora de imágenes. Por si sola no hace nada. Se debe compartir una imagen
 * desde otra aplicació y elegir esta como receptora de la imagen.
 *
 * Al recibirse se muestra en pantalla por esta aplicación.
 *
 *
 * Las partes fundamentales del código para hacerlo son 2:
 * -Registrarse en el m
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recoger datos de accion y tipo del intent
        Intent intent = getIntent();
        String accion = intent.getAction();
        String tipo  = intent.getType();
        //si la accion y tipos son los correctos procesar la recepción de la imagen
        if (accion.equals("android.intent.action.SEND")&&tipo!=null&&tipo.startsWith("image/"))
            mostrarImagen(intent);

    }

    private void mostrarImagen(Intent intent) {
        Uri uri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        InputStream is = null;
        try {
            is = getContentResolver().openInputStream(uri);
            ImageView imageView = findViewById(R.id.imageView);
            Bitmap imagen = BitmapFactory.decodeStream(is);
            imageView.setImageBitmap(imagen);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}