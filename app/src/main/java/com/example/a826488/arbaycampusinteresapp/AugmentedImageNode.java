/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.a826488.arbaycampusinteresapp;

import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.widget.TextView;

import com.google.ar.core.AugmentedImage;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;

import java.util.concurrent.CompletableFuture;

//import com.google.ar.core.math.Vector3;
//import com.google.ar.sceneform.rendering.ModelRenderable;
//import com.google.ar.sceneform.rendering.ViewRenderable;

/**
 * Node for rendering an augmented image. The image is framed by placing the virtual picture frame
 * at the corners of the augmented image trackable.
 */
@SuppressWarnings({"AndroidApiChecker"})
public class AugmentedImageNode extends AnchorNode implements Node.OnTapListener {

  private static final String TAG = "AugmentedImageNode";
  private static final String COFO_URL = "https://www.swansea.ac.uk/science/computationalfoundry/";

    // The augmented image represented by this node.
  private AugmentedImage image;
  private Node urlNode, roomNode, facilitiesNode;

  // Models of the 4 corners.  We use completable futures here to simplify
  // the error handling and asynchronous loading.  The loading is started with the
  // first construction of an instance, and then used when the image is set.

  private static CompletableFuture<ModelRenderable> ulCorner;
  private static CompletableFuture<ModelRenderable> urCorner;
  private static CompletableFuture<ModelRenderable> lrCorner;
  private static CompletableFuture<ModelRenderable> llCorner;
    @Override
    public boolean onTouchEvent(HitTestResult hitTestResult, MotionEvent motionEvent) {
        return super.onTouchEvent(hitTestResult, motionEvent);
    }

    @Override
    public void onTap(HitTestResult hitTestResult, MotionEvent motionEvent) {

    }

    public AugmentedImageNode(Context context) {
    // Upon construction, start loading the models for the corners of the frame.
    if (ulCorner == null) {
        /*ulCorner =
          ModelRenderable.builder()
              .setSource(context, Uri.parse("models/AR_Link.sfb"))
                  .build();*/
      urCorner =
          ModelRenderable.builder()
              .setSource(context, Uri.parse("models/frame_upper_right.sfb"))
              .build();
    }

      if ( urlNode == null) {

          Vector3 localPosition = new Vector3();

          // Upper right corner.
            localPosition.set(0.05f , 0.0f, -0.05f );

          urlNode = new Node();
          urlNode.setParent(this);
          urlNode.setLocalPosition(localPosition);
          urlNode.setEnabled(false);

          Quaternion rotation1 = Quaternion.axisAngle(new Vector3(1.0f, 0.0f, 0.0f), -90); // rotate X axis 90 degrees
          //Quaternion rotation2 = Quaternion.axisAngle(new Vector3(0.0f, 0.0f, 1.0f), 90); // rotate Y axis 90 degrees
          urlNode.setWorldRotation(rotation1);
          //.setWorldRotation(rotation2);

          //.setLookDirection(Vector3.forward(), Vector3.forward());
          //.setLocalPosition(new Vector3(0.0f, planetScale * INFO_CARD_Y_POS_COEFF, 0.0f));

          urlNode.setOnTouchListener((hitTestResult, motionEvent) -> {

             /* Toast.makeText(context, "Link card tapped",
                      Toast.LENGTH_SHORT).show();

             Intent i = new Intent(Intent.ACTION_VIEW);
              i.setData(Uri.parse(COFO_URL));
              context.startActivity(i);*/
              return true;
          });
          ViewRenderable.builder()
                  .setView(context, R.layout.link_card_view)
                  .build()
                  .thenAccept(
                          (renderable) -> {
                              urlNode.setRenderable(renderable);
                              TextView textView = (TextView) renderable.getView();
                              textView.setText("Nic's project fair stand");
                              textView.setTextSize(5);

                          })
                  .exceptionally(
                          (throwable) -> {
                              throw new AssertionError("Could not load plane card view.", throwable);
                          });
    } if(facilitiesNode == null){
          //TODO:
            Vector3 localPosition = new Vector3();

            // Some corner.
            localPosition.set(-0.05f , 0.0f, 0.05f );

            facilitiesNode = new Node();
            facilitiesNode.setParent(this);
            facilitiesNode.setLocalPosition(localPosition);
            facilitiesNode.setEnabled(false);

            Quaternion rotation1 = Quaternion.axisAngle(new Vector3(1.0f, 0.0f, 0.0f), -90); // rotate X axis 90 degrees
            //Quaternion rotation2 = Quaternion.axisAngle(new Vector3(0.0f, 0.0f, 1.0f), 90); // rotate Y axis 90 degrees
            facilitiesNode.setWorldRotation(rotation1);
            //.setWorldRotation(rotation2);

            //.setLookDirection(Vector3.forward(), Vector3.forward());
            //.setLocalPosition(new Vector3(0.0f, planetScale * INFO_CARD_Y_POS_COEFF, 0.0f));

            facilitiesNode.setOnTouchListener((hitTestResult, motionEvent) -> {

             /* Toast.makeText(context, "Link card tapped",
                      Toast.LENGTH_SHORT).show();

             Intent i = new Intent(Intent.ACTION_VIEW);
              i.setData(Uri.parse(COFO_URL));
              context.startActivity(i);*/
                return true;
            });
            ViewRenderable.builder()
                    .setView(context, R.layout.facilities_card)
                    .build()
                    .thenAccept(
                            (renderable) -> {
                                facilitiesNode.setRenderable(renderable);
                                TextView textView = (TextView) renderable.getView();
                                textView.setText("You are in the Great Hall");
                                textView.setTextSize(5);

                            })
                    .exceptionally(
                            (throwable) -> {
                                throw new AssertionError("Could not load plane card view.", throwable);
                            });
        }
  }

  /**
   * Called when the AugmentedImage is detected and should be rendered. A Sceneform node tree is
   * created based on an Anchor created from the image. The corners are then positioned based on the
   * extents of the image. There is no need to worry about world coordinates since everything is
   * relative to the center of the image, which is the parent node of the corners.
   */
  @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
  public void setImage(AugmentedImage image) {
    this.image = image;

    // If any of the models are not loaded, then recurse when all are loaded.
/*    if (!ulCorner.isDone() || !urCorner.isDone() || !llCorner.isDone() || !lrCorner.isDone()) {
      CompletableFuture.allOf(ulCorner, urCorner, llCorner, lrCorner)
          .thenAccept((Void aVoid) -> setImage(image))
          .exceptionally(
              throwable -> {
                Log.e(TAG, "Exception loading", throwable);
                return null;
              });
    } */

    // Set the anchor based on the center of the image.
    setAnchor(image.createAnchor(image.getCenterPose()));

    // Make the 4 corner nodes.
    Vector3 localPosition = new Vector3();
    Node cornerNode;

    // Upper left corner.
   /* localPosition.set(-0.5f * image.getExtentX(), 0.0f, -0.5f * image.getExtentZ());
    cornerNode = new Node();
    cornerNode.setParent(this);
    cornerNode.setLocalPosition(localPosition);
//    cornerNode.setRenderable(ulCorner.getNow(null)); */

    // Upper right corner.
   /* localPosition.set(0.5f * image.getExtentX(), 0.0f, -0.5f * image.getExtentZ());
    cornerNode = new Node();
    cornerNode.setParent(this);
    cornerNode.setLocalPosition(localPosition);
    cornerNode.setRenderable(urCorner.getNow(null));*/

    /*// Lower right corner.
    localPosition.set(0.5f * image.getExtentX(), 0.0f, 0.5f * image.getExtentZ());
    cornerNode = new Node();
    cornerNode.setParent(this);
    cornerNode.setLocalPosition(localPosition);
    cornerNode.setRenderable(lrCorner.getNow(null));

    // Lower left corner.
    localPosition.set(-0.5f * image.getExtentX(), 0.0f, 0.5f * image.getExtentZ());
    cornerNode = new Node();
    cornerNode.setParent(this);
    cornerNode.setLocalPosition(localPosition);
    cornerNode.setRenderable(llCorner.getNow(null));*/
  }

  public AugmentedImage getImage() {
    return image;
  }
}
