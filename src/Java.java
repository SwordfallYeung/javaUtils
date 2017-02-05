import java.io.*;
import java.util.regex.Pattern;

/**
 * Created by 25065 on 2016/11/13.
 */
public class Java {

    public static void main(String[] args) throws Exception{
        File infile=new File("D:\\bna_genoscope_v5_info\\info\\Brassica_napus_genoscope_go.txt");
        File outfile=new File("D:\\bna_genoscope_v5_info\\info\\out_Brassica_napus_genoscope_go.txt");

        FileInputStream fis=new FileInputStream(infile);
        FileOutputStream fos=new FileOutputStream(outfile);

        BufferedReader br=new BufferedReader(new InputStreamReader(fis));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));

        int index=1035976;

        try {
            String line=null;
            while ((line=br.readLine())!=null){

                String[] strings=line.split("\t");
                String geneName=strings[0];
                String[] gos=strings[1].split(",");
                for (String s:gos){
                    line=index+"\t"+geneName+"\t"+s+"\t"+""+"\t"+"bna_genoscope_v5";
                    bw.write(line);
                    bw.newLine();
                    index++;
                }

            }

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
            fos.close();
            br.close();
            bw.close();
        }
    }

    public void testGOConvert()throws Exception{
//        File infile=new File("D:\\ath_ensembl_release33_info\\info\\go.txt");
//        File outfile=new File("D:\\ath_ensembl_release33_info\\info\\out_go.txt");
//
//        FileInputStream fis=new FileInputStream(infile);
//        FileOutputStream fos=new FileOutputStream(outfile);
//
//        BufferedReader br=new BufferedReader(new InputStreamReader(fis));
//        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
//
//        int index=240333;
//
//        try {
//            String line=null;
//            while ((line=br.readLine())!=null){
//
//                String[] strings=line.split("\t");
//                String geneName=strings[0];
//                String[] gos=strings[1].split(",");
//                for (String s:gos){
//                    line=index+"\t"+geneName+"\t"+s+"\t"+""+"\t"+"ath_ensembl_release33";
//                    bw.write(line);
//                    bw.newLine();
//                    index++;
//                }
//
//            }
//
//            bw.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            fis.close();
//            fos.close();
//            br.close();
//            bw.close();
//        }
    }

    public void testFileContainNull() throws Exception{
//        File infile=new File("D:\\gmx_phytozome_v11_info\\info\\genome_info.txt");
//
//        FileInputStream fis=new FileInputStream(infile);
//
//        BufferedReader br=new BufferedReader(new InputStreamReader(fis));
//
//        int index=40364;
//        int i=1;
//        try {
//            String line=null;
//            while ((line=br.readLine())!=null){
//                String[] strings=line.split("\t");
//                int j=1;
//                for (String s:strings){
//                    if (s==null||s.equals("")){
//                        System.out.println(i+"行第"+j+"个有问题,有数据为空");
//                    }
//                    j++;
//                }
//
//                if (strings.length>11){
//                    System.out.println(i+"行有问题,字数不足");
//                }
//                i++;
//            }
//
//            System.out.println("文件正确");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            fis.close();
//            br.close();
//        }
    }

    public void txtAddId() throws Exception{
//        File infile=new File("D:/gmx_ensembl_23/gmx_ensembl_23_ipranno.txt");
//        File outfile=new File("D:/gmx_ensembl_23/out_gmx_ensembl_23_ipranno.txt");
//
//        FileInputStream fis=new FileInputStream(infile);
//        FileOutputStream fos=new FileOutputStream(outfile);
//
//        BufferedReader br=new BufferedReader(new InputStreamReader(fis));
//        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));
//
//        int index=40364;
//
//        try {
//            String line=null;
//            while ((line=br.readLine())!=null){
//                line=index+"\t"+line;
//                bw.write(line);
//                bw.newLine();
//                index++;
//            }
//
//            bw.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            fis.close();
//            fos.close();
//            br.close();
//            bw.close();
//        }
    }

    public void mysqlTranferToSqlServer()throws Exception{
        File infile=new File("D:/dbo.sql");
        File outfile=new File("D:/outdbo.sql");

        if (infile.exists()){
            System.out.println("存在");
        }
        FileInputStream fis=new FileInputStream(infile);
        FileOutputStream fos=new FileOutputStream(outfile);

        BufferedReader br=new BufferedReader(new InputStreamReader(fis));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(fos));

        int i=0;

        try {
            String line=null;
            while ((line=br.readLine())!=null){

                if (line.startsWith("INSERT")){
//                    line=line.replaceFirst("\\`","[dbo].[");
//                    line=line.replace("`","]");
//                    if (line.endsWith(";")){
//                        line=line.replace(";","");
//                    }
//
//                    line=line.replace("\\'","''");
//
//                    bw.write(line);
//                    bw.newLine();
//                    bw.write("GO");
//                    bw.newLine();
//                    bw.write("GO");
//                    bw.newLine();

                    line=line.replace("null)","N'sesame')");
                    bw.write(line);
                    bw.newLine();
                }else {
                    bw.write(line);
                    bw.newLine();
                }
            }

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
            fos.close();
            br.close();
            bw.close();
        }

    }
}
