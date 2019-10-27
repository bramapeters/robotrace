// simple vertex shader
varying vec4 Position;

float z (float x, float y){
        float z = 0.6 * cos(0.3 * x + 0.2 * y) + 0.4 * cos(x - 0.5 * y);
        float z_mountain_x = 5 * cos (0.3*x);
        float z_mountain_yr = 5 * cos (0.3*(y));
        float z_mountain_yl = 5*cos(0.3*(-y - 2));
        if (z_mountain_x  > 0 && (x < 10 || x > 30)) {
            z = z + z_mountain_x;
        }
        if (z_mountain_yr > 0 && (y > 30)){
            z = z + z_mountain_yr;
        }
        if (z_mountain_yl > 0 && (y < 10)){
            z = z + z_mountain_yl;
        }
        return z;
    }

void main()
{
    Position = gl_Vertex;
    Position.z = z(Position.x, Position.y);
    gl_Position    = gl_ModelViewProjectionMatrix * Position;      // model view transform
    gl_FrontColor = gl_Color;
}