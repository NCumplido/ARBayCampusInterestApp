package com.example.a826488.arbaycampusinteresapp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.ux.ArFragment;

public class CofoPlansArActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.1;

    private ArFragment arFragment;
    CoFoFloorZeroImageNode imageNode;

    private FrameLayout layout;

    private String floorIdentifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cofo_plans_ar);

        // Get references
        setContentView(R.layout.activity_ar);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
        floorIdentifier = getIntent().getStringExtra("floorIdentifier");
        layout = findViewById(R.id.ar_layout);

        // Create the ImageNode
        if(floorIdentifier == "floor0"){
            imageNode = new CoFoFloorZeroImageNode(CofoPlansArActivity.this);
        }
        imageNode.initialize();

        // Setup a tap listener
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            if(imageNode.isLoaded) {
                // Create an anchor
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());

                // Assign the ImageNode to the anchor, enable
                imageNode.setParent(anchorNode);
                imageNode.setLocalPosition(new Vector3(0, (float) .4, 0));
                imageNode.setEnabled(true);
            }
        });

        // Display a snackbar to tell the user how to get started
        Snackbar snackbar =
                Snackbar.make(layout, "Tap to place an image on a discovered surface.", 5000);
        snackbar.show();
    }
}
