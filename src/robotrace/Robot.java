package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.*;

/**
* Represents a Robot, to be implemented according to the Assignments.
*/
class Robot {
    
    /** The position of the robot. */
    
    public Vector position = new Vector(0,0,0);
    
    /** The direction in which the robot is running. */
    public Vector direction = new Vector(1, 1, 0);
    
    /** Angle of limbs required to make a "running" movement. */
    public int Angle_Limbs = 45; 
    public double angle_race;
    
    /** Computation of the angle required to rotate the robot in the direction of the direction vector. */
    private double xdir = direction.x;
    private double ydir = direction.y;
    public double Angle_Direction = Math.acos(xdir/(Math.sqrt(Math.pow(xdir, 2)+Math.pow(ydir,2))))*180/Math.PI;

    /** The material from which this robot is built. */
    private final Material material;

    /**
     * Constructs the robot with initial parameters.
     */
    public Robot(Material material
            
    ) {
        this.material = material;
    }
    
    public void setAngle(double angle_race) {
        this.angle_race = angle_race;
    }
    /**
     * Draws this robot (as a {@code stickfigure} if specified).
     */
    public void draw(GL2 gl, GLU glu, GLUT glut, float tAnim, double angle_slider) {
        gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, material.diffuse, 0);
        gl.glMaterialfv(GL_FRONT, GL_SPECULAR, material.specular, 0);
        gl.glMaterialf(GL_FRONT, GL_SHININESS, material.shininess);
        //System.out.println("Angle="+Angle_Direction);
        gl.glPushMatrix();      
            // Vector position is defined in the Robot class
            // This position vector is defined as the vector to the center of the torso of the robot
            gl.glTranslated(position.x,position.y,position.z+1);
            gl.glRotated(angle_race ,0,0,1);
            gl.glScaled(0.4,0.4,0.75);
            glut.glutSolidCube(1);
            gl.glScaled(1,1,0.667);
            drawleftarm(gl,glu,glut,tAnim, angle_slider);
        gl.glPopMatrix();        
    }
    
    public void drawleftarm(GL2 gl, GLU glu, GLUT glut, float tAnim, double angle_slider) {
        // Left Arm
        gl.glTranslated(0,-0.75,0.75);
        gl.glRotated(angle_slider*-90+45,0,1,0);
        gl.glTranslated(0,0,-0.75);       
        gl.glScaled(0.5,0.5,1.5);
        glut.glutSolidCube(1);
        gl.glScaled(2,2,0.667);
        gl.glTranslated(0,0,0.75);
        gl.glRotated(angle_slider*90-45,0,1,0);
        drawrightarm(gl,glu,glut,tAnim, angle_slider);
    }
    
    public void drawrightarm(GL2 gl, GLU glu, GLUT glut, float tAnim, double angle_slider) {
        // Right Arm
        gl.glTranslated(0,1.5,0);
        gl.glRotated(angle_slider*90-45, 0, 1, 0);
        gl.glTranslated(0,0,-0.75);
        gl.glScaled(0.5,0.5,1.5);
        glut.glutSolidCube(1);  
        gl.glScaled(2,2,0.667);
        gl.glTranslated(0,0,0.75);
        gl.glRotated(angle_slider*-90+45,0,1,0);
        drawrightfoot(gl,glu,glut,tAnim, angle_slider);
    }
    
    public void drawrightfoot(GL2 gl, GLU glu, GLUT glut, float tAnim, double angle_slider) {
        // Right Foot
        gl.glTranslated(0,-0.5,-1.5);
        gl.glRotated(angle_slider*-90+45,0,1,0);
        gl.glTranslated(0,0,-0.75);
        gl.glScaled(0.5,0.5,1.5);
        glut.glutSolidCube(1);
        gl.glScaled(2,2,0.667);
        gl.glTranslated(0,0,0.75);
        gl.glRotated(angle_slider*90-45,0,1,0);
        drawleftfoot(gl,glu,glut,tAnim, angle_slider);
    }
    public void drawleftfoot(GL2 gl, GLU glu, GLUT glut, float tAnim, double angle_slider) {
        // Left Foot
        gl.glTranslated(0,-0.5,0);
        gl.glRotated(angle_slider*90-45,0,1,0);
        gl.glTranslated(0,0,-0.75);
        gl.glScaled(0.5,0.5,1.5);
        glut.glutSolidCube(1);
        gl.glScaled(2,2,0.667);
        gl.glTranslated(0,0,0.75);
        gl.glRotated(angle_slider*-90+45,0,1,0);
        drawneck(gl,glu,glut,tAnim, angle_slider);
    }
    
    public void drawneck(GL2 gl, GLU glu, GLUT glut, float tAnim, double angle_slider) {
        // NOTE: The neck is designed as a rectangular rod which is clipped into the head and torso, not a disc.
        gl.glTranslated(0,0.25,1.5);
        gl.glScaled(0.5,0.5,0.5);
        glut.glutSolidCube(1);
        gl.glScaled(2,2,2);
        drawhead(gl,glu,glut,tAnim, angle_slider);
    }

    public void drawhead(GL2 gl, GLU glu, GLUT glut, float tAnim, double angle_slider) {
        //Head
        gl.glRotated(angle_slider*10-5,0,1,0);
        gl.glTranslated(0,0,0.75);
        glut.glutSolidCube(1);

    }
}