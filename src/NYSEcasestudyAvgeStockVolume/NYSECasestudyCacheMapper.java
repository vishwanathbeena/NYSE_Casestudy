package NYSEcasestudyAvgeStockVolume;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.nysecasestudy.keyvalues.LongPair;
import com.nysecasestudy.keyvalues.TextPair;
import com.nysecasestudy.parsers.NYSEDataParser;

public class NYSECasestudyCacheMapper extends Mapper<LongWritable,Text,Text,LongWritable> {
	private HashSet<String> tickerNameSet = new HashSet<String>();
	private TextPair mapoutputKey = new TextPair();
	private LongPair mapoutputValue = new LongPair();
	NYSEDataParser parser = new NYSEDataParser();
	@Override
	public void setup(Context context){
		try{
			URI[] uri=DistributedCache.getCacheFiles(context.getConfiguration());
			Path cacheFile = new Path(uri[0]);
			context.write(new Text(cacheFile.toString()), new LongWritable(1));
			if(cacheFile!=null){
					readFile(cacheFile,context);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void readFile(Path path,Context context) throws IOException, InterruptedException{
		String line=null;
		context.write(new Text("Hello"), new LongWritable(1));
		try{
			BufferedReader br = new BufferedReader(new FileReader(path.toString()));
			while((line=br.readLine())!=null){
				tickerNameSet.add(line);
				context.write(new Text(line), new LongWritable(1));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException{
		parser.parse(value.toString());
		String stockName=parser.getStockTicker();
		//context.write(new Text(stockName), new LongWritable(tickerNameSet.size()));
		/*if(tickerNameSet.contains(stockName)){
			mapoutputKey.setFirst(new Text(stockName));
			mapoutputKey.setSecond(new Text(parser.getTradeYearMonth()));
			mapoutputValue.setFirst(new LongWritable(parser.getVolume()));
			mapoutputValue.setSecond(new LongWritable(1));
			context.write(mapoutputKey,mapoutputValue);
		}*/
			
		
		
	}
}


