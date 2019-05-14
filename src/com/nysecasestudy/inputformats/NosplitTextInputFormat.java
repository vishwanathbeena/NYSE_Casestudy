package com.nysecasestudy.inputformats;


import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;


public class NosplitTextInputFormat extends TextInputFormat{

	@Override
	protected boolean isSplitable(JobContext context, Path file) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
	


