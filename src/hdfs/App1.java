package hdfs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;


public class App1 
{
	public static final String uri="hdfs://192.168.253.10:9000/";
	static Configuration conf=new Configuration();
	
	public static void main(String[] args) throws Exception 
	{
		/**
		 * ע�⣺���ӵ�hadoop��Ȩ�����⣬�������ͷ�������Ҫ�ǣ����������hadoop fs -chmode 777 /
		 */
		System.out.println("��ַ��"+URI.create(uri));

		FileSystem fs=FileSystem.get(URI.create(uri), conf);
		
		//mkdir(fs, "/jinhang_test");
        //System.out.println("�����ļ��гɹ���");
        
//        writeFile(fs, "/jinhang_test/f1", "my name is jinhang !Hello world!");
//        System.out.println("д���ļ��ɹ���");
//          readFile(fs, "/jinhang_test/f1");
//          System.out.println("�����ɹ���");
//          deleteFile(fs, "/jinhang_test/f1");
//          System.out.println("ɾ���ɹ���");
//		fileListInfo(fs, "/");
//		System.out.println("��ʾ�ɹ�");
		
		
		
          
	}
	public static  void mkdir(FileSystem fs,String path) throws IOException
	{
		boolean exists = fs.mkdirs(new Path(path));
		if(!exists)
		{
			boolean re = fs.mkdirs(new Path(path));
	        System.out.println(re);
        }
        System.out.println("mk �ɹ���");
		
	}
	public static  void writeFile(FileSystem fs,String path,String buf) throws IOException
	{
		  FSDataOutputStream fsDataOutputStream=fs.create(new Path(path));//creatָ��д�������
		  IOUtils.copyBytes(new ByteArrayInputStream(buf.getBytes()), fsDataOutputStream,conf,true);
	}
	public static  void readFile(FileSystem fs,String path) throws IOException
	{
		  FSDataInputStream fsDataInputStream=fs.open(new Path(path));//open�ļ�ָ���ļ�������
		  IOUtils.copyBytes(fsDataInputStream, System.out, conf, true);
	}
	public static  void fileListInfo(FileSystem fs,String path) throws IOException
	{
		  FileStatus [] liststatus=fs.listStatus(new Path("/"));
		  for(FileStatus fileStatus :liststatus)
		  {  
			  String type=fileStatus.isDir() ? "�ļ�":"Ŀ¼";
			  short replication=fileStatus.getReplication();
			  String permission=fileStatus.getPermission().toString();
			  long len=fileStatus.getLen();
			  Path path_dir=fileStatus.getPath();
			  System.out.println(type+"\t"+permission+"\t"+replication+"\t"+len+"\t"+path);
			  
		  }
	}
	public static  void deleteFile(FileSystem fs,String path) throws IOException
	{
		 //fs.delete(new Path(path),true);//�ݹ�ɾ���ļ��������������
		 fs.deleteOnExit(new Path(path));//ɾ���ļ�
	}
}
