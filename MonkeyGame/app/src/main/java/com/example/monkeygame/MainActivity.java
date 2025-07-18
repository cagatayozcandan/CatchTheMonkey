package com.example.monkeygame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textScore;
    TextView textTime;
    int score;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    
    ImageView[] imageArray;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        score =0;
         textScore =findViewById(R.id.textScore);
         textTime =findViewById(R.id.textTime);
         imageView=findViewById(R.id.imageView);
         imageView1=findViewById(R.id.imageView1);
         imageView2=findViewById(R.id.imageView2);
         imageView3=findViewById(R.id.imageView3);
         imageView4=findViewById(R.id.imageView4);
         imageView5=findViewById(R.id.imageView5);
         imageView6=findViewById(R.id.imageView6);
         imageView7=findViewById(R.id.imageView7);
         imageView8=findViewById(R.id.imageView8);
         imageArray=new ImageView[]{imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};

         new CountDownTimer(10000,1000){

             @Override
             public void onTick(long millisUntilFinished) {
            textTime.setText("Left :"+millisUntilFinished/1000);
             }

             @Override
             public void onFinish() {
textTime.setText("Time's Off");
handler.removeCallbacks(runnable);
                 for(ImageView images:imageArray){
                     images.setVisibility(View.INVISIBLE);
                 }

                 AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
    alert.setTitle("Restart?");
    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

          //Uygulamayı tekrar başlatmak için
        Intent intent=getIntent();
        finish(); //Uygulamayı tekrar başlatmadan bi bitirsin ki zorlanmasın uygulama
        startActivity(intent);
    }
});
    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT).show();


    }
});
   alert.show();

             }
         }.start();

        hideImages();
    }

    public void increaseScore(View View){
        score++;
        textScore.setText("Score : "+score);
    }
public void hideImages(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView images:imageArray){
                    images.setVisibility(View.INVISIBLE);
                }
                Random random =new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable,400);
            }
        };
        handler.post(runnable);

}

}