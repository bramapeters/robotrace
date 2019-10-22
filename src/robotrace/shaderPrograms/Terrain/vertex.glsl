// simple vertex shader
varying vec4 Position;

float z (float x, float y){
        float z = 0.6 * cos(0.3 * x + 0.2 * y) + 0.4 * cos(x - 0.5 * y);
        return z;
    }

void main()
{
    Position = gl_Vertex;
    Position.z = z(Position.x, Position.y);
    gl_Position    = gl_ModelViewProjectionMatrix * Position;      // model view transform
    gl_FrontColor = gl_Color;
}