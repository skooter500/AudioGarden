package infiniteforms;

import ie.tudublin.IAMDANI;
import ie.tudublin.Poly;
import processing.core.PVector;

public class Cube extends Poly 
{
    
    PVector position;
  
    float smoothedBoxSize = 0;
    float angle = 0;
    float size = 50;
  
    boolean useAmplitude = true;

    int colorOffset = 0;

    public void enter()
    {
        colorOffset = (int) v.random(0, 256);
        smoothedBoxSize = 200;
    }

    public Cube(IAMDANI v)
    {
        super(v);

        position = new PVector(0, 0, 0);
    }    

    public void render()
    {

        v.camera(0, 0, -600, 0, 0, 0f, 0f, 0.001f, 0f);
        v.pushMatrix();
        //v.rotateZ(v.PI + v.yaw);
        //v.translate(v.width / 2, v.height / 2, 0); 
        v.rotateY(v.rol);

        v.rotateX(v.pit);
        v.rotateY(v.yaw);
        v.strokeWeight(2);


        float c = v.hueShift((colorOffset));        
        v.stroke(c, 255, 255, v.alp);
        v.noFill();

        //v.translate(position.x, position.y, position.z);       
        v.rotateY(angle);
        v.rotateX(angle);

        float boxSize = size + (v.getAmplitude() * 200); 
        smoothedBoxSize = v.lerp(smoothedBoxSize, boxSize, 0.1f);                 
        v.box(smoothedBoxSize);
        
        
        angle+=0.001f * v.spe;
        v.popMatrix();
        
        
    }
}
