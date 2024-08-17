package infiniteforms;

import ie.tudublin.IAMDANI;
import ie.tudublin.Art;

public class IFCubes extends Art {
    IFCube[] cubes;
    float z = 0;

    String filename;

    public int colorOffset = 0;
    private float radius;
    private int numCubes;
  
  public void enter()
  {

   
    
    float offs = v.random(0, 255);
    for(IFCube c:cubes)
    {
      c.smoothedBoxSize = 0;
      c.colorOffset = offs;
    }
    v.targetCqz = 1;
    v.cqz = 1;
    v.bas = v.targetBas;
    v.cqz = 1;
    v.targetCqz = 1;

  }

  public String toString()
  {
    return filename;
  }
  
  public IFCubes(IAMDANI v, int numCubes, float radius, float z, String filename)
  {
    super(v);
    this.z = z;
    this.filename = filename;
    this.radius = radius;
    this.numCubes = numCubes;

    cubes = new IFCube[numCubes];
    
    for(int i = 0 ; i < numCubes ; i ++)
    {
      float theta = v.map(i, 0, numCubes, v.HALF_PI, v.TWO_PI + v.HALF_PI);
      float x = v.sin(theta) * radius;
      float y = - v.cos(theta) * radius;
      cubes[i] = new IFCube(v, x, y, 0.0f, filename);        
    }
  }
  
  float theta = 0;
    
  public void render()
  {
    v.noFill();
    v.lights();
    v.camera(0, 0, -700, 0, 0, 0f, 0f, 0.001f, 0f);
    v.strokeWeight(2);

    float col = v.hueShift((colorOffset));        
    
    v.stroke(col, 255, 255);
    v.pushMatrix();    
    v.rotateX(v.pit);
    v.rotateY(v.yaw);
    v.rotateZ(v.rol);

    //v.camera(0, 0, -z, 0, 0, -1, 0, 1, 0);
    //rotateY(theta);   
    //rotateX(theta);    
    for(IFCube c:cubes)
    {
      c.render();       
    }    
    v.popMatrix();
    theta+=0.01f * v.spe;
    v.camera(v.width/2.0f, (v.height/2.0f) - 5000, (v.height/2.0f) / v.tan(v.PI*30.0f / 180.0f), v.width/2.0f, v.height/2.0f, 0, 0, 1, 0);
    
  }
}
