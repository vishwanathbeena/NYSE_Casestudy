package NYSEcasestudyAvgeStockVolume;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.nysecasestudy.inputformats.NosplitTextInputFormat;
import com.nysecasestudy.keyvalues.LongPair;
import com.nysecasestudy.keyvalues.TextPair;

public class NYSECasestudyDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{
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
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true)?0:1);
	}

}
