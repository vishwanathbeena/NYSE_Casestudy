package NYSEcasestudyAvgeStockVolume;

import org.apache.hadoop.mapreduce.Partitioner;

import com.nysecasestudy.keyvalues.TextPair;
import com.nysecasestudy.keyvalues.LongPair;

public class NYSECasestudyPartitioner extends Partitioner<TextPair,LongPair> {

	@Override
	public int getPartition(TextPair key, LongPair value, int arg2) {
		// TODO Auto-generated method stub
		String[] year=key.getSecond().toString().split("-");
		if(Integer.parseInt(year[0])==2013){
			return 0;
		}else		
			return 1;
	}

}
