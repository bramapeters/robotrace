
package robotrace;

/**
 * Implementation of RaceTrack, creating a track from a parametric formula
 */
public class ParametricTrack extends RaceTrack {
    
    protected Vector getPoint(double t) {
        Vector P= new Vector(10*Math.cos(2*Math.PI*t),14*Math.sin(2*Math.PI*t),1);
        return P;

    }

    protected Vector getTangent(double t) {
        Vector Tangent = new Vector(-20*Math.PI*Math.sin(2*Math.PI*t),28*Math.PI*Math.cos(2*Math.PI*t),0);
        double TangentMagnitude = Math.sqrt(Math.pow(Tangent.x,2)+Math.pow(Tangent.y,2));
        
        double TangentNormx = Tangent.x/TangentMagnitude;
        double TangentNormy = Tangent.y/TangentMagnitude;
        
        Vector TangentNorm = new Vector(TangentNormx,TangentNormy,0);

        return TangentNorm;

    }
    
}
