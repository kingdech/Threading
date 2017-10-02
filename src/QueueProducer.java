import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueProducer extends AbstractProducer implements Runnable {

	String producerName;
	LinkedBlockingQueue<Integer> buffer;
	
	Random randomGenerator;
	
	public QueueProducer(String name, LinkedBlockingQueue<Integer> buffer2) {
		this.producerName = name;
		this.buffer = buffer2;
		randomGenerator = new Random();	      
	}	
	
	@Override
	public void run() {
		System.out.println("Starting " + producerName);		

		while(true){
			produce();
		}
	}


	@Override
	void produce() {
		while (buffer.size() < 10) {
			Integer randomInt = randomGenerator.nextInt(100);
			buffer.add(randomInt);
		}
	}
}
