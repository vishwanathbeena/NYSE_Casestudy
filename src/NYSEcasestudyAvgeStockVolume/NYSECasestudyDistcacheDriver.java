package NYSEcasestudyAvgeStockVolume;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.nysecasestudy.inputformats.NosplitTextInputFormat;
import com.nysecasestudy.keyvalues.LongPair;
import com.nysecasestudy.keyvalues.TextPair;

public class NYSECasestudyDistcacheDriver extends Configured implements Tool{

	@Override
	public int run(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
		Configuration conf=new Configuration();
		Job job=new Job(conf);
		job.setJarByClass(NYSECasestudyDriver.class);
		job.setMapperClass(NYSECasestudyMapper.class);
		job.setCombinerClass(NYSECasestudyCombiner.class);
		job.setReducerClass(NYSECasestudyReducerUsingDistributedCache.class);
		job.setPartitionerClass(NYSECasestudyPartitioner.class);
		job.setNumReduceTasks(2);
		job.setMapOutputKeyClass(TextPair.class);
		job.setMapOutputValueClass(LongPair.class);
		job.setOutputKeyClass(TextPair.class);
		job.setOutputValueClass(LongWritable.class);
		job.setInputFormatClass(NosplitTextInputFormat.class);
		FileInputFormat.addInputPath(job, new Path(arg0[0]));
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
		return job.waitForCompletion(true) ? 0 : 1;
	}
	public static void main(String[] args) throws Exception{
		System.exit(ToolRunner.run(new NYSECasestudyDistcacheDriver(), args));
		
	}

}
