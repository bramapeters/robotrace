// simple vertex shader
varying vec3 N;
varying vec3 P;

void main()
{
	N = normalize(gl_NormalMatrix * gl_Normal);
	vec4 Position = gl_ModelViewMatrix * gl_Vertex;
	P = Position.xyz/Position.w;
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}