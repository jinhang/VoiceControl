package com.file;
/*
 * 统计Tb量单词
 * 
 */
import java.io.IOException;
//包要导对
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

		FileInputFormat.setInputPaths(job, new Path(INPUT_PATH));//job执行作业进入路径
		job.setInputFormatClass(TextInputFormat.class);//将文件处理成键值对

		//Map
		job.setMapperClass(MyMapper.class);

		//map <key2，value2> <hello,1>
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setPartitionerClass(HashPartitioner.class);

		job.setNumReduceTasks(1);

		//Reduce
		job.setReducerClass(Myreducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		//key3-value3 <hello,3>
		FileOutputFormat.setOutputPath(job,new Path(OUTPUT_PATH));//job执行作业输出路径
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		job.waitForCompletion(true);//知道程序运行结束
	}
}
