package com.SearchAPI;

import java.io.IOException;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import java.util.*;

public class LuceneTester {

    String indexDir = SearchConstants.IndexDirectory;
    String dataDir = SearchConstants.DataDirectory;
    Indexer indexer;
    Searcher searcher;

    private void query( String sequence, String searchQuery, String nodeid, String ipaddress,
                       String portaddress, String transport, String neighbornode) {
        LuceneTester tester;

        try {
            tester = new LuceneTester();
            tester.createIndex();
            tester.search(sequence, searchQuery, nodeid, ipaddress, portaddress, transport, neighbornode);

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
        numIndexed = indexer.createIndex(dataDir, new TextFileFilter());
        indexer.close();
    }

        public void search(String sequence, String searchQuery, String nodeid, String ipaddress,
                            String portaddress, String transport, String neighbornode) throws IOException, ParseException {
        searcher = new Searcher(indexDir);
        TopDocs hits = searcher.search(searchQuery);


        List<String> result = new ArrayList<String>();

          for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            result.add(doc.get(LuceneConstants.FILE_PATH)) ;
        }

        searcher.close();

        boolean ans = result.isEmpty();
        if (ans) {
            System.out.println("No such File in Index");
        }
        else{
            QueryManager q = new QueryManager();
                    q.createResultFile(result, sequence, searchQuery, nodeid, ipaddress,
                            portaddress, transport, neighbornode, "Index");
        }


    }

    public void dolocalsearch(String searchQuery) throws IOException, ParseException {
        searcher = new Searcher(indexDir);
        TopDocs hits = searcher.search(searchQuery);

        List<String> result = new ArrayList<String>();

        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            result.add(doc.get(LuceneConstants.FILE_PATH)) ;
        }

        searcher.close();

        boolean ans = result.isEmpty();
        if (ans) {
            System.out.println("No such File in Index");
        }
        else{
            SearchMethod s = new SearchMethod();
            s.passLocalResponse(result, searchQuery);
        }
    }
}