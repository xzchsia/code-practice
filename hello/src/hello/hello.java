package hello;

public class hello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello,world");
		
		String str1 = "the light";
        
        String str2 = new String(str1);
         
        //==用于判断是否是同一个字符串对象
        System.out.println( str1  ==  str2);
        
        String str3 = "the light";
        System.out.println( str1  ==  str3);

	}

}
