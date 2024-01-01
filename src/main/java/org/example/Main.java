package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java HTMLCrawler <URL>");
            return;
        }

        String url = args[0];

        try {
            // Fetch HTML content from the main URL
            Document doc = Jsoup.connect(url).get();
//            System.out.println("Retrieved content from main URL:");
//            System.out.println(doc);

            Elements links = doc.select("a[href]");
            if (!links.isEmpty()) {
                Element link = links.first();
                String href = link.attr("href");

                // Fetch and print HTML content from the linked URL
                Document linkedDoc = Jsoup.connect(href).get();
                System.out.println("\nHTML content from linked URL (" + href + "):");
                System.out.println(linkedDoc);
            } else {
                System.out.println("No anchor tags found with href attribute.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
