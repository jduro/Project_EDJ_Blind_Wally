package edj.blindwally.main;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

import edj.blindwally.data.Map;
import edj.blindwally.data.Position;
import edj.blindwally.data.Settings;



public class Tutorial {

	Map scenario;
	Settings settings;
	
	Tutorial(Map scenario, Settings settings)
	{
		this.scenario=scenario;
		this.settings=settings;
	}
	
	/** Buffers hold sound data. */
	  IntBuffer buffer = BufferUtils.createIntBuffer(1);
	 
	  /** Sources are points emitting sound. */
	  IntBuffer source = BufferUtils.createIntBuffer(1);
	 
	  /** Position of the source sound. */
	  FloatBuffer sourcePos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
	 
	  /** Velocity of the source sound. */
	  FloatBuffer sourceVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
	 
	  /** Position of the listener. */
	  FloatBuffer listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
	 
	  /** Velocity of the listener. */
	  FloatBuffer listenerVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 2.5f, 0.0f, -2.5f }).rewind();
	 
	  /** Orientation of the listener. (first 3 elements are "at", second 3 are "up") */
	  FloatBuffer listenerOri = 
	       (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f }).rewind();
	 
	  /**
	  * boolean LoadALData()
	  *
	  *  This function will load our sample data from the disk using the Alut
	  *  utility and send the data into OpenAL as a buffer. A source is then
	  *  also created to play that buffer.
	  */
	  int loadALData() {
	    // Load wav data into a buffer.
	    AL10.alGenBuffers(buffer);
	 
	    if(AL10.alGetError() != AL10.AL_NO_ERROR)
	      return AL10.AL_FALSE;
	 
	    //Loads the wave file from your file system
	    java.io.FileInputStream fin = null;
	    java.io.BufferedInputStream bin= null;
	    try {
	      fin = new java.io.FileInputStream("C:\\Users\\jduro\\Documents\\My Dropbox\\EDJ\\Crowd of kids playing in a schoolyard at a distance.wav");
	      bin = new BufferedInputStream(fin);
	    } catch (java.io.FileNotFoundException ex) {
	      System.out.println("Datei nicht gefunden.");
	      ex.printStackTrace();
	      return AL10.AL_FALSE;
	    }
	  System.out.println("Datei geöffnet.");
	    WaveData waveFile = WaveData.create(bin);
	    try {
	      fin.close();
	    } catch (java.io.IOException ex) {
	    }
	    /*
	    //Loads the wave file from this class's package in your classpath
	    WaveData waveFile = WaveData.create("Footsteps.wav");*/
	 
	    AL10.alBufferData(buffer.get(0), waveFile.format, waveFile.data, waveFile.samplerate);
	    waveFile.dispose();
	 
	    // Bind the buffer with the source.
	    AL10.alGenSources(source);
	 
	    if (AL10.alGetError() != AL10.AL_NO_ERROR)
	      return AL10.AL_FALSE;
	 
	    AL10.alSourcei(source.get(0), AL10.AL_BUFFER,   buffer.get(0) );
	    AL10.alSourcef(source.get(0), AL10.AL_PITCH,    1.0f          );
	    AL10.alSourcef(source.get(0), AL10.AL_GAIN,     1.0f          );
	    AL10.alSource (source.get(0), AL10.AL_POSITION, sourcePos     );
	    AL10.alSource (source.get(0), AL10.AL_VELOCITY, sourceVel     );
	    AL10.alSourcei(source.get(0), AL10.AL_LOOPING,  AL10.AL_TRUE  );
	 
	    // Do another error check and return.
	    if (AL10.alGetError() == AL10.AL_NO_ERROR)
	      return AL10.AL_TRUE;
	 
	    return AL10.AL_FALSE;
	  }
	 
	  /**
	   * void setListenerValues()
	   *
	   *  We already defined certain values for the Listener, but we need
	   *  to tell OpenAL to use that data. This function does just that.
	   */
	  void setListenerValues() {
	    AL10.alListener(AL10.AL_POSITION,    listenerPos);
	    AL10.alListener(AL10.AL_VELOCITY,    listenerVel);
	    AL10.alListener(AL10.AL_ORIENTATION, listenerOri);
	  }
	 
	  /**
	   * void killALData()
	   *
	   *  We have allocated memory for our buffers and sources which needs
	   *  to be returned to the system. This function frees that memory.
	   */
	  void killALData() {
	    AL10.alDeleteSources(source);
	    AL10.alDeleteBuffers(buffer);
	  }

	  public void execute() {
		  System.out.println("Press ENTER to exit");
	    // Initialize OpenAL and clear the error bit.
	    try{
	      AL.create();
	    } catch (LWJGLException le) {
	      le.printStackTrace();
	      return;
	    }

	    AL10.alGetError();
	 
	    // Load the wav data.
	    if(loadALData() == AL10.AL_FALSE) {
	      System.out.println("Error loading data.");
	      return;
	    }

	    setListenerValues();

	    AL10.alSourcePlay(source.get(0));
	  }
	  /**
	   *  Check for keyboard hit
	   */  
	  private boolean kbhit() {
		  while(Keyboard.next()){
			  System.out.println(Keyboard.getEventKey());
			  if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				  
				  listenerPos.put(0, listenerPos.get(0) + listenerVel.get(0));				
				  setListenerValues();
//				  AL10.alSourcePlay(source.get(0));
				  
				  
//				  sourcePos.put(0, sourcePos.get(0)+sourceVel.get(0));
//				  sourcePos.put(0, sourcePos.get(0)-sourceVel.get(0));
//				  AL10.alSource(source.get(0), AL10.AL_POSITION, sourcePos);
				  
			  }else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				  
				  listenerPos.put(0, listenerPos.get(0) - listenerVel.get(0));
				  setListenerValues();
				  AL10.alSourcePlay(source.get(0));
			  }else if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
				  
				  listenerPos.put(2, listenerPos.get(2) + listenerVel.get(2));
				  setListenerValues();
				  AL10.alSourcePlay(source.get(0));
			  }else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
				  
				  listenerPos.put(2, listenerPos.get(2) - listenerVel.get(2));
				  setListenerValues();
				  AL10.alSourcePlay(source.get(0));
			  }
			  
			  
			  System.out.println(" eu("+listenerPos.get(0)+","+listenerPos.get(1)+","+listenerPos.get(2)+")");
		  }
		  return true;
	  }
	  
	  public void update(){
		
		kbhit();
	    
		
	    
	  }
	  
	  public void destroy(){
		  killALData();
		  AL.destroy();
	  }
}
