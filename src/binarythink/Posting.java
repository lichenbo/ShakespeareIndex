package binarythink;


	public class Posting implements Comparable<Posting>{
		
		/* same as Pair */
		private String filename;
		private int freqency;
		

		public Posting(String a, int b) {
			this.filename = a;
			this.freqency = b;
		}
		
		public Posting(String[] str) {
			this.filename = str[0];
			this.freqency = Integer.parseInt(str[1]);
		}

		public void setPosting(String a,int b) {
			this.filename = a;
			this.freqency = b;
		}
		
		public String getA() {
			return filename;
		}
		
		public int getB() {
			return freqency;
		}

		@Override
		public int compareTo(Posting that) {
			// TODO Auto-generated method stub
			if (this.freqency > that.freqency) return -1;
			if (this.freqency == that.freqency) return 0;
			else
				return 1;
		}
		
		public String toString() {
			return filename + "#" + freqency;
		}
	}

