package NYSEcasestudyAvgeStockVolume;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

import com.nysecasestudy.keyvalues.LongPair;
import com.nysecasestudy.keyvalues.TextPair;

public class NYSECasestudyCombiner extends Reducer<TextPair,LongPair,TextPair,LongPair>{
	private Long totalVolume;
	private Long totalDays;
	private LongPair combinerOutput = new LongPair();
	public void reduce(TextPair key,Iterable<LongPair> value,Context context) throws IOException, InterruptedException{
		totalVolume=new Long(0);
		totalDays=new Long(0);
		for(LongPair var:value){
			totalVolume+=var.getFirst().get();
			totalDays+=var.getSecond().get();
		}
		combinerOutput.setFirst(new LongWritable(totalVolume));
		combinerOutput.setSecond(new LongWritable(totalDays));
		context.write(key, combinerOutput);
	}

}
