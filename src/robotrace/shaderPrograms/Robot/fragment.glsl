// simple fragment shader
varying vec3 N;
varying vec3 P;

void main()
{
	vec4 result = vec4(0,0,0,1);
	vec3 normal = normalize(N);

	gl_MaterialParameters mat = gl_FrontMaterial;
	gl_LightSourceParameters light = gl_LightSource[0];
	
        vec3 li;
        if(light.position.w != 0.0){
            li = light.position.xyz/light.position.w;
            li = normalize(li - P);
        }else{
            li = normalize(light.position.xyz);
        }
        
	vec3 H = normalize(normalize(-P) + li);

        // diffuse lighting
	float intensity = max(dot(normal, li), 0.0);
	result += mat.diffuse * light.diffuse * intensity;
	
        // specular lighting
	float D = max(dot(normal, H), 0.0);
	result += mat.specular * light.specular * pow(D, mat.shininess);
	
	gl_FragColor = result;
}
