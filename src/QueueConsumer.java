import java.util.concurrent.LinkedBlockingQueue;


public class QueueConsumer extends AbstractConsumer implements Runnable {

	String consumerName;
	LinkedBlockingQueue<Integer> buffer;
		
	public QueueConsumer(String name, LinkedBlockingQueue<Integer> buffer2) {
		this.consumerName = name;
		this.buffer = buffer2;     
	}
	
	@Override
	public void run() {
		System.out.println("Starting " + consumerName);
		
		while(true){
			consume();
		}
	}

	@Override
	void consume() {
		Integer item;
		try {
			item = buffer.take();
			System.out.println(consumerName + " consumed " + item.toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}