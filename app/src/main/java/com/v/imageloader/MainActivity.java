package com.v.imageloader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.nj.baijiayun.imageloader.listener.LoadListener;
import com.nj.baijiayun.imageloader.loader.ImageLoader;
import com.nj.baijiayun.imageloader.target.BitmapTarget;
import com.nj.baijiayun.imageloader.transform.RoundedCornersTransformation;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    //    public static String url = "http://neixun2.admin.zhiyun88.com/uploads/images/20190916/00ec409a2b832ac01c2a3cbe26031241.png";
    public static String url2 = "http://haodiquan.oss-cn-shanghai.aliyuncs.com/1.572577119933118E12.jpg";
    private static String cover = "";

    private ImageView imageview1, imageview2, imageview2_1, imageview2_2, imageview3, imageview3_1,
            imageview3_2, imageview4, imageview5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview1 = findViewById(R.id.imageview1);
        imageview2 = findViewById(R.id.imageview2);
        imageview2_1 = findViewById(R.id.imageview2_1);
        imageview2_2 = findViewById(R.id.imageview2_2);
        imageview3 = findViewById(R.id.imageview3);
        imageview3_1 = findViewById(R.id.imageview3_1);
        imageview3_2 = findViewById(R.id.imageview3_2);
        imageview4 = findViewById(R.id.imageview4);
        imageview5 = findViewById(R.id.imageview5);

        showImageView1();
        showImageView2();
        showImageView3();
        showImageView4();
        showImageView5();
    }

    /**
     * 模糊 圆形图
     */
    private void showImageView1() {
        ImageLoader.with(this)
                .load(url2)
                .rectRoundCorner(1)
                .openBlur()
                .asCircle()
                .placeHolder(R.mipmap.icon_placeholder)
                .into(imageview1);
    }

    /**
     * 模糊 方形图
     */
    private void showImageView2() {
        ImageLoader.with(this)
                .load(url2)
                .rectRoundCorner(1)
                .openBlur()
                .asSquare()
                .placeHolder(R.mipmap.icon_placeholder)
                .listener(new LoadListener() {
                    @Override
                    public boolean onSuccess(Object resource) {
                        Log.e("===", "onSuccess" + resource);
                        //返回为true 则不执行into中设置图片 需要自己处理
                        //返回为false 则图片交给into处理
                        return false;
                    }

                    @Override
                    public boolean onFail(Exception e) {
                        Log.e("===", "onFail" + e.getMessage());
                        return false;
                    }

                    @Override
                    public void preLoad() {
                        Log.e("===", "preLoad");
                    }
                }).into(imageview2);

        ImageLoader.with(this)
                .load(url2)
                .rectRoundCorner(1)
                .openBlur()
                .asSquare()
                .placeHolder(R.mipmap.icon_placeholder)
                .listener(new LoadListener() {
                    @Override
                    public boolean onSuccess(Object resource) {
                        //返回为true 则不执行into中设置图片 需要自己处理
                        //返回为false 则图片交给into处理
                        if (resource instanceof BitmapDrawable) {
                            imageview2_1.setImageDrawable((BitmapDrawable) resource);
                        }
                        return true;
                    }

                    @Override
                    public boolean onFail(Exception e) {
                        return false;
                    }

                    @Override
                    public void preLoad() {
                    }
                }).into(imageview2_1);

        ImageLoader.with(this)
                .load(url2)
                .rectRoundCorner(1)
                .openBlur()
                .asSquare()
                .placeHolder(R.mipmap.icon_placeholder)
                .listener(new LoadListener<BitmapDrawable>() {
                    @Override
                    public boolean onSuccess(BitmapDrawable resource) {
                        //返回为true 则不执行into中设置图片 需要自己处理
                        //返回为false 则图片交给into处理
                        if (resource != null) {
                            imageview2_2.setImageDrawable(resource);
                        }
                        return true;
                    }

                    @Override
                    public boolean onFail(Exception e) {
                        return false;
                    }

                    @Override
                    public void preLoad() {
                    }
                }).into(imageview2_2);
    }

    /**
     * 顶部圆角图
     */
    private void showImageView3() {
        ImageLoader.with(this)
                .rectRoundCorner(30)
                .setCornerType(RoundedCornersTransformation.CornerType.LEFT)
                .load(url2)
                .into(imageview3);

        ImageLoader.with(this)
                .rectRoundCorner(20)
                .setCornerType(RoundedCornersTransformation.CornerType.TOP)
                .load(url2)
                .into(imageview3_1);

        ImageLoader.with(this)
                .rectRoundCorner(30)
                .setCornerType(RoundedCornersTransformation.CornerType.RIGHT)
                .load(url2)
                .into(imageview3_2);
    }

    private void showImageView4() {
        ImageLoader.with(this)
                .asBitmap()
                .load(url2.replace("http://", "https://"))
                .error(R.mipmap.public_ic_image_error)
                .into(new BitmapTarget() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Log.e("TAG", "onResourceReady" + resource);
                        imageview4.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        Log.e("TAG", "onResourceReady start" + placeholder);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                        Log.e("TAG", "onResourceReady fail" + errorDrawable);
                    }
                });
    }

    public void showImageView5() {
        Glide.with(this)
                .asBitmap()
                .load(url2.replace("http://", "https://"))
                .error(R.mipmap.public_ic_image_error)
                .addListener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object o, Target<Bitmap> target, boolean b) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap bitmap, Object o, Target<Bitmap> target, DataSource dataSource, boolean b) {
                        return false;
                    }
                })
                .into(imageview5);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Cache0" + this.getExternalCacheDir());
        System.out.println("Cache1" + this.getCacheDir());
    }

    public void open(View view) {
        Glide.with(this).resumeRequestsRecursive();
//        ImageLoader.getActualLoader().resumeRequests(this);
    }

    public void next(View view) {
        //startActivity(new Intent(this, SecondActivity.class));
        showImageView3();
    }
}