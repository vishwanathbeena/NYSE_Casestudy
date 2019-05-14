package com.nysecasestudy.keyvalues;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.WritableComparable;

public class LongPair implements WritableComparable<LongPair>{

	private LongWritable first;
	private LongWritable second;
	
	public LongPair(){
		this.first = new LongWritable();
		this.second = new LongWritable();
	}
	public LongPair(Long first,Long second){
		this.first=new LongWritable(first);
		this.second=new LongWritable(second);
	}
	public LongPair(LongWritable first,LongWritable second){
		this.first=first;
		this.second=second;
	}
	
	public LongWritable getFirst() {
		return first;
	}
	public void setFirst(LongWritable first) {
		this.first = first;
	}
	public LongWritable getSecond() {
		return second;
	}
	public void setSecond(LongWritable second) {
		this.second = second;
	}
	@Override
	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		this.first.readFields(arg0);
		this.second.readFields(arg0);
		
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		this.first.write(arg0);
		this.second.write(arg0);
		
	}

	@Override
	public int compareTo(LongPair next) {
		// TODO Auto-generated method stub
		int cmp=this.first.compareTo(next.first);
		if(cmp!=0){
			return cmp;
		}else
			return this.second.compareTo(next.second);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LongPair other = (LongPair) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return first +"\t"+second;
	}
	

}
