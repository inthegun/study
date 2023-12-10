public class Test{

    public static void main(String []args){
       String str = "nobody:*:19576:0:99999:7:::";
       String str1 = "nobody:*:19576:0:99999:7:::";
       String[] tempStr = str.split(":");
       String[] tempStr1 = str1.split(":",-1);
       
       System.out.println("TEMP_STR1");
       for(String t1 : tempStr) System.out.printf("%s%n",t1);
       System.out.println("TEMP_STR2");
       for(String t1 : tempStr1) System.out.printf("%s%n",t1);
    }
}