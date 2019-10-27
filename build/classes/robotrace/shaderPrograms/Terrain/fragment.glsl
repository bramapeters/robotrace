// Simple fragment shader
varying vec4 Position;

void main()
{
    vec4 C;
    vec4 water = vec4(0,0,1,1);
    vec4 sand = vec4(1,1,0,1);
    vec4 grass = vec4(0,1,0,1);
    vec4 rock = vec4(0.8, 0.8, 0.8,1);
    vec4 snow = vec4(0.95, 0.95, 0.95, 1);

    if(Position.z < 0.0){
        C = water;
    }else if(Position.z < 0.5){
        C = sand;
    }else if (Position.z < 2){
        C = grass;
    } else if (Position.z < 3){
        C = rock;
    } else {
        C = snow;
    }

    gl_FragColor = C;
}
