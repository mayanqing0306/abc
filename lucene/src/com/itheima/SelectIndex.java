package com.itheima;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class SelectIndex {

	@Test
	public void Index() throws Exception {
//		Directory directory = FSDirectory.open(new File("D:\\lenovo\\opcc"));
//		IndexReader indexReader=DirectoryReader.open(directory);
//		IndexSearcher indexSearcher=new IndexSearcher(indexReader);
		Directory directory= FSDirectory.open(new File("D:\\lenovo\\opcc"));
		IndexReader indexReader =DirectoryReader.open(directory);
		IndexSearcher indexSearcher=new IndexSearcher(indexReader);
		Query query=new TermQuery(new Term("filename","apache"));
		TopDocs topDocs=indexSearcher.search(query, 10);
		System.out.println("查询总条数"+topDocs.totalHits);
		for (ScoreDoc scoreDoc: topDocs.scoreDocs) {
			Document document=indexSearcher.doc(scoreDoc.doc);
			System.out.println(document.get("filename"));
			//System.out.println(document.get("content"));
			System.out.println(document.get("path"));
			System.out.println(document.get("size"));
		}
		indexReader.close();
	}
	@Test
	public void testAnalyzer() throws Exception {
		
//		毛不易
		
//		Analyzer analyzer = new StandardAnalyzer();
//		Analyzer analyzer = new ChineseAnalyzer(); 
//		C J K
//		Analyzer analyzer = new CJKAnalyzer();
//		Analyzer analyzer = new SmartChineseAnalyzer();
		Analyzer analyzer = new IKAnalyzer();
		
//		StandardAnalyzer:中文的分词 一个字一个字
//		ChineseAnalyzer：过时了
//		CJKAnalyzer:两个字两个字  需要导入 lucene-analyzers-smartcn-4.10.3.jar
//		SmartChineseAnalyzer: 中文还算可以，英文会出现少字母的情况 需要导入 lucene-analyzers-smartcn-4.10.3.jar
		
		
		String str = "The Spring Framework provides a comprehensive programming and configuration model.传智播客：MyBatis 本是apache的一个开源项目iBatis,法轮功 2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis--by";
		
		TokenStream tokenStream = analyzer.tokenStream("test", str);
		
		tokenStream.reset();  //重置指针
//		添加一个引用 字符串
		CharTermAttribute addAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		
		while(tokenStream.incrementToken()) {
			System.out.println(addAttribute);
		}
	}
}
