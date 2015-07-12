//package com.file;
///**
// * 数据去重的最终目标是让原始数据中出现次数超过一次的数据在输出文件中只出现一次。我们自然而然会想到将同一个数据的所有记录都交给一台
// * reduce机器，无论这个数据出现多少次，只要在最终结果中输出一次就可以了。具体就是reduce的输入应该以数据作为key，而对value-list
// * 则没有要求。当reduce接收到一个<key，value-list>时就直接将key复制到输出的key中，并将value设置成空值。
//       在MapReduce流程中，map的输出<key，value>经过shuffle过程聚集成<key，value-list>后会交给reduce。
//       所以从设计好的reduce输入可以反推出map的输出key应为数据，value任意。继续反推，map输出数据的key为数据，而在这个实例中
//       每个数据代表输入文件中的一行内容，所以map阶段要完成的任务就是在采用Hadoop默认的作业输入方式之后，将value设置为key，
//       并直接输出（输出中的value任意）。map中的结果经过shuffle过程之后交给reduce。reduce阶段不会管每个key有多少个value，
//       它直接将输入的key复制为输出的key，并输出就可以了（输出中的value被设置成空了）。
// * @author jinhang
// *
// */
//import java.io.IOException;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.Reducer;
//import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
//import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//import org.apache.hadoop.util.GenericOptionsParser;
//public class Dedup {
//	//map将输入中的value复制到输出数据的key上，并直接输出
//	public static class Map extends Mapper<Object,Text,Text,Text>
//	{
//		private static Text line=new Text();//每行数据
//		//实现map函数
//		public void map(Object key,Text value,Context context)throws IOException,InterruptedException
//		{
//			line=value;
//            context.write(line, new Text(""));
//		} 
//    }
//	//reduce将输入中的key复制到输出数据的key上，并直接输出
//	public static class Reduce extends Reducer<Text,Text,Text,Text>{
//
//		//实现reduce函数
//
//		public void reduce(Text key,Iterable<Text> values,Context context)
//
//				throws IOException,InterruptedException{
//
//			context.write(key, new Text(""));
//
//		}
//
//
//
//	}
//
//
//
//	public static void main(String[] args) throws Exception
//	{
//
//		Configuration conf = new Configuration();
//
//		//这句话很关键
//
//		conf.set("mapred.job.tracker", "192.168.1.110:9001");
//        String[] ioArgs=new String[]{"dedup_in","dedup_out"};
//
//		String[] otherArgs = new GenericOptionsParser(conf, ioArgs).getRemainingArgs();
//
//		if (otherArgs.length != 2) {
//
//			System.err.println("Usage: Data Deduplication <in> <out>");
//
//			System.exit(2);
//
//		}
//        Job job = new Job(conf, "Data Deduplication");
//        job.setJarByClass(Dedup.class);
//        //设置Map、Combine和Reduce处理类
//        job.setMapperClass(Map.class);
//        job.setCombinerClass(Reduce.class);
//        job.setReducerClass(Reduce.class);
//        //设置输出类型
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(Text.class);
//        //设置输入和输出目录
//
//		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
//
//		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
//
//		System.exit(job.waitForCompletion(true) ? 0 : 1);
//
//	}
//}
