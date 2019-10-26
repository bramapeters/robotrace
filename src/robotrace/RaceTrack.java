package robotrace;

import static com.jogamp.opengl.GL.GL_LINE_LOOP;
import static com.jogamp.opengl.GL.GL_TRIANGLE_STRIP;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import static com.jogamp.opengl.GL2.*;

/**
 * Implementation of a race track that is made from Bezier segments.
 */
abstract class RaceTrack {
    
    /** The width of one lane. The total width of the track is 4 * laneWidth. */
    private final static float laneWidth = 1.22f;
    
    
    
    /**
     * Constructor for the default track.
     */
    public RaceTrack() {
        
    }

    
    /**
     * Draws this track, based on the control points.
     */
    public void draw(GL2 gl, GLU glu, GLUT glut, GlobalState gs, double trackNr) {
        // If gs.trackNr = 0, the parametrics track is drawn.
<<<<<<< HEAD
        if(trackNr==1){
=======
        if(trackNr==0){
            
>>>>>>> parent of 4c14e1b... track texture added including shading
            System.out.println("trackNr="+gs.trackNr);

            int N=40, Ntracks=4, Ncorners=2;    
            int tmin=0;        
            double dt = Math.pow(N, -1);

            drawTrack(gl, glu, glut, tmin, dt, N, Ntracks, Ncorners);
            drawBrick(gl, glu,glut, tmin, dt, N, Ntracks, Ncorners);
            /** Draw Ntracks+1 lines that separate each track. */
            for (int k=0; k<=Ntracks;k++){
                gl.glBegin(GL_LINE_LOOP);
                gl.glColor3f(0, 0, 0);   

                /** Draw a line between each track using N vertices. */
                for (int i=0; i<=N; i++){
                    Vector P = getPoint(tmin+i*dt);
                    Vector Normal = new Vector(0,0,1);
                    Vector Tangent = getTangent(tmin+i*dt);
                    Vector Bitangent = Normal.cross(Tangent);
                    gl.glVertex3d(P.x+(k-3)*laneWidth*Bitangent.x,P.y+(k-3)*laneWidth*Bitangent.y,P.z+(k-3)*laneWidth*Bitangent.z);     
                }
                gl.glEnd();
            }
            
        // If gs.trackNr = 1, the Bezier track is drawn.
        } else if (trackNr==0){
            System.out.println("trackNr="+gs.trackNr);
            
            double[] Px = new double[]{-20,-20,20,20};
            double[] Py = new double[]{-20,20,20,-20};
            double[] Pz = new double[]{1,1,1,1};

            int M = Px.length;
            int N = 20;
            int Ntracks=4;
            int tmin=0;
            double dt = Math.pow(N,-1);

<<<<<<< HEAD

            for(int i = 0; i < M; i++){
                Vector P0 = new Vector(0,0,0);
                Vector P1 = new Vector(-5,-3,1);
                Vector P2 = new Vector(-5,3,1);
                Vector P3 = new Vector(0,0,0);
                
                /** 0th order of Continuity: Q0 = P3. */
                if(i==M-1){
                    Vector P0add = new Vector(Px[i],Py[i],Pz[i]);
                    P0 = P0.add(P0add);
                    Vector P3add = new Vector(Px[0],Py[0],Pz[0]);
                    P3 = P3.add(P3add);
                }else{
                    Vector P0add = new Vector(Px[i],Py[i],Pz[i]);
                    P0 = P0.add(P0add);
                    Vector P3add = new Vector(Px[i+1],Py[i+1],Pz[i+1]);
                    P3 = P3.add(P3add);
=======
                /** Draw a line between each track using N vertices. */
                for (int i=0; i<=N; i++){
                    Vector P = getPoint(tmin+i*dt);
                    Vector Normal = new Vector(0,0,1);
                    Vector Tangent = getTangent(tmin+i*dt);
                    Vector Bitangent = Normal.cross(Tangent);
                    gl.glVertex3d(P.x+(k-3)*laneWidth*Bitangent.x,P.y+(k-3)*laneWidth*Bitangent.y,P.z+(k-3)*laneWidth*Bitangent.z);     
>>>>>>> parent of 4c14e1b... track texture added including shading
                }
                /** 1th order of Continuity: P3-P2=Q1-Q0. */

                if(i!=0 && i%2!=0){ //odd
                    P1.x=2*P0.x-P2.x;
                    P1.y=2*P0.y-P2.y;
                    P1.z=1;
                    P2.x=-P1.x;
                    P2.y=P1.y;
                    P2.z=1;
                } else if(i!=0 && i%2==0){  //even
                    P1.x=-P2.x;
                    P1.y=-P2.x;
                    P1.z=1;                    
                    P2.x=P1.x;
                    P2.y=-P1.y;
                    P2.z=1;
                }

                gl.glBegin(GL_LINE_LOOP);
                gl.glColor3f(0,0,0);
                    for(int k = 0; k <= N; k++){                  
                        Vector P = getCubicBezierPnt(tmin+k*dt,P0,P1,P2,P3);
                        gl.glVertex3d(P.x,P.y,P.z);
                    }
                
                
            }
                
            gl.glEnd();
            

            
        }
    }
    
    public void drawTrack(GL2 gl, GLU glu, GLUT glut, int tmin, double dt, int N, int Ntracks, int Ncorners){
<<<<<<< HEAD
        ShaderPrograms.trackShader.useProgram(gl);
        Textures.track.bind(gl);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        for (int i = 0; i < 100; i++){
            gl.glBegin(GL_TRIANGLE_STRIP);
            for (int j = -2; j <= 2; j++) {
                double i_d = i * 0.01;
                gl.glNormal3d(Vector.Z.x, Vector.Z.y, Vector.Z.z);
                Vector N1 = getTangent(i_d).cross(Vector.Z).normalized();
                Vector P1 = getPoint(i_d).add(N1.scale(j * laneWidth));
                Vector N2 = getTangent(i_d + 0.01).cross(Vector.Z).normalized();
                Vector P2 = getPoint(i_d + 0.01).add(N2.scale(j * laneWidth));
                
                double texx = 0.25 * (j + 2) ;
                double texy = 10 * i_d;     
                gl.glTexCoord2d(texx, texy);
                gl.glVertex3d(P1.x, P1.y, P1.z);
                texy = 10 * (i_d + 0.01);
                gl.glTexCoord2d(texx, texy);
                gl.glVertex3d(P2.x, P2.y, P2.z);
            }
            gl.glEnd();
            gl.glFlush();
        }

    }
    
    public void drawBrick(GL2 gl, GLU glu, GLUT glut, int tmin, double dt, int N, int Ntracks, int Ncorners){
        ShaderPrograms.trackShader.useProgram(gl);
        Textures.brick.bind(gl);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        for (int i = 0; i < 100; i++){
            double i_d = i * 0.01;
            gl.glBegin(GL_TRIANGLE_STRIP);
                Vector N1 = getTangent(i_d).cross(Vector.Z).normalized();
                Vector P1 = getPoint(i_d).add(N1.scale(2 * laneWidth));
                Vector N2 = getTangent(i_d + 0.01).cross(Vector.Z).normalized();
                Vector P2 = getPoint(i_d + 0.01).add(N2.scale(2 * laneWidth));
                gl.glNormal3d(Vector.Z.x, Vector.Z.y, Vector.Z.z);
                gl.glTexCoord2d(0, 0);
                gl.glNormal3d(N1.x, N1.y, N1.z);
                gl.glVertex3d(P1.x, P1.y, P1.z);
                gl.glTexCoord2d(0, 1);
                gl.glVertex3d(P1.x, P1.y, P1.z - 1);
                gl.glTexCoord2d(1, 0);
                gl.glNormal3d(N2.x, N2.y, N2.z);
                gl.glVertex3d(P2.x, P2.y, P2.z);
                gl.glTexCoord2d(1, 1);
                gl.glVertex3d(P2.x, P2.y, P2.z - 1);
            gl.glEnd();
            gl.glBegin(GL_TRIANGLE_STRIP);
                Vector N3 = getTangent(i_d).cross(Vector.Z).normalized();
                Vector P3 = getPoint(i_d).add(N3.scale(-2*laneWidth));
                Vector N4 = getTangent(i_d + 0.01).cross(Vector.Z).normalized();
                Vector P4 = getPoint(i_d + 0.01).add(N4.scale(-2*laneWidth));
                gl.glTexCoord2d(0, 0);
                gl.glNormal3d(-N3.x, -N3.y, -N3.z);
                gl.glVertex3d(P3.x, P3.y, P3.z);
                gl.glTexCoord2d(0, 1);
                gl.glVertex3d(P3.x, P3.y, P3.z - 1);
                gl.glTexCoord2d(1, 0);
                gl.glNormal3d(-N4.x, -N4.y, -N4.z);
                gl.glVertex3d(P4.x, P4.y, P4.z);
                gl.glTexCoord2d(1, 1);
                gl.glVertex3d(P4.x, P4.y, P4.z - 1);
            gl.glEnd();
        }
 
=======
        /** Draw surface of the track at z=1*/
        gl.glBegin(GL_TRIANGLE_STRIP);
        gl.glColor3f(255, 0, 0);   
        for (int i=0; i<=N; i++){
            Vector P = getPoint(tmin+i*dt);
            Vector Normal = new Vector(0,0,1);
            Vector Tangent = getTangent(tmin+i*dt);
            Vector Bitangent = Normal.cross(Tangent);
            gl.glVertex3d(P.x+1*laneWidth*Bitangent.x,P.y+1*laneWidth*Bitangent.y,1);                
            gl.glVertex3d(P.x-3*laneWidth*Bitangent.x,P.y-3*laneWidth*Bitangent.y,1);                
        }
        gl.glEnd();
        gl.glFlush();
        
        /** Draw the surface of track at z=-1. */
        gl.glBegin(GL_TRIANGLE_STRIP);
        for (int i=0; i<=N; i++){
            Vector P = getPoint(tmin+i*dt);
            Vector Normal = new Vector(0,0,1);
            Vector Tangent = getTangent(tmin+i*dt);
            Vector Bitangent = Normal.cross(Tangent);
            gl.glVertex3d(P.x+1*laneWidth*Bitangent.x,P.y+1*laneWidth*Bitangent.y,-1);                
            gl.glVertex3d(P.x-3*laneWidth*Bitangent.x,P.y-3*laneWidth*Bitangent.y,-1);  
        }
        gl.glEnd();
        gl.glFlush();
    }
    
    public void drawBrick(GL2 gl, GLU glu, GLUT glut, int tmin, double dt, int N, int Ntracks, int Ncorners){
                /** Draw the surface of the inner side of track. */
        gl.glBegin(GL_TRIANGLE_STRIP);
        for (int i=0; i<=N; i++){
            Vector P = getPoint(tmin+i*dt);
            Vector Normal = new Vector(0,0,1);
            Vector Tangent = getTangent(tmin+i*dt);
            Vector Bitangent = Normal.cross(Tangent);
            gl.glVertex3d(P.x+1*laneWidth*Bitangent.x,P.y+1*laneWidth*Bitangent.y,1);                
            gl.glVertex3d(P.x+1*laneWidth*Bitangent.x,P.y+1*laneWidth*Bitangent.y,-1); 
        }
        gl.glEnd();
        gl.glFlush();
        
        /** Draw the surface of the outer side of the track. */
        gl.glBegin(GL_TRIANGLE_STRIP);
        for (int i=0; i<=N; i++){
            Vector P = getPoint(tmin+i*dt);
            Vector Normal = new Vector(0,0,1);
            Vector Tangent = getTangent(tmin+i*dt);
            Vector Bitangent = Normal.cross(Tangent);
            gl.glVertex3d(P.x-3*laneWidth*Bitangent.x,P.y-3*laneWidth*Bitangent.y,1);                
            gl.glVertex3d(P.x-3*laneWidth*Bitangent.x,P.y-3*laneWidth*Bitangent.y,-1);  
        }
        gl.glEnd();
        gl.glFlush();
>>>>>>> parent of 4c14e1b... track texture added including shading
    }
    
    /**
     * Returns the center of a lane at 0 <= t < 1.
     * Use this method to find the position of a robot on the track.
     */
    public Vector getLanePoint(int lane, double t){
        Vector Pcenter = getPoint(t);
        Vector Normal = new Vector(0,0,1);
        Vector Tangent = getTangent(t);
        Vector Bitangent = Normal.cross(Tangent);
        

        Vector P = new Vector(Pcenter.x+(lane-2.5)*laneWidth*Bitangent.x,Pcenter.y+(lane-2.5)*laneWidth*Bitangent.y,1);                
        //System.out.println("P="+P);
        return P;

    }
    
    /**
     * Returns the tangent of a lane at 0 <= t < 1.
     * Use this method to find the orientation of a robot on the track.
     */
    public Vector getLaneTangent(int lane, double t){

        Vector Tangent = getTangent(t);
        //System.out.println("Tangent="+t);   //lane needs to be included in the function probably.
    
        return Tangent;

    }
   
    public Vector getCubicBezierPnt(double t, Vector P0, Vector P1, Vector P2, Vector P3){
        
        double Px=Math.pow((1-t),3)*P0.x+3*t*Math.pow((1-t),2)*P1.x+3*Math.pow(t,2)*(1-t)*P2.x+Math.pow(t,3)*P3.x;
        double Py=Math.pow((1-t),3)*P0.y+3*t*Math.pow((1-t),2)*P1.y+3*Math.pow(t,2)*(1-t)*P2.y+Math.pow(t,3)*P3.y;
        double Pz=Math.pow((1-t),3)*P0.z+3*t*Math.pow((1-t),2)*P1.z+3*Math.pow(t,2)*(1-t)*P2.z+Math.pow(t,3)*P3.z;
        
        Vector P = new Vector(Px,Py,Pz);
        return P;
    }
    
    public Vector getCubicBezierTng(double t, Vector P0, Vector P1, Vector P2, Vector P3){

        double Tangentx = -3*P0.x*Math.pow((1-t), 2)+3*P1.x*Math.pow((1-t),2)-6*P1.x*t*(1-t)-3*P2.x*Math.pow(t,2)+6*P2.x*t*(1-t)+3*P3.x*Math.pow(t,2);
        double Tangenty = -3*P0.y*Math.pow((1-t), 2)+3*P1.y*Math.pow((1-t),2)-6*P1.y*t*(1-t)-3*P2.y*Math.pow(t,2)+6*P2.y*t*(1-t)+3*P3.y*Math.pow(t,2);
        double Tangentz = -3*P0.z*Math.pow((1-t), 2)+3*P1.z*Math.pow((1-t),2)-6*P1.z*t*(1-t)-3*P2.z*Math.pow(t,2)+6*P2.z*t*(1-t)+3*P3.z*Math.pow(t,2);
        
        Vector Tangent = new Vector(Tangentx,Tangenty,Tangentz);
        
        return Tangent;
    }
    
    
    
    // Returns a point on the test track at 0 <= t < 1.
    protected abstract Vector getPoint(double t);

    // Returns a tangent on the test track at 0 <= t < 1.
    protected abstract Vector getTangent(double t);
}
