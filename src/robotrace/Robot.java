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
    public Vector position = new Vector(0, 0, 2.5);
    
    /** The direction in which the robot is running. */
    public Vector direction = new Vector(1, 1, 0);
    
    /** Angle of limbs required to make a "running" movement. */
    public int Angle_Limbs = 45;  
    
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

    /**
     * Draws this robot (as a {@code stickfigure} if specified).
     */
    public void draw(GL2 gl, GLU glu, GLUT glut, float tAnim) {
        //System.out.println("Angle="+Angle_Direction);

        gl.glPushMatrix();
            gl.glTranslated(position.x,position.y,position.z);
            drawTorso(gl, glu, glut, tAnim, Angle_Direction);
            gl.glTranslated(0,-0.75,0.75);
            drawArms(gl, glu, glut, tAnim, Angle_Limbs);
            gl.glTranslated(0,-0.5,-1.5);
            drawLegs(gl, glu, glut, tAnim, Angle_Limbs);
            gl.glTranslated(0,0.25,1.5);
            drawNeck(gl, glu, glut, tAnim);
            gl.glTranslated(0,0,0.75);
            glut.glutSolidCube(1);
        gl.glPopMatrix();        
    }
    
    public void drawTorso(GL2 gl, GLU glu, GLUT glut, float tAnim, double Angle_Direction){
            // Vector position is defined in the Robot class
            // This position vector is defined as the vector to the center of the torso of the robot
            gl.glRotated(-Angle_Direction,0,0,1);
            gl.glScaled(1,1,1.5);
            glut.glutSolidCube(1);
            gl.glScaled(1,1,0.667);
    }
    
    public void drawArms(GL2 gl, GLU glu, GLUT glut, float tAnim, double Angle_Limbs){
            // Left Arm
            gl.glRotated(-Angle_Limbs,0,1,0);
            gl.glTranslated(0,0,-0.75);       
            gl.glScaled(0.5,0.5,1.5);
            glut.glutSolidCube(1);
            gl.glScaled(2,2,0.667);
            gl.glTranslated(0,0,0.75);
            gl.glRotated(Angle_Limbs,0,1,0);
        
            // Right Arm
            gl.glTranslated(0,1.5,0);
            gl.glRotated(Angle_Limbs, 0, 1, 0);
            gl.glTranslated(0,0,-0.75);
            gl.glScaled(0.5,0.5,1.5);
            glut.glutSolidCube(1);  
            gl.glScaled(2,2,0.667);
            gl.glTranslated(0,0,0.75);
            gl.glRotated(-Angle_Limbs,0,1,0);
    }
    
    public void drawLegs(GL2 gl, GLU glu, GLUT glut, float tAnim, double Angle_Limbs){
            // Right Foot
            gl.glRotated(-Angle_Limbs,0,1,0);
            gl.glTranslated(0,0,-0.75);
            gl.glScaled(0.5,0.5,1.5);
            glut.glutSolidCube(1);
            gl.glScaled(2,2,0.667);
            gl.glTranslated(0,0,0.75);
            gl.glRotated(Angle_Limbs,0,1,0);
        
            // Left Foot
            gl.glTranslated(0,-0.5,0);
            gl.glRotated(Angle_Limbs,0,1,0);
            gl.glTranslated(0,0,-0.75);
            gl.glScaled(0.5,0.5,1.5);
            glut.glutSolidCube(1);
            gl.glScaled(2,2,0.667);
            gl.glTranslated(0,0,0.75);
            gl.glRotated(-Angle_Limbs,0,1,0);
    }
    
    public void drawNeck(GL2 gl, GLU glu, GLUT glut, float tAnim){
            // NOTE: The neck is designed as a rectangular rod which is clipped into the head and torso, not a disc.
            gl.glScaled(0.5,0.5,0.5);
            glut.glutSolidCube(1);
            gl.glScaled(2,2,2);
    }
    
}


