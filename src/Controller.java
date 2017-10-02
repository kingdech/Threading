import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class Controller {

	private static final Controller INSTANCE = new Controller();
	
	private Controller(){}
	
	public static Controller getInstance(){
		return INSTANCE;
	}
	
	public void createWorkers(){
		ArrayList<Integer> buffer = new ArrayList<Integer>();
		
		for(int i = 0; i < 10; i++){
			AbstractProducer p = createProducer("P" + i, buffer);
			new Thread(p).start();
		}
		
		for(int i = 0; i < 10; i++){
			AbstractConsumer p = createConsumer("C" + i, buffer);
			new Thread(p).start();
		}
		
		new Thread(new DeltaProducer("DeltaProducer", buffer)).start();
		
		new Thread(new DeltaConsumer("DeltaConsumer", buffer)).start();
		
		LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(10);
		
		//for(int i = 0; i < 10; i++){
		//	AbstractProducer p = createProducer("P" + i, queue);
		//	new Thread(p).start();
		//}
		
		//for(int i = 0; i < 10; i++){
		//	AbstractConsumer p = createConsumer("C" + i, queue);
		//	new Thread(p).start();
		//}
	}
	
	private static AbstractProducer createProducer(String name, AbstractCollection<Integer> collection){
		if(collection instanceof LinkedBlockingQueue){
			return new QueueProducer(name, (LinkedBlockingQueue<Integer>)collection);
		}
		else{
			return new ArrayProducer(name, (ArrayList<Integer>)collection);
		}
	}
	
	private static AbstractConsumer createConsumer(String name, AbstractCollection<Integer> collection){
		if(collection instanceof LinkedBlockingQueue){
			return new QueueConsumer(name, (LinkedBlockingQueue<Integer>)collection);
		}
		else{
			return new ArrayConsumer(name, (ArrayList<Integer>)collection);
		}
	}
	
	public static void main(String[] args){
		Controller.getInstance().createWorkers();
	}
}
