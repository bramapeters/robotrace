// simple vertex shader
varying vec3 N;
varying vec3 P;
varying vec3 L;

void main()
{	
	vec4 Position = gl_ModelViewMatrix * gl_Vertex;
	P = Position.xyz/Position.w;

	N = normalize(gl_NormalMatrix * gl_Normal);
	
	gl_LightSourceParameters li = gl_LightSource[0];
	vec3 LightDir = li.position.xyz;
	L = normalize(LightDir);
	if(li.position.w != 0.0){
		L = normalize(LightDir - P);
	}

        gl_TexCoord[0] = gl_MultiTexCoord0;
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}
