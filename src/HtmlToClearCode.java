import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by 25065 on 2017/2/5.
 */
public class HtmlToClearCode {

    public static void main(String[] args) throws Exception{
        String fileInName="D:\\CloudStark\\spring-framework-4.3.6.RELEASE\\docs\\spring-framework-reference - Chinese\\html\\index.html";
        String fileOutName="D:\\index.html";
        File fileIn=new File(fileInName);
        File fileOut=new File(fileOutName);

        FileInputStream fis=new FileInputStream(fileIn);
        FileOutputStream fos=new FileOutputStream(fileOut);

        BufferedReader br=new BufferedReader(new InputStreamReader(fis));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));

        String line=null;
        while ((line=br.readLine())!=null){

//          String line="  123<dt><span class=\"part\"><a href=\"spring-introduction.html\">I. Overview of Spring Framework</a></span></dt><dd><dl><dt><span class=\"chapter\"><a href=\"overview-getting-started-with-spring.html\">1. Getting Started with Spring</a></span></dt><dt><span class=\"chapter\"><a href=\"overview.html\">2. Introduction to the Spring Framework</a></span></dt><dd><dl><dt><span class=\"section\"><a href=\"overview.html#overview-dependency-injection\">2.1. Dependency Injection and Inversion of Control</a></span></dt><dt><span class=\"section\"><a href=\"overview.html#overview-modules\">2.2. Modules</a></span></dt><dd><dl><dt><span class=\"section\"><a href=\"overview.html#overview-core-container\">2.2.1. Core Container</a></span></dt><dt><span class=\"section\"><a href=\"overview.html#overview-aop-instrumentation\">2.2.2. AOP and Instrumentation</a></span></dt><dt><span class=\"section\"><a href=\"overview.html#overview-messaging\">2.2.3. Messaging</a></span></dt><dt><span class=\"section\"><a href=\"overview.html#overview-data-access\">2.2.4. Data Access/Integration</a></span></dt><dt><span class=\"section\"><a href=\"overview.html#overview-web\">2.2.5. Web</a></span></dt><dt><span class=\"section\"><a href=\"overview.html#overview-testing\">2.2.6. Test</a></span></dt></dl></dd><dt><span class=\"section\"><a href=\"overview.html#overview-usagescenarios\">2.3. Usage scenarios</a></span></dt><dd><dl><dt><span class=\"section\"><a href=\"overview.html#dependency-management\">2.3.1. Dependency Management and Naming Conventions</a></span></dt><dd><dl><dt><span class=\"section\"><a href=\"overview.html#overview-spring-dependencies\">Spring Dependencies and Depending on Spring</a></span></dt><dt><span class=\"section\"><a href=\"overview.html#overview-maven-dependency-management\">Maven Dependency Management</a></span></dt><dt><span class=\"section\"><a href=\"overview.html#overview-maven-bom\">Maven \"Bill Of Materials\" Dependency</a></span></dt><dt><span class=\"section\"><a href=\"overview.html#overview-gradle-dependency-management\">Gradle Dependency Management</a></span></dt><dt><span class=\"section\"><a href=\"overview.html#overview-ivy-dependency-management\">Ivy Dependency Management</a></span></dt><dt><span class=\"section\"><a href=\"overview.html#overview-distribution-zip\">Distribution Zip Files</a></span></dt></dl></dd><dt><span class=\"section\"><a href=\"overview.html#overview-logging\">2.3.2. Logging</a></span></dt><dd><dl><dt><span class=\"section\"><a href=\"overview.html#overview-not-using-commons-logging\">Not Using Commons Logging</a></span></dt><dt><span class=\"section\"><a href=\"overview.html#overview-logging-slf4j\">Using SLF4J</a></span></dt><dt><span class=\"section\"><a href=\"overview.html#overview-logging-log4j\">Using Log4j</a></span></dt></dl></dd></dl></dd></dl></dd></dl></dd>";

            List<String> list=new ArrayList<>();
            Stack<String> stack=new Stack<>();
//            System.out.println(line);
           if (line.startsWith("  123")){
               System.out.println("获取相应的内容正确！！！");
               line=line.substring(line.indexOf("3")+1);
//               System.out.println(line);
               int index=3;
               int i=1;
               while (line!=null||line.equals("")){

                   int startIndex=line.indexOf("<");
                   int endIndex=line.indexOf(">");
//                   System.out.println(startIndex+" "+endIndex);
                   if (startIndex!=-1||endIndex!=-1){
                       String oneTags=line.substring(startIndex,endIndex+1);
                       line=line.substring(endIndex+1);
//                       System.out.println(oneTags);

                       switch (oneTags){
                           case "<dt>":
                               if (stack.empty()){
                                   stack.push("<dt>");
                                   list.add(i+"_<dt>_"+index);
                                   String blank="";
                                   while (index>0){
                                       blank+=" ";
                                       index--;
                                   }
                                   String dtStrings=blank+"<dt>"+line.substring(0,line.indexOf("</dt>"))+"</dt>";
                                   line=line.substring(line.indexOf("</dt>")+5);
                                   System.out.println(dtStrings);
                                   bw.write(dtStrings);
                                   bw.newLine();
                               }else {
                                   if (stack.peek().equals("<dt>")||stack.peek().equals("</dd>")){
                                       String strings=list.get(list.size()-1);
                                       int position=Integer.parseInt(strings.substring(strings.lastIndexOf("_")+1));
                                       stack.push("<dt>");
                                       list.add(i+"_<dt>_"+position);
                                       String blank="";
                                       while (position>0){
                                           blank+=" ";
                                           position--;
                                       }
                                       String dtStrings=blank+"<dt>"+line.substring(0,line.indexOf("</dt>"))+"</dt>";
                                       line=line.substring(line.indexOf("</dt>")+5);
                                       System.out.println(dtStrings);
                                       bw.write(dtStrings);
                                       bw.newLine();
                                   }else if (stack.peek().equals("<dl>")){
                                       String strings=list.get(list.size()-1);
                                       int position=Integer.parseInt(strings.substring(strings.lastIndexOf("_")+1))+5;
                                       stack.push("<dt>");
                                       list.add(i+"_<dt>_"+position);
                                       String blank="";
                                       while (position>0){
                                           blank+=" ";
                                           position--;
                                       }
                                       String dtStrings=blank+"<dt>"+line.substring(0,line.indexOf("</dt>"))+"</dt>";
                                       line=line.substring(line.indexOf("</dt>")+5);
                                       System.out.println(dtStrings);
                                       bw.write(dtStrings);
                                       bw.newLine();
                                   }
                               }

                               break;
                           case "<dd>":
                               if (stack.peek().equals("<dt>")){
                                   String strings=list.get(list.size()-1);
                                   int position=Integer.parseInt(strings.substring(strings.lastIndexOf("_")+1));
                                   stack.push("<dd>");
                                   list.add(i+"_<dd>_"+position);
                                   String blank="";
                                   while (position>0){
                                       blank+=" ";
                                       position--;
                                   }
                                   String dtStrings=blank+"<dd>";

                                   System.out.println(dtStrings);
                                   bw.write(dtStrings);
                                   bw.newLine();
                               }
                               break;

                           case "<dl>":
                               if (stack.peek().equals("<dd>")){
                                   String strings=list.get(list.size()-1);
                                   int position=Integer.parseInt(strings.substring(strings.lastIndexOf("_")+1))+5;
                                   stack.push("<dl>");
                                   list.add(i+"_<dl>_"+position);
                                   String blank="";
                                   while (position>0){
                                       blank+=" ";
                                       position--;
                                   }
                                   String dtStrings=blank+"<dl>";
                                   System.out.println(dtStrings);
                                   bw.write(dtStrings);
                                   bw.newLine();
                               }
                               break;
                           case "</dl>":
                               while (stack.peek().equals("<dt>")){
                                   stack.pop();
                                   list.remove(list.size()-1);
                               }
                               if (stack.peek().equals("<dl>")){
                                   String strings=list.get(list.size()-1);
                                   int position=Integer.parseInt(strings.substring(strings.lastIndexOf("_")+1));
                                   stack.pop();
                                   list.remove(list.size()-1);
                                   String blank="";
                                   while (position>0){
                                       blank+=" ";
                                       position--;
                                   }
                                   String dtStrings=blank+"</dl>";
                                   System.out.println(dtStrings);
                                   bw.write(dtStrings);
                                   bw.newLine();
                               }
                               break;
                           case "</dd>":
                               if (stack.peek().equals("<dd>")){
                                   String strings=list.get(list.size()-1);
                                   int position=Integer.parseInt(strings.substring(strings.lastIndexOf("_")+1));
                                   stack.pop();
                                   list.remove(list.size()-1);
                                   String blank="";
                                   while (position>0){
                                       blank+=" ";
                                       position--;
                                   }
                                   String dtStrings=blank+"</dd>";
                                   System.out.println(dtStrings);
                                   bw.write(dtStrings);
                                   bw.newLine();
                               }
                               break;

                       }

                   }else {
                       break;
                   }

                   i++;

               }
           }else {
               bw.write(line);
               bw.newLine();
           }

        System.out.println("ListSize: "+list.size());
        for (String s:list){
            System.out.println(s);
        }

        System.out.println("StackSize: "+stack.size());
        }

        bw.flush();
        fis.close();
        br.close();
        fos.close();
        bw.close();
    }
}
