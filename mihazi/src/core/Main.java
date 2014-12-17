package core;


public class Main {

	public static void main(String[] args){
			
		//create the field and the engine
		Field field = new Field();
		Engine engine = new Engine(field);
		
		
		Frame frame = new Frame(engine);
		engine.setFrame(frame);
			
	}
	
}
