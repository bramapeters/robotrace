package robotrace;

import com.jogamp.opengl.GLException;
import java.io.IOException;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

/**
 *
 * @author Niels Rood
 */
public class Textures {
    
    public static Texture head = null;
    public static Texture torso = null;
    public static Texture track;
    public static Texture brick = null;
        
    public static void loadTextures() {
        head = loadTexture("textures/head.jpg");
        torso = loadTexture("textures/torso.jpg");
        track = loadTexture("textures/track.jpg");
        brick = loadTexture("textures/brick.jpg");
    }
    
    /**
    * Try to load a texture from the given file. The file
    * should be located in the same folder as RobotRace.java.
    */
    private static Texture loadTexture(String file) {
        Texture result = null;

        try {
            // Try to load from local folder.
            result = TextureIO.newTexture(Textures.class.getResource(file), false, null);
        } catch(GLException | IOException e1) {
            e1.printStackTrace();
        }       
        return result;
    }
}
