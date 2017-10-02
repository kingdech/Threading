import java.util.ArrayList;
import java.util.Iterator;


public class DeltaConsumer extends AbstractConsumer implements Runnable {

	private String name;
	private ArrayList<Integer> buffer;
	
	public DeltaConsumer(String name, ArrayList<Integer> buffer){
		this.name = name;
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			synchronized(buffer){
				consume();
			}
		}
	}

	@Override
	void consume() {
		Iterator<Integer> looper = buffer.iterator();
		while(looper.hasNext()){
			if(looper.next() == 123456789){
				looper.remove();
				System.out.println("Got Delta");
			}
		}
	}
}
