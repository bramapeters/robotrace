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
    public void draw(GL2 gl, GLU glu, GLUT glut, GlobalState gs) {


        // If gs.trackNr = 0, the parametrics track is drawn.
        if(gs.trackNr==0){
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
        } else if (gs.trackNr==1){
            System.out.println("trackNr="+gs.trackNr);
            // NOTE: THIS IS NOT THE BEZIER TRACK!
            // It's just for testing to see if the track is actually being switched.
            // If it is being switched, only track-lines show up.
            int N=20;
            int Ntracks=1;
            int tmin=0;        
            double dt = Math.pow(N, -1);

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
                gl.glFlush();
            }
        }
    }
    
    public void drawTrack(GL2 gl, GLU glu, GLUT glut, int tmin, double dt, int N, int Ntracks, int Ncorners){
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
        System.out.println("P="+P);
        return P;
    }
    
    public Vector getCubicBezierTng(double t, Vector P0, Vector P1, Vector P2, Vector P3){
        // Calculates the tangent vector at the end of the Bezier curve.
        // So at P3.
        double Tangentx = 3*(P3.x-P2.x);
        double Tangenty = 3*(P3.y-P2.y);
        double Tangentz = 3*(P3.z-P2.z);
        
        Vector Tangent = new Vector(Tangentx,Tangenty,Tangentz);
        
        return Tangent;
    }
    
    
    
    // Returns a point on the test track at 0 <= t < 1.
    protected abstract Vector getPoint(double t);

    // Returns a tangent on the test track at 0 <= t < 1.
    protected abstract Vector getTangent(double t);
}
