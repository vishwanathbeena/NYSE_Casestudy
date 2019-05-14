package NYSEcasestudyAvgeStockVolume;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.nysecasestudy.keyvalues.LongPair;
import com.nysecasestudy.keyvalues.TextPair;
import com.nysecasestudy.parsers.NYSEDataParser;

public class NYSECasestudyMapper extends Mapper<LongWritable,Text,TextPair,LongPair>{
	NYSEDataParser parser = new NYSEDataParser();
	private TextPair mapOutputKey = new TextPair();
	private LongPair mapOutputValue = new LongPair();
	enum NOTRADECOUNTER{NOTRADEDAYS,TRADEDAYS};
	public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException{
		parser.parse(value.toString());
		mapOutputKey.setFirst(new Text(parser.getStockTicker()));
		mapOutputKey.setSecond(new Text(parser.getTradeYearMonth()));
		mapOutputValue.setFirst(new LongWritable(parser.getVolume()));
		if(parser.getVolume()== 0){
			context.getCounter(NOTRADECOUNTER.NOTRADEDAYS).increment(1);
		}else{
			context.getCounter(NOTRADECOUNTER.TRADEDAYS).increment(1);
		}
			
		mapOutputValue.setSecond(new LongWritable(1));
		context.write(mapOutputKey, mapOutputValue);
	}

}
