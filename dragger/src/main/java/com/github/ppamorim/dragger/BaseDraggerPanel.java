package com.github.ppamorim.dragger;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class BaseDraggerPanel extends FrameLayout {

  private static final float DEFAULT_DRAG_LIMIT = 0.5f;
  private static final int DEFAULT_DRAG_POSITION = DraggerPosition.TOP.ordinal();

  public TypedArray attributes;
  public float draggerLimit;
  public int draggerPosition;

  public FrameLayout dragView;
  public FrameLayout shadowView;

  public BaseDraggerPanel(Context context) {
    super(context);
  }

  public BaseDraggerPanel(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public BaseDraggerPanel(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  /**
   * Apply all the custom view configuration and inflate the main view. The view won't be
   * visible if this method is not called.
   */
  public void initializeView(int layoutId) {
    inflate(getContext(), layoutId, this);
    dragView = (FrameLayout) findViewById(R.id.drag_view);
    shadowView = (FrameLayout) findViewById(R.id.shadow_view);
  }

  public void addViewOnShadow(View view) {
    eraseViewIfNeeded(shadowView);
    shadowView.addView(view);
  }

  public void addViewOnDrag(View view) {
    eraseViewIfNeeded(dragView);
    dragView.addView(view);
  }

  public void eraseViewIfNeeded(FrameLayout frameLayout) {
    if (frameLayout.getChildCount() > 0) {
      frameLayout.removeAllViews();
    }
  }

  public void initializeAttributes(AttributeSet attrs) {
    attributes = getContext().obtainStyledAttributes(attrs, R.styleable.dragger_layout);
    if (attributes != null) {
      draggerLimit = attributes.getFloat(R.styleable.dragger_layout_drag_limit, DEFAULT_DRAG_LIMIT);
      draggerPosition =
          attributes.getInt(R.styleable.dragger_layout_drag_position, DEFAULT_DRAG_POSITION);
    }
  }

}
