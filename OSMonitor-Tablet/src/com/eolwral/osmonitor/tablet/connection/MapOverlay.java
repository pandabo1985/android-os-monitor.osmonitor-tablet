package com.eolwral.osmonitor.tablet.connection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MapOverlay extends Overlay {
  private final GeoPoint geoPoint;
  private final Context context;
  private final int drawable;

  /**
   * @param context the context in which to display the overlay
   * @param geoPoint the geographical point where the overlay is located
   * @param drawable the ID of the desired drawable
   */
  public MapOverlay(Context context, GeoPoint geoPoint, int drawable) {
    this.context = context;
    this.geoPoint = geoPoint;
    this.drawable = drawable;
  }

  @Override
  public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {

	Point screenPoint = new Point();
    mapView.getProjection().toPixels(geoPoint, screenPoint);

    Bitmap markerImage = BitmapFactory.decodeResource(context.getResources(), drawable);

    canvas.drawBitmap(markerImage,
        screenPoint.x - markerImage.getWidth() / 2,
        screenPoint.y - markerImage.getHeight() / 2, null);
    
    return false;
  }

  @Override
  public boolean onTap(GeoPoint p, MapView mapView) {
    return false;
  }
}

