/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nlplearn3;


//java 

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;



// OpenNLP

import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;
import opennlp.tools.sentdetect.SentenceDetectorME;


// Stanford NLP

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.WordToSentenceProcessor;



// LingPipe
import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunking;
import com.aliasi.sentences.IndoEuropeanSentenceModel;
import com.aliasi.sentences.SentenceChunker;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;





/**
 *
 * @author dalion
 */
public class NLPlearn3 {

    private static  String para = "When determining the end of sentences"
                                + " we need to consider serveral factor. Sentences may end with"
                                + " exclamation marks! or possibly questions marks? Within"
                                + " sentences we may find numbers like 3.14159,abbreviations"
                                + " such as found in Mr. Smith, and possibly ellipses either"
                                + " within a sentence ..., or at the end of a sentence...";
    public static void print(String s)
    {
        System.out.println(s);
    }
    public static void print(int i)
    {
        System.out.println(i);
    }
    public static void print(double d)
    {
        System.out.println(d);
    }
    private static void print(Span span) 
    {
        System.out.println(span);
    }
    public static void print(List l)
    {
        System.out.println(l);
    }
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        // TODO code application logic here
        // 第三章文本断句
        // OpenNLPz  1111111111111111111111
        // SentenceDetector class
       
        try
        {
            InputStream is = new FileInputStream(new File("/home/dalion/nlptest","en-sent.bin"));
            SentenceModel model = new SentenceModel(is);
            SentenceDetectorME detector = new SentenceDetectorME(model);
            String sents[] = detector.sentDetect(para);
            double probablities[] = detector.getSentenceProbabilities();//文本断句置信度
            for (String sent : sents)
            {
                print(sent);
            }
            for (double probablity : probablities)
            {
                print(probablity);
            }
            // setPosDetect method
            Span spans[] = detector.sentPosDetect(para);
            for(Span span : spans)
            {
                print(span);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NLPlearn3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NLPlearn3.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        // Stanford API
        // PTBTokenizer
        
        PTBTokenizer ptb = new PTBTokenizer(new StringReader(para),new CoreLabelTokenFactory(),null);
        // null is option syn is "americanize=true,...,..."
        WordToSentenceProcessor wtsp = new WordToSentenceProcessor();
        List<List<CoreLabel>> sents = wtsp.process(ptb.tokenize());
        for (List<CoreLabel> sent : sents)
        {
            print(sent);
        }
        for(List<CoreLabel> sent : sents)
        {
            for (CoreLabel ele : sent)
            {
                System.out.print(ele+" ");
                System.out.print(ele.endPosition()+" ");
            }
        }
        for(List<CoreLabel> sent : sents)
        {
            System.out.println(sent.get(0)+" "+sent.get(0).beginPosition());
        }
        for(List<CoreLabel> sent : sents)
        {
            int size = sent.size();
            System.out.println(sent.get(size-1) + " " + sent.get(size-1).endPosition());
        }
        
        // Document Preprocessor class
        Reader reader = new StringReader(para);
        DocumentPreprocessor dp = new DocumentPreprocessor(reader);
        for(List sentence : dp)
        {
            print(sentence);
        }
        // setTokenizerFactory -> pointto分词器
        try
        {
            Reader reader2 = new FileReader("/home/dalion/nlpProject/NLPlearn3/src/nlplearn3/xmlSBD.xml");
            DocumentPreprocessor dp2 = new DocumentPreprocessor(reader2,DocumentPreprocessor.DocType.XML);
            dp2.setElementDelimiter("sentence");
            
            for(List sent : dp2)
            {
                print(sent);
            }

              // use ListIterator
        for (List sent : dp2)
        {
            ListIterator list = sent.listIterator();
            while(list.hasNext())
            {
                System.out.print(list.next()+" ");
            }
        }
        } catch(FileNotFoundException ex)
        {
            print("File not Found");
        }
        
      // Use StanfordCoreNLP
        
        Properties prop = new Properties();
        prop.put("annotators","tokenize, ssplit");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(prop);
        Annotation annotation = new Annotation(para);
        pipeline.annotate(annotation);
        try{
        pipeline.xmlPrint(annotation,System.out);
        } catch(IOException ex)
        {
            print("IO EXCEPTION");
        }
        
        // LingPipe
        // IndeoEuropeanSentenceModel
        // arg1 最后一个词项是否必停用词
        // arg2 是否需要括号匹配
        TokenizerFactory tokenfactory = IndoEuropeanTokenizerFactory.INSTANCE;
        IndoEuropeanSentenceModel sentModel = new IndoEuropeanSentenceModel();
        List<String> tokenList = new ArrayList<>();
        List<String> whiteList = new ArrayList<>();
        Tokenizer tokenizer = tokenfactory.tokenizer(para.toCharArray(), 0, para.length());
        tokenizer.tokenize(tokenList,whiteList);
        
        String[] tokens = new String[tokenList.size()];
        String[] whites = new String[whiteList.size()];
        tokenList.toArray(tokens);
        whiteList.toArray(whites);
        
        int[] sentenceBoundaries = sentModel.boundaryIndices(tokens,whites);
        int start = 0;
        for(int boundary : sentenceBoundaries)
        {
            while(start <= boundary)
            {
                print(tokenList.get(start)+whiteList.get(start+1));
                start++;
            }
                print(boundary);
        }
        
        // User SentenceChunker class
        SentenceChunker sC = new SentenceChunker(tokenfactory,sentModel);
        Chunking chunking = sC.chunk(para.toCharArray(),0,para.length());
        Set<Chunk> sentences = chunking.chunkSet();
        String slice = chunking.charSequence().toString();
        for(Chunk sent : sentences)
        {
            print("["+slice.substring(sent.start(),sent.end())+"]");
        }
        // Use MedlineSentenceModel class
        // unrelized ...
        
        
        //训练文本断句模型
        //OpenNLP SentenceDetectorME class
        //上文已实现
        
        
    
    
    
    
    
    
    }

   
    

    
}
