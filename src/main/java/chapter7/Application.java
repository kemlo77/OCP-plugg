package chapter7;

import static chapter7.ThreadColor.ANSI_BLUE;
import static chapter7.ThreadColor.ANSI_GREEN;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Application {

  private static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(10);

  public static void main(String[] args) {
    new Producer(blockingQueue).start();
    new Consumer(blockingQueue).start();

  }

}


class Consumer extends Thread {

  private BlockingQueue<String> blockingQueue;

  public Consumer(BlockingQueue<String> blockingQueue) {
    super("Consumer");
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      //System.out.println(Thread.currentThread().getName());
      try {
        Thread.sleep(500);
        System.out.println(ANSI_GREEN+"\t" + blockingQueue.size() + " items in blocking queue");
        System.out.println(ANSI_GREEN+"\tConsumer polling " + blockingQueue.poll() + " from blocking queue");
        System.out.println(ANSI_GREEN+"\t" + blockingQueue.size() + " items in blocking queue");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}


class Producer extends Thread {

  private BlockingQueue<String> blockingQueue;

  public Producer(BlockingQueue<String> blockingQueue) {
    super("Producer");
    this.blockingQueue = blockingQueue;
  }

  @Override
  public void run() {
    for (char i = 'A'; i < 'K'; i++) {
      //System.out.println(Thread.currentThread().getName());
      try {
        System.out.println(ANSI_BLUE+"adding " + i + " to the blocking queue");
        blockingQueue.offer("" + i);
        Thread.sleep(400);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}