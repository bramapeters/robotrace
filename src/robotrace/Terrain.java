package robotrace;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.GL2;
import static com.jogamp.opengl.GL2.GL_POLYGON;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import com.jogamp.opengl.glu.GLU;


/**
 * Represents the terrain, to be implemented according to the Assignments.
 */
class Terrain {

    
    
    public Terrain() {
        
    }

    /**
     * Draws the terrain.
     */
    public void draw(GL2 gl, GLU glu, GLUT glut) {
        ShaderPrograms.terrainShader.useProgram(gl);
        double u, v;
        double nu = 10, nv = 10;
        double du = 1/nu, dv = 1/nv;
        gl.glTranslated(-20, -20, 0);
        for(int i = 0; i < nu * 40; i++){
            u = i * du;
            for(int j = 0; j < nv * 40; j++){
                v = j * dv;
                gl.glBegin(GL_QUADS);
                    gl.glVertex3d(u, v, 0);
                    gl.glVertex3d(u, v + dv, 0); 
                    gl.glVertex3d(u + du, v + dv, 0);
                    gl.glVertex3d(u + du, v, 0);
                gl.glEnd();
            }
        }
    }
}
