// simple fragment shader
 #version 120

varying vec3 N;
varying vec3 P;
varying vec3 L;
uniform sampler2D track;

vec4 shading(vec3 P, vec3 N, vec3 L, gl_MaterialParameters mat, gl_LightSourceParameters light){
	vec4 shading = vec4(0,0,0,1);
	vec3 position = P;
	vec3 norm = normalize(N);
	vec3 li = normalize(L);
	vec3 E = normalize(-P);
	vec3 V = reflect(-L, N);

	shading += vec4(1,1,1,1) * light.ambient;                       // ambient lighting component
	float intensity = max(dot(norm, li), 0.0);                      // diffuse lighting component
	shading += vec4(1,1,1,1) * light.diffuse * intensity;
	float specular = max(dot(V, E),0.0);                            // specular lighting component
	shading += vec4(1,1,1,1) * light.specular * pow(specular, 5);
		
	return shading;
}

void main(){
	gl_MaterialParameters mat = gl_FrontMaterial;
	gl_LightSourceParameters li = gl_LightSource[0];
	gl_FragColor = texture2D(track, gl_TexCoord[0].st) * shading(P, N, L, mat, li);
}