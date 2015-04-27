package com.bwq.treasuryArbitrage.dataFetch;
import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;


public interface TestJNA extends  Library{
	public static final TestJNA 
	INSTANCE = (TestJNA)Native.loadLibrary("./DLL/TestJNA", TestJNA.class);
	
	void initial(String[] codes, int length,String user,String pwd);
	
	void shut();
	
	void copyData();
	
	void callBack_String(Callback callback);
	
	void callBack_Double(Callback callback);
}

