package binarythink;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class InvertedIndexMapper extends Mapper<LongWritable,Text,Text,Text>{
	protected void map(LongWritable key, Text value, Context context) throws IOException,InterruptedException {
		FileSplit fileSplit = (FileSplit)context.getInputSplit();
		Map<String,Integer> freqhash = new HashMap<String,Integer>();
		String filename = fileSplit.getPath().getName();
		Text word = new Text();
		//Text fileName_lineOffset = new Text(fileName + "#" + key.toString());
		//StringTokenizer itr = new StringTokenizer(value.toString());
		String pattern = "[^a-zA-Z0-9]";
		String[] tokens = value.toString().split(pattern);
		for(String token: tokens) {
			//String token = itr.nextToken();
			word.set(token);
			if (freqhash.containsKey(token)) {
				freqhash.put(token, freqhash.get(token)+1);
			} else {
				freqhash.put(token, 1);
			}
			context.write(word, new Text(filename + "#" + freqhash.get(token)));
		}
	}

}
