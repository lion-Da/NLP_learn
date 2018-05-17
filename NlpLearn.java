/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NlpLearn;


//书中第一章简述代码 及第二章文本分词代码
//java


import java.util.*;
import java.io.*;
import java.text.BreakIterator;



/*
// Lingpipe

import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.Tokenizer;
import com.aliasi.tokenizer.TokenizerFactory;
import com.aliasi.tokenizer.EnglishStopTokenizerFactory;
import com.aliasi.tokenizer.LowerCaseTokenizerFactory;
import com.aliasi.tokenizer.PorterStemmerTokenizerFactory;
import com.aliasi.tokenizer.Tokenization;



// opennlp

import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.namefind.NameFinderME;

import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.Span;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import opennlp.tools.tokenize.TokenSample;
import opennlp.tools.tokenize.TokenSampleStream;
import opennlp.tools.tokenize.TokenizerFactory;

import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.stemmer.PorterStemmer;


//stanford nlp

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.simple.*;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
*/

/**
 *
 * @author dalion
 */

public class NlpLearn {
    static String para = "Let`s pause, ant then reflect.";
    public static void print(String s)
    {
        System.out.println(s);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // here is apache opennlp
        /*
        try(InputStream is = new FileInputStream(new File("/home/dalion/nlptest/","en-token.bin")))
        {
            TokenizerModel model = new TokenizerModel(is);
            Tokenizer tokenizer = new TokenizerME(model);
            String tokens[] = tokenizer.tokenize("You can use OpenNLP with any language, demo models are provided here.\n" +
            "\n" +
            "The models are fully compatible with the latest release, they can be used for testing or getting started.\n" +
            "\n" +
            "Please train your own models for all other use cases.\n" +
            "\n" +
            "Documentation, including Mr.tom, code usage and command-line interface examples are available here\n" +
            "\n" +
            "You can also follow our mailing lists for news and updates.");
            for (String i : tokens)
            {
                System.out.println(i);
            }
                System.out.println("OVER!!");
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("FIlenotefound");
        }
        catch(IOException ex)
        {
            System.out.println("IOexception");
        }
        }
         */
        // here is standford nlp
        // set up pipeline properties
        /*
        Document doc = new Document("add your text here! It can contain multiple sentences.");
        for (Sentence sent : doc.sentences()) {  // Will iterate over two sentences
        // We're only asking for words -- no need to load any models yet
            System.out.println("The second word of the sentence '" + sent + "' is " + sent.word(0));
        // When we ask for the lemma, it will load and run the part of speech tagger
            System.out.println("The third lemma of the sentence '" + sent + "' is " + sent.lemma(2));
        // When we ask for the parse, it will load and run the parser
            System.out.println("The parse of the sentence '" + sent + "' is " + sent.parse());
        // ...
        }
        PTBTokenizer ptb = new PTBTokenizer(new StringReader("do you like Mr.Smith."),new CoreLabelTokenFactory(),null);
        while(ptb.hasNext())
        {
            System.out.println(ptb.next());
        }
         */
        // here is lingpipe
        /*
        List<String> tokenList = new ArrayList<>();
        List<String> whiteList = new ArrayList<>();
        String text = "Do you like Mr.Dalion, yes i`m his fans!\n \tSo can you sign me?";
        TokenizerFactory tokenizerf = IndoEuropeanTokenizerFactory.INSTANCE;
        Tokenizer t = tokenizerf.tokenizer(text.toCharArray(),0,text.length());
        t.tokenize(tokenList,whiteList);
        for (String i : tokenList)
        {
            System.out.println(i+" ");
        }
         */
        // page 13 code
        /*
        String para = "The first sentence. THe Second Sentence.";
        Reader reader = new StringReader(para);
        DocumentPreprocessor documentpreprocessor = new DocumentPreprocessor(reader);
        List<String> sent = new LinkedList<String>();
        for (List<HasWord> e : documentpreprocessor)
        {
            StringBuilder sb = new StringBuilder();
            List<HasWord> hasWordList = e;
        for (HasWord token : hasWordList)
        {
            sb.append(token).append(" ");
        }
            sent.add(sb.toString());
        }
        for (String s : sent)
        {
            System.out.println(s);
        }
         */
        // page 15 code FAILED
        /*
        try
        {
            String[] sent =
            {"Tim was a good neighbor. Perhaps not as good as Bob, but still pretty good. Of course Mr.Adam took the cake!"};
            Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
            TokenNameFinderModel model = new TokenNameFinderModel(new File("/home/dalion/nlptest","es-ner-person.bin"));
            NameFinderME finder = new NameFinderME(model);
            for (String s : sent)
            {
                String[] tokens = tokenizer.tokenize(s);
                Span[] nameSpans = finder.find(tokens);
                System.out.println(Arrays.toString(Span.spansToStrings(nameSpans, s)));
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
         */
        // page 16 code
        /*
        POSModel model = new POSModelLoader().load(new File("/home/dalion/nlptest","en-pos-maxent.bin"));
        POSTaggerME tagger = new POSTaggerME(model);
        String sent = "POS processing is useful for enhancing thequality of data sent to other elements os a pipeline.";
        String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(sent);
        String tags[] = tagger.tag(tokens);
        for(int i=0;i<tokens.length;i++)
        {
            System.out.println(tokens[i]+"["+tags[i]+"]");
        }
         */
        //page 18 code
        /*
        Properties properties = new Properties();
        properties.put("annotators","tokenize,ssplit,parse");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        Annotation anotation = new Annotation("the meaning and purpose of life is plain to see.");
        pipeline.annotate(anotation);
        pipeline.prettyPrint(anotation,System.out);
         */
        //page 29 code
        // Scanner class split by ptrn
        /*
        String ptrn = "[ ,.`]";
        Scanner scan = new Scanner("Let`s pause, and then reflect.");
        scan.useDelimiter(ptrn);
        List<String> list = new ArrayList<String>();
        while(scan.hasNext())
        {
            String token = scan.next();
            list.add(token);
        }
        for (String t : list)
        {
            System.out.println(t);
        }
         */
        //page 30 code
        // split + re
        /*
        String re = "\\s+";
        String text = "Mr.Dalion went to 123 Washiton avenue.";
        String token[] = text.split(re);// split(re,2) present for re twice;
        for (String s : token)
        {
            System.out.println(s);
        }
         */
        // page 31 code
        // BreakIterator [`,. ]
        /*
        BreakIterator wordIterator = BreakIterator.getWordInstance();
        String text = "Let`s pause, and then reflect.";
        wordIterator.setText(text);
        int boundary = wordIterator.first();
        //find last end
        while(boundary != BreakIterator.DONE)
        {
            int begin = boundary;
            System.out.println(boundary + "-");
            boundary = wordIterator.next();
            int end = boundary;
            if(end == BreakIterator.DONE)   break;
            System.out.println(boundary+" ["+text.substring(begin,end)+"]");
        }
         */
        // page 34 code
        // StreamTokenizer not release
        // StringTokenizer will be coded
        /*
        StringTokenizer st = new StringTokenizer("let`s pause, and then reflect.","[ ,.`]"); // overload here to set split symbols
        while(st.hasMoreElements())
        {
            System.out.println(st.nextToken());
        }
        */
    
        /********************
         * use nlp to split word
         ***********************/
        // page 35 code 
        // opennlp Tokenizer
        // simple tokenizer (split `)
        /*
        SimpleTokenizer sT = SimpleTokenizer.INSTANCE;
        String tokens[] = sT.tokenize(para);
        for (String s : tokens)
        {
            System.out.println(s);
        }
        // split by whitespace
        String tokens_whitespace[] = WhitespaceTokenizer.INSTANCE.tokenize(para);
        for (String s : tokens_whitespace)
        {
            System.out.println(s);
        }
        // TokenizerME -> maxent to make sure [ text <-> data ]
        try(InputStream is = new FileInputStream(new File("/home/dalion/nlptest/","en-token.bin")))
        {
            TokenizerModel model = new TokenizerModel(is);
            Tokenizer tokenizer = new TokenizerME(model);
            String tokens_me[] = tokenizer.tokenize(para);
            for (String i : tokens_me)
            {
                System.out.println(i);
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("FIlenotefound");
        }
        catch(IOException ex)
        {
            System.out.println("IOexception");
        }
        */
        /****************
         * stanford nlp api
         ****************/
        //page 38 code 
        // PTBTokenizer(Reader,LexedTokenFactory<T>,options)
        //PTBTokenizer ptb = new PTBTokenizer(new StringReader(para),new CoreLabelTokenFactory(),null);// CoreLabelFactory <-> WordTokenFactory
        //while(ptb.hasNext())
        //{
        //    System.out.println(ptb.next());
        //}
        /*
        CoreLabelTokenFactory ctf = new CoreLabelTokenFactory();
        PTBTokenizer ptb = new PTBTokenizer(new StringReader(para),ctf,"invertible=true");
        while(ptb.hasNext())
        {
            CoreLabel cl = (CoreLabel)ptb.next();
            System.out.println(cl.originalText() + " (" + cl.beginPosition() + "-" + cl.endPosition() + ")");
        }
        
        // DocumentPreprocessor class
        Reader reader = new StringReader(para);
        DocumentPreprocessor documentpreprocessor = new DocumentPreprocessor(reader);
        Iterator<List<HasWord>> it = documentpreprocessor.iterator();
        while(it.hasNext())
        {
            List<HasWord> st = it.next();
            for (HasWord token : st)
            {
                System.out.println(token);
            }
        }
        */
        // Pipeline
        /* word begin end
        Properties properties = new Properties();
        properties.put("annotators","tokenize,ssplit");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        Annotation anotation = new Annotation("the meaning and purpose of life is plain to see.");
        pipeline.annotate(anotation);
        pipeline.prettyPrint(anotation,System.out);
        */
        
        /*******
         * LingPipe
         */
        /*
        char text2[] = para.toCharArray();
        TokenizerFactory tF = IndoEuropeanTokenizerFactory.INSTANCE;
        Tokenizer tokenizer = tF.tokenizer(text2, 0, text2.length);
        for (String s : tokenizer)
        {
            System.out.println(s);
        }
        */
        // Train to split word
        // *********************
        //**********************
        /*
        BufferedOutputStream mOutputStream = null;
        try
        {
            
            InputStreamFactory in = new MarkableFileInputStreamFactory(new File("/home/dalion/nlptest/train.train"));
            ObjectStream<String> lineStream = new PlainTextByLineStream(in,"UTF-8");
            
            ObjectStream<TokenSample> sampleStream = new TokenSampleStream(lineStream);
            TokenizerFactory factory = new TokenizerFactory("en",null,true,null);
            TrainingParameters mlParams = new TrainingParameters();
            mlParams.put(TrainingParameters.CUTOFF_PARAM, 5);
            mlParams.put(TrainingParameters.ITERATIONS_PARAM, 100);
            TokenizerModel model = TokenizerME.train(sampleStream, factory, mlParams);
            
            mOutputStream = new BufferedOutputStream( new FileOutputStream(new File("/home/dalion/nlptest/mymodel.bin")));
            model.serialize(mOutputStream);
            
        }
        catch(UnsupportedEncodingException ex)
        {
            ex.printStackTrace();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
        */
        /***
         * 标准化处理
         * 去除待消词 java方法不进行code
         * code lingpipe方法
         */
        /*
        String p = "A simple apparoach is to create a class to hold and remove stopwords";
        p = p.toLowerCase(); // to low case
        TokenizerFactory factory = IndoEuropeanTokenizerFactory.INSTANCE;
        factory = new EnglishStopTokenizerFactory(factory);
        Tokenizer tokenizer = factory.tokenizer(p.toCharArray(), 0, p.length());
        for (String s : tokenizer)
        {
            System.out.println(s);
        }
        */
        /************
         * 词干化
         */
        /*
        String words[] = {"bank","banking","banks","banker","banked","bankart"};
        PorterStemmer ps = new PorterStemmer();
        for (String word : words)
        {
            String stem = ps.stem(word);
            print("Word: "+word+" Stem:" + stem);
        }
        // Lingpipe
        
        TokenizerFactory tokenizerFactory = IndoEuropeanTokenizerFactory.INSTANCE;
        TokenizerFactory porterFactory = new PorterStemmerTokenizerFactory(tokenizerFactory);
        String words[] = {"bank","banking","banks","banker","banked","bankart"};
        String[] stems = new String[words.length];
        for ( int i=0;i<words.length;i++)
        {
            Tokenization tokenizer = new Tokenization(words[i],porterFactory);
            stems = tokenizer.tokens();
            print("Word: "+words[i]);
            for(String stem : stems)
            {
                print(" Stem: "+stem);
            }
        }
        */
        /************
         * 词型还原
         */    
        // StanfordLemmatizer
        /*
        StanfordCoreNLP pipeline;
        Properties props = new Properties();
        props.put("annotators","tokenize,ssplit,pos,lemma");
        pipeline = new StanfordCoreNLP(props);
        String p = "Similar to stemming is lemmatization. This is the process of finding its lemma, its form as found in a dictionary.";
        Annotation document = new Annotation(p);
        pipeline.annotate(document);
        List<CoreMap> sent = document.get(SentencesAnnotation.class);
        List<String> lemmas = new LinkedList<>();
        for (CoreMap Sentence : sent)
        {
            for(CoreLabel word : Sentence.get(TokensAnnotation.class))
            {
                lemmas.add(word.get(LemmaAnnotation.class));
            }
        }
        
        for (String s : lemmas)
        {
            System.out.print(s+" ");
        }
        */
        //opennlp
        //不进行实现 效果暂时不如斯坦福nlp好
        //page 54 流水线
        /*
        String p = "A simple apparoach is to create a class to hold and remove stopwords";
        TokenizerFactory factory = IndoEuropeanTokenizerFactory.INSTANCE;
        factory = new LowerCaseTokenizerFactory(factory);
        
        factory = new EnglishStopTokenizerFactory(factory);
        factory = new PorterStemmerTokenizerFactory(factory);       
        Tokenizer tokenizer = factory.tokenizer(p.toCharArray(), 0, p.length());
        for (String token : tokenizer)
        {
            print(token);
        }
        */     
 
    }
}
