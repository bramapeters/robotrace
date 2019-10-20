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
        Vector E = new Vector(20,30,20);
        E=E.add(gs.cnt);
        
        float x = (float)Math.cos(gs.theta) * (float)Math.cos(gs.phi) * gs.vDist;
        float y = (float)Math.sin(gs.theta) * (float)Math.cos(gs.phi) * gs.vDist;
        float z = (float)Math.sin(gs.phi) * gs.vDist;
        
        E.x = E.x + x;
        E.y = E.y + y;
        E.z = E.z + z;
        
        eye = E;
        center = gs.cnt;
    }

    /**
     * Computes eye, center, and up, based on the first person mode.
     * The camera should view from the perspective of the robot.
     */
    private void setFirstPersonMode(GlobalState gs, Robot focus) {
        Vector Vx = new Vector(0,0,2);
        Vector Vy = new Vector(focus.direction.x, focus.direction.y, focus.direction.z);
        
        eye = focus.position.add(Vx).add(Vy);
        center = focus.position.add(focus.direction.scale(100)).add(Vx);
    }
}
