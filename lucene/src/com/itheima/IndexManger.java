package com.itheima;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.hunspell.Dictionary;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

public class IndexManger {

	@Test
	public void createIndex() throws Exception{
		Directory dictionary=FSDirectory.open(new File("D:\\lenovo\\longzu"));
		Analyzer analyzer=new StandardAnalyzer();
		
		IndexWriterConfig config=new IndexWriterConfig(Version.LATEST, analyzer);
		IndexWriter indexWriter=new IndexWriter(dictionary, config);
		File dir=new File("C:\\Users\\dell\\Desktop\\龙族txt");
		for (File f:dir.listFiles()) {
			String filename=f.getName();
			String filecontent =FileUtils.readFileToString(f);
			String filepath=f.getPath();
			long filesize=FileUtils.sizeOf(f);
			Field filenameField=new TextField("filename", filename, Store.YES);
			Field filecontentField=new TextField("content",filecontent,Store.YES );
			Field filepathField = new TextField("path", filepath,Store.YES);
			Field filesizeField = new TextField("size", filesize+"", Store.YES);
			Document document = new Document();
			document.add(filenameField);
			document.add(filecontentField);
			document.add(filepathField);
			document.add(filesizeField);
			indexWriter.addDocument(document);
		}
		indexWriter.close();
	}
	
}
