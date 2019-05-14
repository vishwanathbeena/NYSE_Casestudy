package NYSEcasestudyAvgeStockVolume;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.nysecasestudy.keyvalues.LongPair;
import com.nysecasestudy.keyvalues.TextPair;
import com.nysecasestudy.parsers.CompanyParser;

public class NYSECasestudyReducerUsingDistributedCache extends Reducer<TextPair,LongPair,TextPair,LongWritable> {
	HashMap<String,String> stockAndCompanyDetails = new HashMap<String, String>();
	private Long totalVolume;
	private Long totalDays;
	private Long averageVolume;
	private CompanyParser companyParser = null;
	public void setup(Context context) throws IOException{
		readFile();
	}

	public void readFile() throws IOException {
		// TODO Auto-generated method stub
		File companyDataFile = new File("companylist_noheader.csv");
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(companyDataFile)));
			String line;
			while ((line = in.readLine()) != null) {
				companyParser = new CompanyParser(line);
				stockAndCompanyDetails.put(companyParser.getStockTicker(), companyParser.getName());
			}

		} finally {
			IOUtils.closeStream(in);
		}
		
	}
	public void reduce(TextPair keys,Iterable<LongPair> values,Context context) throws IOException, InterruptedException{
		totalVolume=new Long(0);
		totalDays=new Long(0);
		String ticker ;
		String stockName;
		TextPair key_new = new TextPair();
		Text date = new Text();
		for(LongPair var:values){
			totalVolume+=var.getFirst().get();
			totalDays+=var.getSecond().get();
		}
		averageVolume=totalVolume/totalDays;
		ticker = keys.getFirst().toString();
		date = keys.getSecond();
		stockName = stockAndCompanyDetails.get(ticker);
		key_new.setFirst(new Text(stockName));
		key_new.setSecond(date);
		context.write(key_new, new LongWritable(averageVolume));
		
	}

}
