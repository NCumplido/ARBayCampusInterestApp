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

public class CoFoFloorPlansImageNode extends Node implements Node.OnTapListener {
    private final int PIXEL_TO_METER_RATIO = 2000;
    private int floorId;

    //private Bitmap image;
    private Image image;
    public boolean isLoaded = false;
    private String filepath;

    private Node imageNode;
    private ViewRenderable imageViewRenderable;
    private Context context;

    public CoFoFloorPlansImageNode(String filepath, Context context) {
        this.filepath = filepath;

        this.context = context;
        //this.image = BitmapFactory.decodeFile(filepath);

        setOnTapListener(this);
        this.setEnabled(false);
    }

    public void initialize() {

        if (filepath=="floor0"){
            initialiseCoFoFloor0();
        } else if (filepath=="floor1"){
        } else if (filepath=="floor2"){
        } else if (filepath=="floor3"){
        } else if (filepath=="floor4"){
        }

    }

    public void initialiseCoFoFloor0(){

        // Setup the ImageView and MetaData renderables
        imageNode = new Node();
        imageNode.setParent(this);
        imageNode.setEnabled(true);

        CompletableFuture<ViewRenderable> imageStage =
                ViewRenderable.builder().setView(context, R.layout.cofo_floor_0_card).build();

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
