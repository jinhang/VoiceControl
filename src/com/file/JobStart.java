package com.file;
/*
 * ͳ��Tb������
 * 
 */
import java.io.IOException;
//��Ҫ����
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;


import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;


public class JobStart {
	public static void main(String[] args) throws IOException,InterruptedException,ClassNotFoundException
	{
		String INPUT_PATH="hdfs://192.168.253.10:9000/jinhang_test/input";
		String OUTPUT_PATH="hdfs://192.168.253.10:9000/jinhang_test/output";
		Job job=new Job(new Configuration(), "WordCountAPP");
		//job.setJarByClass(WordCountAPP.class);//jar

		FileInputFormat.setInputPaths(job, new Path(INPUT_PATH));//jobִ����ҵ����·��
		job.setInputFormatClass(TextInputFormat.class);//���ļ�����ɼ�ֵ��

		//Map
		job.setMapperClass(MyMapper.class);

		//map <key2��value2> <hello,1>
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setPartitionerClass(HashPartitioner.class);

		job.setNumReduceTasks(1);

		//Reduce
		job.setReducerClass(Myreducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		//key3-value3 <hello,3>
		FileOutputFormat.setOutputPath(job,new Path(OUTPUT_PATH));//jobִ����ҵ���·��
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.waitForCompletion(true);//֪���������н���
	}
}
