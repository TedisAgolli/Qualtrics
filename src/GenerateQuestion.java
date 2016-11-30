/**
 * Created by Tedis on 11/19/2016.
 */

import java.io.*;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class GenerateQuestion {



    public static void main(String [] args) {

        String header = "[[AdvancedFormat]]\r\n" +
                "[[Block:Image Comparison]]\r\n";


        // The name of the file to open.
        String fileName = "questions.txt";


        try {
            // FileReader reads text files in the default encoding.
            FileWriter fileWriter=
                    new FileWriter(fileName,true);

            // Always wrap FileReader in BufferedReader.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            //Writing to file
            bufferedWriter.append(header);

            Random r = new Random();
            int Low = 0;
            int High = 2;
            int Result;
            int Result2;

            String link = "http://176.32.230.42/tedisagolli.com/images";
            Elements files;

            Document doc = Jsoup.connect(link).get();

        /*    for(Element element :  doc.select("a[href~=(?i)\\.(dir)]") )
            {
                 doc = Jsoup.connect(link+"/" + element.attr("href")).get();
                 files = doc.select("a[href~=(?i)\\.(png|jpe?g)]");

                for (int i=0;i<files.size();i+=2) {
                    Result= r.nextInt(High-Low) + Low;
                    Result2 = 1-Result;
                    bufferedWriter.append(makeImgComparison(element.attr("href") + files.get(i + Result).attr("href"),element.attr("href") +files.get(i+Result2).attr("href")));
                }
            }*/

            String folderName;
            for(Element element :  doc.select("a[href~=(?i)\\.(dir)]") )
            {

                folderName = "[[Block:" +element.attr("href") + "]] \r\n" ;

    bufferedWriter.append(folderName);


                doc = Jsoup.connect(link+"/" + element.attr("href")).get();
                files = doc.select("a[href~=(?i)\\.(png|jpe?g)]");

                for (int i=0;i<files.size();i+=2) {

                    bufferedWriter.append(makeSingleImg(element.attr("href") + files.get(i).attr("href")));
                }
            }





            bufferedWriter.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    private static String makeImgComparison(String url1, String url2)
    {
        String question = "[[Question:Matrix]]\r\n" +
                "<img src=\"http://176.32.230.42/tedisagolli.com/images/" + url1 + "\" style=\"width: 240px; height: 400px;\" /> <img src=\"http://176.32.230.42/tedisagolli.com/images/" + url2 +"\" style=\"width: 240px; height: 400px;\" /> &nbsp;&nbsp;<br />\r\n" +
                "<br />\r\n" +
                "<span style=\"font-family:georgia,serif;\"><span style=\"font-size:16px;\">How would you respond to this statement?&nbsp;</span></span>\r\n" +
                "\"<div><span style=\"font-size:16px;\"><span style=\"font-family:georgia,serif;\">&quot;These images are identical.&quot;</span></span></div>\r\n" +
                "[[Choices]]\r\n" +
                "Answer\r\n" +
                "\r\n" +
                "[[Answers]]\r\n" +
                "Undecided\r\n" +
                "Agree\r\n" +
                "Disagree\r\n\r\n";

        return question;
    }


    private static String makeSingleImg(String url)
    {
        String question = "[[Question:Matrix]]\r\n" +
                "<img src=\"http://176.32.230.42/tedisagolli.com/images/" + url + "\" style=\"width: 240px; height: 400px;\" /> <br />\r\n" +
                "<br />\r\n" +
                "<span style=\"font-family:georgia,serif;\"><span style=\"font-size:16px;\">How would you respond to this statement?&nbsp;</span></span>\r\n" +
                "\"<div><span style=\"font-size:16px;\"><span style=\"font-family:georgia,serif;\">&quot;This image is identical to the one I am used to seeing on my phone.&quot;</span></span></div>\r\n" +
                "[[Choices]]\r\n" +
                "Answer\r\n" +
                "\r\n" +
                "[[Answers]]\r\n" +
                "Undecided\r\n" +
                "Agree\r\n" +
                "Disagree\r\n\r\n";

        return question;
    }


}
