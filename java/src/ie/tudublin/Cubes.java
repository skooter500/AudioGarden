package ie.tudublin;

import processing.core.PApplet;

public class Cubes extends Poly
{
    float start = v.millis();
    float sizeBox = 800;
    float sizeRect = 10;

    public Cubes(IAMDANI v)
    {
        super(v);
    }   

    public void enter()
    {
        sizeBox = 1000;
        sizeRect = 10;
        v.cqz = 255;
    }

    @Override
    public void render(int ellapsed)
    {        
        v.noStroke();
        v.lights();
        v.smooth();
        
        //function calls for main box and background 
        boxes();
        back();
    }

    void back()
    {
        float count = 0;
        v.translate(v.width/2, v.height/2);
        
        for(int i = 0; i < 200;i++)
        {
            v.push();                    
            v.rotate(PApplet.sin(v.frameCount+i)*100);
            
            //colour map
            float c = PApplet.map(count, 0, v.getAudioBuffer().size() , 100, 400);

            v.stroke(v.hueShift(c),255,255, v.alp);
            v.strokeWeight(1);
            v.noFill();
            //background circles
            //+i causes circles to move outwards up to 400
            v.circle(200, 200+i * v.spe, 1);
            //Rectangles with spaces between them to give effects.
            //getsmoothed causes them to beat out in width to the music
            v.rect(100-i*2 * v.spe, 100, i, 0+i*v.getSmoothedAmplitude()*4);
            v.rect(100-i*2 * v.spe, 100, i, 5+i*v.getSmoothedAmplitude()*4);
            v.rect(100-i*2 * v.spe, 100, i, 10+i*v.getSmoothedAmplitude()*4);
            v.rect(100-i*2 * v.spe, 100, i, 15+i*v.getSmoothedAmplitude()*4);
            v.pop();
            count++;
        }
        
    }
    
    void boxes()
    {
        float count = 0;
        //position is PI div by 34, so boxes aren't on top of eachother.
        float pos = PApplet.PI/34;
        float angle = 0;
        
        //after 5 seconds the main box gets smaller until its 300 at a rate of 0.008f
        if(v.millis() - start > 5000)
        {
            //starts big and gets smaller after 5 seconds
            sizeBox = PApplet.lerp(sizeBox, 300, 0.008f * v.spe);
        }
        //cubes rotataing on diff angles to give effect they are enveloped in eachother
        for(int i= 0; i < 70; i++)
        {
            float c = PApplet.map(count, 0, v.getAudioBuffer().size() , 0, 225);
            //push/pop matrix keeps all the 200 boxes in the same spot
            v.pushMatrix();            
            
            v.translate(v.width/2, v.height/2);


            v.rotateX(v.pit);
            v.rotateZ(v.yaw);

            v.fill(v.hueShift(c), 255, 255, v.alp);
            //rotates boxes on X + Y axis. This way we see the shape on its side. This doesn't move the shape, just positions it
            v.rotateY(angle + pos *i*PApplet.sin(angle)+10);
            v.rotateX(angle/2 + pos*i+10);
            //rotate allows for movement
            v.rotate(PApplet.radians(v.frameCount) * v.targetSpe);

            

            v.box(sizeBox);
            v.popMatrix();
            //count for colour map
            count+=10;
        }
        angle+=0.01 * v.spe;
         
    } 
}
