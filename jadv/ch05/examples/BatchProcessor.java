package examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;

public class BatchProcessor implements Runnable, Serializable {
  private static final long serialVersionUID = 1L;
  private ArrayList<Job> jobs;
  private transient ListIterator<Job> iterator;

  public BatchProcessor(ArrayList<Job> jobs) {
    this.jobs = jobs;
    iterator = jobs.listIterator();
  }

  public void run() {
    while (iterator.hasNext() && !Thread.interrupted()) {
      Job job = iterator.next();
      job.runJob();
    }
  }

  public String toString() {
    StringBuilder result = new StringBuilder(super.toString());
    result.append(' ').append(jobs.size()).append(" jobs : ");
    if (iterator.hasNext()) {
      result.append("Next job: ").append(iterator.nextIndex());
    }
    else {
      result.append("Completed");
    }
    return result.toString();
  }

  private void readObject(ObjectInputStream in) throws IOException,
      ClassNotFoundException {
    in.defaultReadObject();
    // get a list iterator to start at the next job
    int firstJob = in.readInt();
    iterator = jobs.listIterator(firstJob);
  }

  private void writeObject(ObjectOutputStream out) throws IOException {
    out.defaultWriteObject();
    // store the index of the next job for use at deserialization
    out.writeInt(iterator.nextIndex());
  }

  // If there is an existing serialized BatchProcessor, then load it.
  // Otherwise, create a list of test jobs and run them through a new
  // batch processor
  public static void main(String args[]) {
    BatchProcessor processor = null;
    File serialFile = new File("batch_processor.ser");
    if (serialFile.canRead()) {

      try (ObjectInputStream in = new ObjectInputStream(
          new FileInputStream(serialFile))) {

        processor = (BatchProcessor) in.readObject();
        System.err.println("Found previous batch processor : "
            + processor);
      }
      catch (IOException ioex) {
        System.err.println("Error occurred reading " + serialFile
            + ": " + ioex);
        System.exit(1);
      }
      catch (ClassNotFoundException cnfex) {
        System.err.println("Class " + cnfex.getMessage()
            + " not found while unmarshalling " + serialFile);
        System.exit(2);
      }
    }

    if (processor == null) {
      System.err.println("Starting new batch processor");
      ArrayList<Job> joblist = new ArrayList<>();
      for (int i = 0; i < 200; i++) {
        joblist.add(new TestJob("Test" + i));
      }
      processor = new BatchProcessor(joblist);
    }

    // start the batch processor, then interrupt it after a short pause
    Thread t = new Thread(processor);
    t.start();
    try {
      Thread.sleep(2000);
    }
    catch (InterruptedException ignore) {
    }
    t.interrupt();

    // serialize the BatchProcessor
    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream(serialFile))) {

      out.writeObject(processor);
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
