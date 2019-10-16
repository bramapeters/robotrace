package robotrace;

/**
 * Implementation of a camera with a position and orientation. 
 */
class Camera {

    /** The position of the camera. */
    public Vector eye = new Vector(3f, 6f, 5f);

    /** The point to which the camera is looking. */
    public Vector center = Vector.O;

    /** The up vector. */
    public Vector up = Vector.Z;

    /**
     * Updates the camera viewpoint and direction based on the
     * selected camera mode.
     */
    public void update(GlobalState gs, Robot focus) {

        switch (gs.camMode) {
            
            // First person mode    
            case 1:
                setFirstPersonMode(gs, focus);
                break;
                
            // Default mode    
            default:
                setDefaultMode(gs);
        }
    }

    /**
     * Computes eye, center, and up, based on the camera's default mode.
     */
    private void setDefaultMode(GlobalState gs) {
        Vector E = new Vector(0,0,0);
        E=E.add(gs.cnt);
        
        float x = (float)Math.cos(gs.theta) * (float)Math.cos(gs.phi) * gs.vDist;
        float y = (float)Math.sin(gs.theta) * (float)Math.cos(gs.phi) * gs.vDist;
        float z = (float)Math.sin(gs.phi) * gs.vDist;
        
        E.x = E.x + x;
        E.y = E.y + y;
        E.z = E.z + z;
        
        eye = E;
        center = gs.cnt;
        /*
        
        Vector E = new Vector(0,0,0);
        E.z=gs.vDist*Math.cos(gs.phi);
        double xyDist = gs.vDist*Math.sin(gs.phi);    //Magnitude of eye vector on XY-plane
        E.x=xyDist*Math.cos(gs.theta);
        E.y=xyDist*Math.sin(gs.theta);
        
        Vector U = new Vector(0,0,0);
        if(E.z>0){
            U.y=-E.y;
            U.z=E.z;
            U.x=-E.x;
        } else {
                       
        }

        System.out.println("U="+U);
        System.out.println("E="+E);
        
        eye = E;
        center = C;
        up = U;  
        */
    }

    /**
     * Computes eye, center, and up, based on the first person mode.
     * The camera should view from the perspective of the robot.
     */
    private void setFirstPersonMode(GlobalState gs, Robot focus) {

    }
}
