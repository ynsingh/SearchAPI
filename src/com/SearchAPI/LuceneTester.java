package com.SearchAPI;

import java.io.IOException;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import java.util.*;

//import static com.SearchAPI.createFile.createResultFile;


public class LuceneTester {


    String indexDir = "/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/lucene-3.6.2/Index";
    String dataDir = "/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/lucene-3.6.2/Data";
    Indexer indexer;
    Searcher searcher;

    /*  private void query() {
          LuceneTester tester;

          try {
              tester = new LuceneTester();
              tester.createIndex();
              tester.search("Suresh");

          } catch (IOException e) {
              e.printStackTrace();
          } catch (ParseException e) {
              e.printStackTrace();
          }
      }*/
    private void query(String qry) {
        LuceneTester tester;

        try {
            tester = new LuceneTester();
            tester.createIndex();
            tester.search(qry);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void querylocal(String qry) {
        LuceneTester tester;

        try {
            tester = new LuceneTester();
            tester.createIndex();
            tester.dolocalsearch(qry);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private void createIndex() throws IOException {
        indexer = new Indexer(indexDir);
        int numIndexed;
        long startTime = System.currentTimeMillis();
        numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
        long endTime = System.currentTimeMillis();
        indexer.close();
        // System.out.println(numIndexed+" File indexed, time taken: " +(endTime-startTime)+" ms");
    }

    /*private void search(String searchQuery) throws IOException, ParseException {
        searcher = new Searcher(indexDir);
        long startTime = System.currentTimeMillis();
        TopDocs hits = searcher.search(searchQuery);
        long endTime = System.currentTimeMillis();
        // System.out.println(hits.totalHits + " documents found. Time :" + (endTime - startTime));
        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            System.out.println("File: " + doc.get(LuceneConstants.FILE_PATH));
        }
        searcher.close();
    }*/






    private void search(String searchQuery) throws IOException, ParseException {
        searcher = new Searcher(indexDir);
        long startTime = System.currentTimeMillis();
        TopDocs hits = searcher.search(searchQuery);
        long endTime = System.currentTimeMillis();


        List<String> result = new ArrayList<String>();

          for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            result.add(doc.get(LuceneConstants.FILE_PATH)) ;
        }

        searcher.close();

        boolean ans = result.isEmpty();
        if (ans == true) {
            System.out.println("No such File in Index");
        }
        else{
            //for(String indlresult:result)
            //    System.out.println(indlresult);
            //createResultFile(result);
            QueryManager.createResultFile(result);
        }


    }

    private void dolocalsearch(String searchQuery) throws IOException, ParseException {
        searcher = new Searcher(indexDir);
        long startTime = System.currentTimeMillis();
        TopDocs hits = searcher.search(searchQuery);
        long endTime = System.currentTimeMillis();


        List<String> result = new ArrayList<String>();

        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            result.add(doc.get(LuceneConstants.FILE_PATH)) ;
        }

        searcher.close();

        boolean ans = result.isEmpty();
        if (ans == true) {
            System.out.println("No such File in Index");
        }
        else{
            //for(String indlresult:result)
            //    System.out.println(indlresult);
            OwnQuery.passLocalResponse(result, searchQuery);
        }


    }


}