package com.example.a826488.arbaycampusinteresapp;

import android.content.Context;
import android.media.Image;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.rendering.ViewRenderable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//This ImageNode is for STUDY SPACES
public class ImageNode extends Node implements Node.OnTapListener {
    private final int PIXEL_TO_METER_RATIO = 2000;

    private Image image;
    public boolean isLoaded = false;

    private Node imageNode;
    private ViewRenderable imageViewRenderable;
    private Context context;

    public ImageNode( Context context) {
        this.context = context;
        setOnTapListener(this);
        this.setEnabled(false);
    }

    public void initialize() {
        // Setup the ImageView and MetaData renderables
        imageNode = new Node();
        imageNode.setParent(this);
        imageNode.setEnabled(true);

        CompletableFuture<ViewRenderable> imageStage =
                ViewRenderable.builder().setView(context, R.layout.widget_image).build();

        CompletableFuture.allOf(imageStage)
                .handle((notUsed, throwable) -> {
                    if (throwable != null) {
                        throwable.printStackTrace();
                        Toast.makeText(context, "Unable to load renderable", Toast.LENGTH_SHORT).show();
                        return null;
                    }

                    try {
                        // Setup the image node
                        imageNode.setRenderable(imageStage.get());
                        imageViewRenderable = (ViewRenderable) imageNode.getRenderable();
                        //imageViewRenderable.setPixelsToMetersRatio(PIXEL_TO_METER_RATIO);
                        ImageView imageView = (ImageView) imageViewRenderable.getView();
                        //imageView.setImageResource();

                        isLoaded = true;

                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show();
                    }

                    return null;
                });
    }

    @Override
    public void onActivate() {
        if (getScene() == null) {
            throw new IllegalStateException("Scene is null!");
        }
    }

    @Override
    public void onTap(HitTestResult hitTestResult, MotionEvent motionEvent) {
    }
}