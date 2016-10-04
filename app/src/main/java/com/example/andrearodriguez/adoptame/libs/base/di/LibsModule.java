package com.example.andrearodriguez.adoptame.libs.base.di;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cloudinary.Cloudinary;
import com.cloudinary.android.Utils;
import com.example.andrearodriguez.adoptame.libs.ClaudinaryImageStorage;
import com.example.andrearodriguez.adoptame.libs.GlideImageLoader;
import com.example.andrearodriguez.adoptame.libs.GreenRobotEventBus;
import com.example.andrearodriguez.adoptame.libs.base.EventBus;
import com.example.andrearodriguez.adoptame.libs.base.ImageLoader;
import com.example.andrearodriguez.adoptame.libs.base.ImageStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 7/24/16.
 */
@Module
public class LibsModule {
    private Activity activity;


    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus ){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }


    @Provides
    @Singleton
    RequestManager providesRequestManager(Activity activity){
        return Glide.with(activity);
    }

    @Provides
    @Singleton
    Activity providesActivity(){
        return  this.activity;
    }
    @Provides
    @Singleton
    ImageStorage providesImageStorage(Cloudinary cloudinary){
        return new ClaudinaryImageStorage(cloudinary);
    }
    @Provides
    @Singleton
    Cloudinary providesCloudinary (Context context){
        return new Cloudinary(Utils.cloudinaryUrlFromContext(context));
    }
}
