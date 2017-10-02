import java.util.ArrayList;
import java.util.Random;


public class ArrayProducer extends AbstractProducer implements Runnable {

	String producerName;
	ArrayList<Integer> buffer;
	
	Random randomGenerator;
	
	public ArrayProducer(String name, ArrayList<Integer> buffer2) {
		this.producerName = name;
		this.buffer = buffer2;
		randomGenerator = new Random();	      
	}
	
	@Override
	public void run() {
		System.out.println("Starting " + producerName);		

		while(true){
			synchronized(buffer){
				produce();
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	void produce() {
		while (buffer.size() < 10) {
			Integer randomInt = randomGenerator.nextInt(100);
			buffer.add(randomInt);
			buffer.notifyAll();
		}
	}
}
