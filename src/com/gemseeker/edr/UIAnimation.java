package com.gemseeker.edr;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 *
 * @author Gem Seeker
 */
public class UIAnimation {
    
    /**
     * Translate the Node horizontally. The <tt>double value</tt> determines the
     * direction of the translate animation. If it is set to a negative value,
     * the Node is translated to left, and positive value will translate the
     * Node to the right.
     * @param node
     * @param value
     * @param durationInMillis
     * @param autoReverse 
     */
    public static void translateX(Node node, double value, long durationInMillis, boolean autoReverse){
        Timeline timeline = new Timeline();
        if(autoReverse){
            timeline.setCycleCount(2);
        }else{
            timeline.setCycleCount(1);
        }
        timeline.setAutoReverse(autoReverse);
        
        KeyValue keyValue = new KeyValue(node.translateXProperty(), value);
        KeyFrame keyFrame = new KeyFrame(new Duration(durationInMillis), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    
    /**
     * Translate the Node horizontally. This method requires two double values
     * that specifies the starting and destination values.
     * @param node
     * @param from The value of which the Node will be translated from.
     * @param to The value of which the Node will be translated to.
     * @param durationInMillis
     * @param autoReverse 
     */
    public static void translateX(Node node, double from, double to, long durationInMillis, boolean autoReverse){
        DoubleProperty translate = node.translateXProperty();
        
        KeyValue keyValue1 = new KeyValue(translate, from);
        KeyValue keyValue2 = new KeyValue(translate, to);
        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
        KeyFrame keyFrame2 = new KeyFrame(new Duration(durationInMillis), keyValue2);
        
        Timeline timeline = new Timeline(keyFrame1, keyFrame2);
        if(autoReverse){
            timeline.setCycleCount(2);
        }else{
            timeline.setCycleCount(1);
        }
        timeline.setAutoReverse(autoReverse);
        timeline.play();
    }
    
    /**
     * Translate the Node vertically. The <tt>double value</tt> determines the
     * direction of the translate animation. If it is set to a negative value,
     * the Node is translated downwards, and positive value will translate the
     * Node upwards.
     * @param node
     * @param value
     * @param durationInMillis
     * @param autoReverse 
     */
    public static void translateY(Node node, double value, long durationInMillis, boolean autoReverse){
        Timeline timeline = new Timeline();
        if(autoReverse){
            timeline.setCycleCount(2);
        }else{
            timeline.setCycleCount(1);
        }
        timeline.setAutoReverse(autoReverse);
        
        KeyValue keyValue = new KeyValue(node.translateYProperty(), value);
        KeyFrame keyFrame = new KeyFrame(new Duration(durationInMillis), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    
    /**
     * Translate the Node vertically. This method requires two double values
     * that specifies the starting and destination values.
     * @param node
     * @param from The value of which the Node will be translated from.
     * @param to The value of which the Node will be translated to.
     * @param durationInMillis
     * @param autoReverse 
     */
    public static void translateY(Node node, double from, double to, long durationInMillis, boolean autoReverse){
        DoubleProperty translate = node.translateYProperty();
        
        KeyValue keyValue1 = new KeyValue(translate, from);
        KeyValue keyValue2 = new KeyValue(translate, to);
        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
        KeyFrame keyFrame2 = new KeyFrame(new Duration(durationInMillis), keyValue2);
        
        Timeline timeline = new Timeline(keyFrame1, keyFrame2);
        if(autoReverse){
            timeline.setCycleCount(2);
        }else{
            timeline.setCycleCount(1);
        }
        timeline.setAutoReverse(autoReverse);
        timeline.play();
    }
    
    /**
     * Stretch the Node horizontally. Setting the <tt>double value</tt> to positive
     * will expand the Node horizontally, and negative value will shrink the Node
     * horizontally.
     * @param node
     * @param value
     * @param durationInMillis
     * @param autoReverse 
     */
    public static void scaleX(Node node, double value, long durationInMillis, boolean autoReverse){
        Timeline timeline = new Timeline();
        if(autoReverse){
            timeline.setCycleCount(2);
        }else{
            timeline.setCycleCount(1);
        }
        timeline.setAutoReverse(autoReverse);
        
        KeyValue keyValue = new KeyValue(node.scaleXProperty(), value);
        KeyFrame keyFrame = new KeyFrame(new Duration(durationInMillis), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    
    public static void scaleX(Node node, double from, double to, long durationInMillis, boolean autoReverse){
        DoubleProperty translate = node.scaleXProperty();
        
        KeyValue keyValue1 = new KeyValue(translate, from);
        KeyValue keyValue2 = new KeyValue(node.translateXProperty(), to);
        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
        KeyFrame keyFrame2 = new KeyFrame(new Duration(durationInMillis), keyValue2);
        
        Timeline timeline = new Timeline(keyFrame1, keyFrame2);
        if(autoReverse){
            timeline.setCycleCount(2);
        }else{
            timeline.setCycleCount(1);
        }
        timeline.setAutoReverse(autoReverse);
        timeline.play();
    }
    
    /**
     * Stretch the Node vertically. Setting the <tt>double value</tt> to positive
     * will expand the Node vertically, and negative value will shrink the Node
     * vertically.
     * @param node
     * @param value
     * @param durationInMillis
     * @param autoReverse 
     */
    public static void scaleY(Node node, double value, long durationInMillis, boolean autoReverse){
        Timeline timeline = new Timeline();
        if(autoReverse){
            timeline.setCycleCount(2);
        }else{
            timeline.setCycleCount(1);
        }
        timeline.setAutoReverse(autoReverse);
        
        KeyValue keyValue = new KeyValue(node.scaleYProperty(), value);
        KeyFrame keyFrame = new KeyFrame(new Duration(durationInMillis), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    
    public static void scaleY(Node node, double from, double to, long durationInMillis, boolean autoReverse){
        DoubleProperty translate = node.scaleYProperty();
        
        KeyValue keyValue1 = new KeyValue(translate, from);
        KeyValue keyValue2 = new KeyValue(node.translateXProperty(), to);
        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
        KeyFrame keyFrame2 = new KeyFrame(new Duration(durationInMillis), keyValue2);
        
        Timeline timeline = new Timeline(keyFrame1, keyFrame2);
        if(autoReverse){
            timeline.setCycleCount(2);
        }else{
            timeline.setCycleCount(1);
        }
        timeline.setAutoReverse(autoReverse);
        timeline.play();
    }
    
    /**
     * Scale the Node in both horizontal and vertical orientation. Positive value
     * will make the Node expand, and negative value will shrink the Node down.
     * @param node
     * @param value
     * @param durationInMillis
     * @param autoReverse 
     */
    public static void scale(Node node, double value, long durationInMillis, boolean autoReverse){
        scaleX(node, value, durationInMillis, autoReverse);
        scaleY(node, value, durationInMillis, autoReverse);
    }
    
    public static void scale(Node node, double from, double to, long durationInMillis, boolean autoReverse){
        scaleX(node, from, to, durationInMillis, autoReverse);
        scaleY(node, from, to, durationInMillis, autoReverse);
    }
    
    /**
     * Rotates the Node. The value represents the angle of rotation. If the degree
     * value is set to positive, the Node will rotate clockwise, and negative
     * value will rotate the Node counter-clockwise.
     * @param node
     * @param degrees
     * @param durationInMillis
     * @param autoReverse 
     */
    public static void rotate(Node node, int degrees, long durationInMillis, boolean autoReverse){
        Timeline timeline = new Timeline();
        if(autoReverse){
            timeline.setCycleCount(2);
        }else{
            timeline.setCycleCount(1);
        }
        timeline.setAutoReverse(autoReverse);
        
        KeyValue keyValue = new KeyValue(node.rotateProperty(), degrees);
        KeyFrame keyFrame = new KeyFrame(new Duration(durationInMillis), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    
    public static void rotate(Node node, int from, int to, long durationInMillis, boolean autoReverse){
        DoubleProperty rotate = node.rotateProperty();
        
        KeyValue keyValue1 = new KeyValue(rotate, from);
        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
        
        KeyValue keyValue2 = new KeyValue(rotate, to);
        KeyFrame keyFrame2 = new KeyFrame(new Duration(durationInMillis), keyValue2);
        
        Timeline timeline = new Timeline(keyFrame1, keyFrame2);
        if(autoReverse){
            timeline.setCycleCount(2);
        }else{
            timeline.setCycleCount(1);
        }
        timeline.setAutoReverse(autoReverse);
        timeline.play();
    }
    
    /**
     * Animates the opacity (or transparency) of the Node. The value ranges from
     * 0.0 to 1.0 where 0.0 is completely transparent and 1.0 is 100& opaque.
     * @param node
     * @param opacityValue
     * @param durationInMillis
     * @param autoReverse 
     */
    public static void fade(Node node, double opacityValue, long durationInMillis, boolean autoReverse){
        Timeline timeline = new Timeline();
        if(autoReverse){
            timeline.setCycleCount(2);
        }else{
            timeline.setCycleCount(1);
        }
        timeline.setAutoReverse(autoReverse);
        
        KeyValue keyValue = new KeyValue(node.opacityProperty(), opacityValue);
        KeyFrame keyFrame = new KeyFrame(new Duration(durationInMillis), keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    
    public static void fade(Node node, double from, double to, long durationInMillis, boolean autoReverse){
        DoubleProperty opacity = node.opacityProperty();
        
        KeyValue keyValue1 = new KeyValue(opacity, from);
        KeyValue keyValue2 = new KeyValue(opacity, to);
        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
        KeyFrame keyFrame2 = new KeyFrame(new Duration(durationInMillis), keyValue2);
        
        Timeline timeline = new Timeline(keyFrame1, keyFrame2);
        if(autoReverse){
            timeline.setCycleCount(2);
        }else{
            timeline.setCycleCount(1);
        }
        timeline.setAutoReverse(autoReverse);
        timeline.play();
    }
}
