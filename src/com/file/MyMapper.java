package com.file;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 1.Key-value �ı�����ʼλ�� �ı���
 * 2.ÿ��ת���ɼ�ֵ��
 * 3.ͳ�ƴ��� �¼�ֵ�� key-value ����-���� hello 3
 * @author jinhang
 *
 */
public class MyMapper extends Mapper<LongWritable,Text, Text, IntWritable>{
	Text key2=new Text();//�е���
	IntWritable value2=new IntWritable(1);//�õ����ڸ��г��ִ���
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
			context.write(key2, value2);//����-���ʴ��� (hello,1)����hello 2��(hello,1)
		}
		
	}
	
	//key�ı�����ʼλ��
	//value�ı���
	
}
