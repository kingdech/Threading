import java.util.ArrayList;


public class ArrayConsumer extends AbstractConsumer implements Runnable {

	String consumerName;
	ArrayList<Integer> buffer;
		
	public ArrayConsumer(String name, ArrayList<Integer> buffer2) {
		this.consumerName = name;
		this.buffer = buffer2;     
	}
	
	@Override
	public void run() {
		System.out.println("Starting " + consumerName);
		
		while(true){
			synchronized(buffer){
				if (buffer.size() > 0){
					consume();
				}
				else{
					try {
						buffer.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	void consume() {
		Integer item = buffer.remove(buffer.size() - 1);
		System.out.println(consumerName + " consumed " + item.toString());
	}

}
