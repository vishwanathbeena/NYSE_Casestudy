package NYSEcasestudyAvgeStockVolume;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

import com.nysecasestudy.keyvalues.LongPair;
import com.nysecasestudy.keyvalues.TextPair;

public class NYSECasestudyReducer extends Reducer<TextPair,LongPair,TextPair,LongWritable>{
	private Long totalVolume;
	private Long totalDays;
	private Long averageVolume;
	public void reduce(TextPair key,Iterable<LongPair> value,Context context) throws IOException, InterruptedException{
		totalVolume=new Long(0);
		totalDays=new Long(0);
		for(LongPair var:value){
			totalVolume+=var.getFirst().get();
			totalDays+=var.getSecond().get();
		}
		averageVolume=totalVolume/totalDays;
		context.write(key, new LongWritable(averageVolume));
	}

}
