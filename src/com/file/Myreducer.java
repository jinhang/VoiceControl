package com.file;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Myreducer extends Reducer<Text, IntWritable, Text,IntWritable>
{
	IntWritable value3=new IntWritable();//���ʳ��ֵ��ܴ����� map 1��reduce
	/**
	 *  key ����
	 *  value map��� 1�ļ���
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
		Text key3=new Text();//����
		value3.set(sum);
		context.write(key3, value3);
	}
	
}
