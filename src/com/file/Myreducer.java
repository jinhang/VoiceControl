package com.file;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Myreducer extends Reducer<Text, IntWritable, Text,IntWritable>
{
	IntWritable value3=new IntWritable();//单词出现的总次数即 map 1的reduce
	/**
	 *  key 单词
	 *  value map输出 1的集合
	 */

	protected void reduce(Text keys, Iterable<IntWritable> values,
			org.apache.hadoop.mapreduce.Reducer.Context context)
			throws IOException, InterruptedException 
	{
		// TODO Auto-generated method stub
		int sum=0;
		for(IntWritable count:values)
		{
			
			sum+=count.get();
		}
		Text key3=new Text();//单词
		value3.set(sum);
		context.write(key3, value3);
	}
	
}
