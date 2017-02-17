package test;
import com.google.gson.Gson;

public class TestGson {
	public static void main(String[] args) {
		Gson gson = new Gson();
		String[] strs = new String[6];
		for(int i=0;i<strs.length;i++){
			strs[i] = String.valueOf(1);
		}
		System.out.println(gson.toJson(strs));
	}
}
