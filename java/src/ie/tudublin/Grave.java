package ie.tudublin;

public class Grave extends Art {

    public Grave (IAMDANI v){
        super(v);
    }

    public void render(int ellapsed){
        v.lights();
        //v.translate((v.width/2)-50,(v.height/2)-150);
        v.stroke(255);
        

        v.translate((v.width / 2)-50, (v.height/2)+150);
        v.rotateX(600);
        v.rotateY(525);
        v.scale(400);
        //v.fill(198,0,19);
        //v.shape(v.grave);
        v.lights();
        
        
    }
    
}
