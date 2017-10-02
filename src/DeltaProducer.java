import java.util.ArrayList;


public class DeltaProducer extends AbstractProducer implements Runnable {

	private String producerName;
	private ArrayList<Integer> buffer;
	
	public DeltaProducer(String ProducerNumber, ArrayList<Integer> buffer){
		this.producerName = ProducerNumber;
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		System.out.println("Starting " + producerName);
		while(true){
			synchronized(buffer){
				produce();
			}
		}
	}

	@Override
	void produce() {
		// TODO Auto-generated method stub
		buffer.add(123456789);
		buffer.notify();
	}
}
