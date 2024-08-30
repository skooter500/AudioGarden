package infiniteforms;

import ie.tudublin.IAMDANI;
import ie.tudublin.DANI;
import ie.tudublin.Art;
import processing.core.PFont;

public class Nematode extends Art
{
  float length = 5;
  String name = "";
  float w = 50;

  int limbs = 1;
  float eyes = 2;
  char gender = 'h';
  float r = w * 0.5f;
  float eyeRadius = w * 0.2f;
  float  c1, c2;
  float fatness = 1.0f;
  
  float speed = 0.0f;
  
  float alpha = 127;
  
    float cw = 50;

    String[] nameCombos = {"Veridixalus", "Obscuronema", "Cryptodermis", "Abyssolidae", "Luminentia", "Mythiloricus", "Xenoplaga", "Silentusis", "Nebulatus", "Crepituspora", "Vorticella", "Aureolux", "Serpentifex", "Mystinaria", "Venomorpha", "Chiaroscarabia", "Necrobyssia", "Aquiluxidae", "Umbraquillia", "Astraflora", "Igniscens", "Caelumbricus", "Quasariana", "Phantomalis", "Cognitara", "Aetherworm", "Luminentis", "Astrovermis", "Spectraquill", "Mysterionema", "Veritasquama", "Nebulifera", "Cryptonecta", "Cirrhaedis", "Luminaridae", "Astralisia", "Venomiridia", "Mystiqua", "Vorpalis", "Cryptotilla", "Abyssogloea", "Luminarix", "Caelumnex", "Praeternix", "Aquiluxa", "Phasmidrillus", "Venomorium", "Cirruscula", "Veridixia", "Mystikonema", "Obscuraquill", "Xenocrypta", "Astraflorum", "Ignisvermis", "Caelumbra", "Quasarix", "Phantomnema", "Cognitarum", "Aetherianus", "Luminara", "Astroverma", "Spectrillus", "Mysteriosa", "Veritara", "Nebularum", "Cryptoforma", "Cirrhaedon", "Luminaris", "Astralisus", "Venomoria", "Mystara", "Vorpalisus", "Cryptotillus", "Abyssogloia", "Luminarixus", "Caelumnexus", "Praeternixis", "Aquiluxus", "Phasmidrilia", "Venomoriana", "Cirrusculis", "Veridixus", "Mysticus", "Obscuronia", "Cryptodermus", "Abyssolus", "Luminensis", "Mythoricus", "Xenoplaga", "Silentisus", "Nebulus", "Crepitum", "Vorticellus", "Aureoluxus", "Serpentifexis", "Mystinariusus", "Venomorphaeus", "Chiaroscarabus", "Necrobyssian", "Aquiluxidaeus"};
    
    DANI dani;

  public Nematode(IAMDANI v, int length, String name, int limbs, int eyes, char gender) {
    super(v);
    this.length = length;
    this.name = name;
    this.limbs = limbs;
    this.eyes = eyes;
    this.gender = gender;
    
  }

  public Nematode(IAMDANI v)
  {
    super(v);

    dani = new DANI(v, "nematodes.txt");
    dani.l = 15;
    v.cqz = 50;
    v.targetCqz = 50; 
  }

  
  int reset = 0;
  
  public void enter()
  {
    c1 = v.random(0, 256);
    c2 = c1 + 127;
    length = (int) v.random(1, 5); 
    limbs = (int) v.random(0, 2);
    eyes = (int) v.random(1, 9);
    
    char[] genders = {'m','f', 'h', 'n'};    
    gender = genders[(int)v.random(0, genders.length)];
    
    fatness = v.random(50, 200);
    
    int nameLength = 1;
    name = "";
    for(int i = 0 ; i < nameLength ; i ++)
    {
      name += nameCombos[(int)v.random(0, nameCombos.length)] + " ";
    }
    name = name.substring(0, name.length() - 1);
    colorOffset = v.random(0, 256);
    v.println(this);
    dani.t = this;
    dani.enter();
    reset = 0;
  }

  float theta = 0;


  public void render()
  {
    render(v.width / 2, v.height / 1.8f, v.frameCount * speed);
  }
  
  float colorOffset = 0;

  public void render(float cx, float cy, float offs)
  {
    
    w = 80;
    float half = w * length * 0.5f;
    v.strokeWeight(2);

    
    
    v.pushMatrix();
    //v.camera(0, 0, -5000, 0, 0, 0f, 0f, 0.001f, 0f);
    v.translate(cx * .6f, cy);
    v.translate(-200, - half * 0.2f - 100);

    //v.rotateX(v.pit);
    //v.rotateZ(v.PI + v.yaw);

    v.noFill();
    float hw = w / 2;

    
    float c3 = v.hueShift(c2); 
      
    v.fill(c3 % 255, 255, 255, v.alp);
    v.textAlign(v.CENTER, v.CENTER);
    v.textAlign(v.CENTER, v.CENTER);    
    v.text(name, 0, -w * 3.5f);       
    v.translate(0, 130); 
    v.noFill();
    for (int i = 0; i < length; i ++)
    { 
      v.rotateX(v.pit);
      v.rotateY(-v.yaw + 0.13f);
      v.rotateZ(v.rol);
      //println(c1, c2);
      v.stroke(c3, 255, 255, v.alp);
    
      float y = i * w;
      float f = 0.5f;
      float w1 = v.sin(v.map(i, 0, length, f + f, v.PI)) * w;
      //w = w1;
      //w = w1;
      v.ellipse(0, y, w, w);
      float haw = w * 0.5f;
      if (limbs > 0 && i > 0)
      {
        float er = (w * 1.0f) - eyeRadius;
        v.line(-haw, y, -er + (eyeRadius * 1.5f), y);
        v.line(haw, y, er - (eyeRadius * 1.5f), y);
        v.stroke(v.hueShift(c2), 255, 255, v.alp);
        v.circle(er, y, eyeRadius * 3.0f);
        v.circle(-er, y, eyeRadius * 3.0f);
      }      
      if (i == 0)
      {
        drawEyes(eyes, w * 0.5f, w * 0.5f);
      }
    }
    
    drawGenitals();
    theta += v.spe * 0.03f * v.getSmoothedAmplitude();
    v.translate(-200, -500); 
    dani.render(false);
       
    v.popMatrix();
    
  }

  private void drawGenitals()
  {
    switch (gender)
    {
    case 'm':
      { 
        float y =  ((length-1) * w) + (w * 0.5f);   
        v.stroke(v.hueShift(c1), 255, 255, v.alp);
        v.line(0, y, 0, y + r);
        v.stroke(v.hueShift(c2), 255, 255, v.alp);
        v.circle(0, y + r + eyeRadius, eyeRadius * 2.0f);
      }
      break; 
    case 'f':  
      { 
        float y =  ((length-1) * w);  
        v.stroke(v.hueShift(c2), 255, 255, v.alp);
        v.circle(0, y, eyeRadius * 4.0f);
      }
      break;
    case 'h':
      {
        float y =  ((length-1) * w);  
        v.stroke(v.hueShift(c2), 255, 255, v.alp);
        v.circle(0, y, eyeRadius * 4.0f);
        y =  ((length-1) * w) + (w * 0.5f);   
        v.stroke(v.hueShift(c1), 255, 255, v.alp);
        v.line(0, y, 0, y + r);
        v.stroke(v.hueShift(c2), 255, 255, v.alp);        
        v.circle(0, y + r + eyeRadius, eyeRadius * 2.0f);
      }
      break;
    }
  }
  
  public String toString() {
    return "Tadpole [c1=" + c1 + ", c2=" + c2 + ", eyeRadius=" + eyeRadius + ", eyes=" + eyes + ", gender=" + gender
            + ", length=" + length + ", limbs=" + limbs + ", name=" + name + ", r=" + r + ", w=" + w + "]";
}

  void drawEyes(float numEyes, float hw, float hh)
  { //<>//
    float offs = 90.0f / (float)numEyes;
    for(float i = 0 ; i < numEyes ; i ++)
    {      
      float angle = v.map(i, 0, numEyes, -90, 90) + offs;
      float stalkLength = r * 0.25f + (v.sin(v.map(angle, -90, 90, 0, v.PI)) * r * 8);
      
      drawEye(angle, stalkLength, hw, hh);
      
    }     
  }

  private void drawEye(float angle, float stalkLength, float headW, float headH)
  {
    
    float x1 = v.sin(v.radians(angle)) * (headW);
    float y1 = - v.cos(v.radians(angle)) * (headH);

    float x2 = v.sin(v.radians(angle)) * (headW + stalkLength);
    float y2 = - v.cos(v.radians(angle)) * (headH + stalkLength);
    float ex = v.sin(v.radians(angle)) * (headW + stalkLength + eyeRadius);
    float ey = - v.cos(v.radians(angle)) * (headH + stalkLength + eyeRadius);
    v.stroke(v.hueShift(c2), 255, 255, v.alp);        

    v.rotateX(v.pit);
    v.rotateY(-v.yaw + 0.13f);
    v.rotateZ(v.rol);

    v.circle(ex, ey, eyeRadius * 2.0f);

    v.stroke(v.hueShift(c1), 255, 255, v.alp);
    v.line(x1, y1, x2, y2);
  }
}
