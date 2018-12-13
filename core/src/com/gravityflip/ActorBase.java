package com.gravityflip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Set;

/**
 *  Extend the Actor class to include graphics and collision detection.
 *  Actor class stores data such as position and rotation.
 */
public class ActorBase extends Actor {

    public float Health = 1;
    private TextureRegion textureRegion;
    private Rectangle rectangle;

    protected float speed = 20;

    private HashMap<String,AnimationStruct> Animations = new HashMap<String, AnimationStruct>();

    private AnimationStruct CurrentAnim;
    protected boolean spriteFlipped = false;

    private Vector2 velocityVec;
    private Vector2 accelerationVec;
    private float acceleration;
    private float maxSpeed;
    private float deceleration;

    private Animation<TextureRegion> animation;
    private float elapsedTime;
    private boolean animationPaused;

    private Polygon boundaryPolygon;

    // stores size of game world for all actors
    static Rectangle worldBounds;
    private String currAnimKey;

    Vector2 movementDir;
    public boolean worldBound = true;

    public ActorBase() {
        super();
        textureRegion = new TextureRegion();
        rectangle = new Rectangle();
        animation = null;
        elapsedTime = 0;
        animationPaused = false;

        velocityVec = new Vector2(0, 0);
        accelerationVec = new Vector2(0, 0);
        acceleration = 0;
        maxSpeed = 1000;

        setWorldBounds(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        boundaryPolygon = null;
    }

    public ActorBase(float x, float y, Stage s) {

        super();
        setPosition(x, y);
        s.addActor(this);

        textureRegion = new TextureRegion();
        rectangle = new Rectangle();

        velocityVec = new Vector2(0, 0);
        accelerationVec = new Vector2(0, 0);
        acceleration = 0;
        maxSpeed = 1000;

        animation = null;
        elapsedTime = 0;
        animationPaused = false;
        boundaryPolygon = null;

        setWorldBounds(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        boundaryPolygon = null;
    }

    public Rectangle getRectangle() {
        rectangle.setPosition(this.getX(), this.getY());
        return rectangle;
    }

    public float GetHealth(){
        return Health;
    }

    public void TakeDamage(float damage){
        Health -= damage;
        if (Health <= 0){
        }
    }

    //Stage will automatically call Actor class
    public void act(float dt) {
        super.act(dt);
        if (worldBound){
            boundToWorld();
        }

        if (!animationPaused)
            elapsedTime += dt;
    }

    public void ResetElapsed(){
        elapsedTime = 0;
    }

    public void draw(Batch batch, float parentAlpha) {

        Color c = getColor(); // used to apply tint color effect

        batch.setColor(c.r, c.g, c.b, c.a);

        if (animation != null && isVisible())
            batch.draw(animation.getKeyFrame(elapsedTime),
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

        super.draw(batch, parentAlpha);

    }

    /**
     *  //APPLY PHYSICS ON ACTOR
     * @param width
     * @param height
     */

    //set acceleation of actor
    public void setAcceleration(float acc) {

        acceleration = acc;
    }

    //set maximum speed of actor
    public void setMaxSpeed(float ms) {
        maxSpeed = ms;
    }

    public void setSpeed(float speed) {
        //if length is zero, then assume motion angle is zero degrees
        if (velocityVec.len() == 0)
            velocityVec.set(speed, 0);
        else
            velocityVec.setLength(speed);
    }
    public void accelerateAtAngle(float angle)
    {
        accelerationVec.add(
                new Vector2(acceleration, 0).setAngle(angle));
    }


    public void applyPhysics(float dt)
    {
        //apply acceleration
        velocityVec.add(accelerationVec.x * dt, accelerationVec.y * dt);

        float speed = getSpeed();

        //decrease soeed (decelerate) when not accelerating
        if(accelerationVec.len() == 0)
            speed -= deceleration * dt;

        //keep speed within set bounds
        speed = MathUtils.clamp(speed, 0, maxSpeed);

        //update velocity
        setSpeed(speed);

        //update position according to value stored in velocity vector
        moveBy(velocityVec.x * dt, velocityVec.y * dt);

        //reset acceleration
        accelerationVec.set(0,0);
    }

    public void setTexture(Texture t) {
        textureRegion.setRegion(t);
        this.setSize(t.getWidth(), t.getHeight());
        rectangle.setSize(t.getWidth(), t.getHeight());
    }


    /* Animation methods */

    public void setAnimation(String key) {

        //Instantiate animation object while passing in array and duration of each frame
        if(currAnimKey == key) return;
        if (!Animations.containsKey(key)) return;


        currAnimKey = key;
        CurrentAnim = Animations.get(key);

        Animation<TextureRegion> anim = new Animation<TextureRegion>(CurrentAnim.frameDuration, CurrentAnim.textureRegions);


        //if loop is true, set LOOP ON
        if (CurrentAnim.loop)
            anim.setPlayMode(Animation.PlayMode.LOOP);

            //else, no looping
        else
            anim.setPlayMode(Animation.PlayMode.NORMAL);

        //if animation is null, set the animation to a default state
        animation = anim;
        TextureRegion tr = animation.getKeyFrame(0);
        float w = tr.getRegionWidth();
        float h = tr.getRegionHeight();


        setSize(w, h);
        setOrigin(w / 2, h / 2);

    }

    /* Animation methods for multiple images */

    public void loadAnimationFromFiles(String animKey, String[] fileNames, float frameDuration, boolean loop) {
        //Number of images to read
        int fileCount = fileNames.length;

        //Create empty TextureRegion array
        Array<TextureRegion> textureArray = new Array<TextureRegion>();

        //For the number of images, add them back into the array
        for (int n = 0; n < fileCount; n++) {
            String fileName = fileNames[n];
            //Create new texture with fileName at n
            Texture texture = new Texture(Gdx.files.internal(fileName));

            //Set Linear filter
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            //Add this textures to the Array

            TextureRegion text = new TextureRegion(texture);
            //text.flip(false,false);
            textureArray.add(text);
        }

        AnimationStruct animStruct = new AnimationStruct();
        animStruct.textureRegions = textureArray;
        animStruct.frameDuration = frameDuration;
        animStruct.loop = loop;

        Animations.put(animKey, animStruct);

        if (animation == null)
            setAnimation(animKey);

    }

    public void loadTexture(String fileName) {
        //set a new array of file names to a capacity of 1
        String[] fileNames = new String[1];

        //first image in array will be file name
        fileNames[0] = fileName;

        //return anim object from first animation method that will load a single image
        loadAnimationFromFiles(fileName ,fileNames, 1, true);
    }



    public void FlipCurrentAnim(){
        Set<String> keys = Animations.keySet();
        for (String key : keys) {
            for (TextureRegion tex: Animations.get(key).textureRegions) {
                tex.flip(false,true);
            }
        }
    }

    public boolean isAnimationFinished() {
        return animation.isAnimationFinished(elapsedTime);
    }

    public void setAnimationPaused(boolean pause) {
        animationPaused = pause;
    }

    /**
     *  //SET ACTOR BACK TO BEGINNING
     * @param width
     * @param height
     */

    public void wrapAroundWorld()
    {
        if (getX() + getWidth() < 0)
            setX( worldBounds.width );

        if (getX() > worldBounds.width)
            setX( -getWidth());

        if (getY() + getHeight() < 0)
            setY( worldBounds.height );

        if (getY() > worldBounds.height)
            setY( -getHeight() );
    }


    /**
     *  //SET BOUNDING BOX
     * @param width
     * @param height
     */

    public void setBoundaryRectangle()
    {
        float w = getWidth();
        float h = getHeight();

        float [] vertices = {0, 0, w, h, 0, h};
        boundaryPolygon = new Polygon(vertices);
    }

    public void setBoundaryPolygon(int numSides) {
        float w = getWidth();
        float h = getHeight();

        float[] vertices = new float[2 * numSides];
        for (int i = 0; i < numSides; i++) {
            float angle = i * 6.28f / numSides;
            // x-coordinate
            vertices[2 * i] = w / 2 * MathUtils.cos(angle) + w / 2;
            // y-coordinate
            vertices[2 * i + 1] = h / 2 * MathUtils.sin(angle) + h / 2;
        }
        boundaryPolygon = new Polygon(vertices);

    }

    /**
     *  //Get bounding polygon
     * @param width
     * @param height
     */
    public Polygon getBoundaryPolygon()
    {
        boundaryPolygon.setPosition(getX(), getY());
        boundaryPolygon.setOrigin(getOriginX(), getOriginY());
        boundaryPolygon.setRotation(getRotation());
        boundaryPolygon.setScale(getScaleX(), getScaleY());
        return  boundaryPolygon;
    }

    /**
     *  //Use if actors overlap each other
     * @param width
     * @param height
     */
    public boolean overlaps(ActorBase other) {

        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        //initial test to improve performance
        if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
            return  false;

        return Intersector.overlapConvexPolygons(poly1, poly2);
    }

    /**
     *  //Stop actor from overlapping another Actor
     * @param width
     * @param height
     */
    public Vector2 preventOverlap(ActorBase other) {

        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        //if initial test to improve performance
        if(!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
            return null;

        MinimumTranslationVector mtv = new MinimumTranslationVector();
        boolean polygonOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mtv);

        if(!polygonOverlap)
            return null;

        this.moveBy(mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);

        return mtv.normal;
    }

    public void setOpacity(float opacity) {
        this.getColor().a = opacity;
    }

    /**
     *  //If actor is within distance of another Actor
     * @param width
     * @param height
     */

    public boolean isWithinDistance(float distance, ActorBase other)
    {
        Polygon polygon1 = this.getBoundaryPolygon();
        float scaleX = (this.getWidth() + 2 * distance) / this.getWidth();
        float scaleY = (this.getHeight() + 2 * distance) / this.getHeight();
        polygon1.setScale(scaleX, scaleY);

        Polygon polygon2 = other.getBoundaryPolygon();

        //initial test to improve performance
        if(!polygon1.getBoundingRectangle().overlaps(polygon2.getBoundingRectangle()))
            return false;

        return Intersector.overlapConvexPolygons(polygon1, polygon2);
    }

    /**
     *  //Bound actor within world
     * @param width
     * @param height
     */
    public static void setWorldBounds(float width, float height)
    {
        worldBounds = new Rectangle(0, 100, width, height);
    }


    public static Rectangle getWorldBounds()
    {

        return worldBounds;
    }

    public void boundToWorld()
    {
        if (getX() < 0)
            setX(0);
        if (getX() + getWidth() > worldBounds.width)
            setX(worldBounds.width - getWidth());
        if (getY() < getWidth()* 2f)
            setY(getWidth() * 2f);
        if (getY() + getHeight() > worldBounds.height)
            setY(worldBounds.height - getHeight());
    }

    public boolean InWorldBound(){

            if (getX() < 0)
                return false;
            if (getX() + getWidth() > worldBounds.width)
                return false;
            if (getY() < getWidth()* 2f)
                return false;
            if (getY() + getHeight() > worldBounds.height)
                return false;
            else{
                return true;
            }
    }




    public float getSpeed() {
        return speed;
    }

    public Vector2 MovevementDir() {
        return movementDir;
    }

    public void SetMovementDir(Vector2 movementDir) {
        this.movementDir = movementDir;
    }

    public Vector2 getPosition() {
        return new Vector2(getX(),getY());
    }
}