package binarythink;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InvertedIndexReducer extends Reducer<Text,Text,Text,Text> {
	protected void reduce(Text key, Iterable<Text> values, Context context) 
			throws IOException, InterruptedException {
		Iterator<Text> it = values.iterator();
		List<Posting> postings = new ArrayList<Posting>();
		nextToken:
		for (;it.hasNext();) {
			String str = it.next().toString();
			String[] filenamefreq = str.split("#");
			for (Posting p: postings) {
				if (p.getA().equals(filenamefreq[0])) {
					p.setPosting(p.getA(), p.getB()+Integer.parseInt(filenamefreq[1]));
					continue nextToken;
				}
			}
			postings.add(new Posting(filenamefreq));
		}
		StringBuilder all = new StringBuilder();
		Collections.sort(postings);
		Iterator<Posting> itp = postings.iterator();
		if(itp.hasNext())
			all.append(itp.next().toString());
		for (;itp.hasNext();) {
			all.append(";");
			all.append(itp.next().toString());
		}
		context.write(key, new Text(all.toString()));
	}
}
