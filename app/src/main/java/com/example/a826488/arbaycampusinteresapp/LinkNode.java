package com.example.a826488.arbaycampusinteresapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.widget.TextView;

import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;

/**
 * Node that represents a planet.
 *
 * <p>The planet creates two child nodes when it is activated:
 *
 * <ul>
 *   <li>The visual of the planet, rotates along it's own axis and renders the planet.
 *   <li>An info card, renders an Android View that displays the name of the planerendt. This can be
 *       toggled on and off.
 * </ul>
 *
 * The planet is rendered by a child instead of this node so that the spinning of the planet doesn't
 * make the info card spin as well.
 */
public class LinkNode extends Node implements Node.OnTapListener {
    private final String planetName;
    private final float planetScale;
    private final float orbitDegreesPerSecond;
    private final float axisTilt;
    private final ModelRenderable planetRenderable;
    private Node infoCard;
    private final Context context;
    private Node m_parentNode;

    public LinkNode(
            Context context,
            String planetName,
            float planetScale,
            float orbitDegreesPerSecond,
            float axisTilt,
            ModelRenderable planetRenderable) {
        this.context = context;
        this.planetName = planetName;
        this.planetScale = planetScale;
        this.orbitDegreesPerSecond = orbitDegreesPerSecond;
        this.axisTilt = axisTilt;
        this.planetRenderable = planetRenderable;
        setOnTapListener(this);

        m_parentNode = new Node();
        //m_parentNode.setOnTouchListener();
    }

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    public void onActivate() {

        if (getScene() == null) {
            throw new IllegalStateException("Scene is null!");
        }

        if (infoCard == null) {
            infoCard = new Node();
            infoCard.setParent(this);
            infoCard.setEnabled(false);
            //infoCard.setLocalPosition(new Vector3(0.0f, planetScale * INFO_CARD_Y_POS_COEFF, 0.0f));

            ViewRenderable.builder()
                    .setView(context, R.layout.link_card_view)
                    .build()
                    .thenAccept(
                            (renderable) -> {
                                infoCard.setRenderable(renderable);
                                TextView textView = (TextView) renderable.getView();
                                textView.setText("Link node tapped!");
                            })
                    .exceptionally(
                            (throwable) -> {
                                throw new AssertionError("Could not load plane card view.", throwable);
                            });
        }

    }

    @Override
    public void onUpdate(FrameTime frameTime) {
        if (infoCard == null) {
            return;
        }

        // Typically, getScene() will never return null because onUpdate() is only called when the node
        // is in the scene.
        // However, if onUpdate is called explicitly or if the node is removed from the scene on a
        // different thread during onUpdate, then getScene may be null.
        if (getScene() == null) {
            return;
        }
    }

    @Override
    public void onTap(HitTestResult hitTestResult, MotionEvent motionEvent) {

    }


}

