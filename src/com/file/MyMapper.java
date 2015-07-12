package com.file;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 1.Key-value 文本行起始位置 文本行
 * 2.每行转化成键值对
 * 3.统计次数 新键值对 key-value 单词-次数 hello 3
 * @author jinhang
 *
 */
public class MyMapper extends Mapper<LongWritable,Text, Text, IntWritable>{
	Text key2=new Text();//行单词
	IntWritable value2=new IntWritable(1);//该单词在该行出现次数
	@Override
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException 
	{
		// TODO Auto-generated method stub
		String []split=value.toString().split(" ");
		for(String word : split)
		{
			key2.set(word);
			context.write(key2, value2);//单词-单词次数 (hello,1)两个hello 2个(hello,1)
		}
		
	}
	
	//key文本行起始位置
	//value文本行
	
}
