import java.io.*;
import java.util.*;

/**
 * Created by miren_t on 3/31/2015.
 */
public class IR3 {
    public static HashMap<String, Integer> queryIndex= new HashMap<String, Integer>();
    static TreeMap<String, TreeMap<Integer, Integer>> index= new TreeMap<String, TreeMap<Integer, Integer>>();
    static int documentInfo[][] ;
    static HashMap<Integer, Double> docsW1= new HashMap<Integer, Double>();
    static HashMap<Integer, Double> docsW2= new HashMap<Integer, Double>();
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        String stopwordsFile="stopwords";
        String queryFile= "hw3.queries";
        String indexFile= "Index_Version1.uncompressed";
        String documentsInfo= "documentsInfo";
        try {
            TreeMap<String, String> stopwordsMap= getStopWords(stopwordsFile);
            String[] queries= getQueryFile(queryFile);

            //TODO: update document info file with lemmas from IR2!
            //currently using stemmed document info
            readIndex(indexFile);
            readDocInfo(documentsInfo);
            for(String query: queries){

                if(query.length()>0){
                indexQuery(query, stopwordsMap);
                //Todo: generate ranking
                generateRanking(index, documentInfo);
                    //TODO: update W1 and W2 based on df clarification
                }
            }
  //          System.out.println(docsW1);
            System.out.println("end");

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @SuppressWarnings("unchecked")
    private static void readDocInfo(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream= new FileInputStream(new File(filename));
        ObjectInputStream objectInputStream= new ObjectInputStream(fileInputStream);
        documentInfo= (int[][]) objectInputStream.readObject();
        objectInputStream.close();
    }

    @SuppressWarnings("unchecked")
    private static void generateRanking(TreeMap<String, TreeMap<Integer, Integer>> index, int[][] documentInfo){
        for(int i=1; i<documentInfo.length; i++){
            System.out.print(i + " --> ");
            generateW1(index, i);
            generateW2(index, i);
        }
    }

    @SuppressWarnings("unchecked")
    private static void generateW1(TreeMap<String, TreeMap<Integer, Integer>> index, int docID){
        int maxFreq= documentInfo[docID][1];
        int collectionSize= documentInfo.length;//redundant
        double w1= 0.0d;
        for(String query: queryIndex.keySet()){
            if(query.length()>0){
                int tf= termFreq(query, docID, index);
                int df= documentFrequency(query, index);
                if(df!=0)
                    w1+=(0.4d + 0.6d * Math.log(tf + 0.5d) / Math.log(maxFreq + 1.0d)) * (Math.log(collectionSize/df)/Math.log(collectionSize));
                else
                    w1+=0;
            }
        }
        docsW1.put(docID, w1);
        System.out.print(w1 + " --> ");
    }

    @SuppressWarnings("unchecked")
    private static void generateW2(TreeMap<String, TreeMap<Integer, Integer>> index, int docID){
        int doclen= documentInfo[docID][0];
        int collectionSize= documentInfo.length;//redundant
        double w2= 0.0d;
        for(String query: queryIndex.keySet()) {
            if (query.length() > 0) {
                int tf = termFreq(query, docID, index);
                int df = documentFrequency(query, index);
                if(df!=0)
                    w2 += (0.4 + 0.6 * (tf / (tf + 0.5 + 1.5 * (doclen / avgdoclen(index)))) * (Math.log(collectionSize / df) / Math.log(collectionSize)));
                else
                    w2+=0;
            }
        }
        docsW2.put(docID, w2);
        System.out.print(w2 + "\n");
    }

    @SuppressWarnings("unchecked")
    private static double avgdoclen(TreeMap<String, TreeMap<Integer, Integer>> index){
        double avg=0.0;
        for(int i=1; i<documentInfo.length; i++)
            avg+=documentInfo[i][0];
        return avg/index.size();
    }

    @SuppressWarnings("unchecked")
    private static int termFreq(String term, int docID, TreeMap<String, TreeMap<Integer, Integer>> index){
        TreeMap<Integer, Integer> posting= index.get(term);
        int count=0;
        if(posting!=null && posting.containsKey(docID)){
            count= posting.get(docID);
        }
        return count;
    }

    @SuppressWarnings("unchecked")
    private static int documentFrequency(String term, TreeMap<String, TreeMap<Integer, Integer>> index){
        TreeMap<Integer, Integer> posting= index.get(term);
        if(posting!=null)
            return posting.size();
        return 0;
    }

    @SuppressWarnings("unchecked")
    private static void readIndex(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream= new FileInputStream(new File(filename));
        ObjectInputStream objectInputStream= new ObjectInputStream(fileInputStream);
        index= (TreeMap<String, TreeMap<Integer, Integer>>) objectInputStream.readObject();
        objectInputStream.close();
    }

    @SuppressWarnings("unchecked")
    private static void indexQuery(String query, TreeMap<String, String> stopwordsMap) throws IOException {
        String tokens[]= query.replaceAll("[^a-zA-Z0-9'.]", " ").replaceAll("\\s+", " ").trim().split(" ");
        ArrayList<String> normalizedTokens= new ArrayList<String>();
        for (int i = 0; i < tokens.length; i++) {
            if(tokens[i].length()>0)
                normalizedTokens.add(tokens[i].trim());
        }
        HashMap<String, Integer> tokensInFile= new HashMap<String, Integer>();
        for(String normalizedToken: normalizedTokens){
            ArrayList<String> noPuncTokens= removePunctuations(normalizedToken);
            if(noPuncTokens!=null){
                for(String noPuncToken: noPuncTokens){
                    if (tokensInFile.get(noPuncToken) == null)
                        tokensInFile.put(noPuncToken, 1);
                    else
                        tokensInFile.put(noPuncToken, tokensInFile.get(noPuncToken) + 1);
                }
            }
        }
        HashMap<String, Integer> lemmaHashMap= new Lemmatizer().lemmatizeHash(tokensInFile);
        //System.out.println(lemmaHashMap);
        //HashMap<String, Integer> lemmaNormalizedHashMap= removeStopwords(lemmaHashMap, stopwordsMap);
        queryIndex= removeStopwords(lemmaHashMap, stopwordsMap);
    }

    private static HashMap<String, Integer> removeStopwords(HashMap<String, Integer> lemmaHashMap, TreeMap<String, String> stopwords) throws FileNotFoundException {
        Iterator<Map.Entry<String, Integer>> lemmaIterator= lemmaHashMap.entrySet().iterator();
        HashMap<String, Integer> lemmaNormalizedHashMap= new HashMap<String, Integer>();
        while(lemmaIterator.hasNext()){
            Map.Entry<String, Integer> lemmaMap= lemmaIterator.next();
            if(!stopwords.containsKey(lemmaMap.getKey())){
                lemmaNormalizedHashMap.put(lemmaMap.getKey(), lemmaMap.getValue());
            }
        }
        return lemmaNormalizedHashMap;
    }

    public static ArrayList<String> removePunctuations(String tokens){
        ArrayList<String> noPuncTokens= new ArrayList<String>();
        ArrayList<String> noPuncTokensCopy= new ArrayList<String>();
        if(tokens.contains("-")){
            String noHyphens[]= tokens.split("-");
            for(String noH: noHyphens){
                noPuncTokens.add(noH.toLowerCase());
            }
        }
        else
            noPuncTokens.add(tokens.toLowerCase());

        for(String token: noPuncTokens){
            if(token.contains(".") && token.length()>1){
                noPuncTokensCopy.add(token.replaceAll("\\.", ""));
            }
            else if(!token.contains("."))
                noPuncTokensCopy.add(token.toLowerCase());
        }
        noPuncTokens.clear();

        for(String token: noPuncTokensCopy){
            if(token.endsWith("'s")){
                noPuncTokens.add(token.replace("'s", "").trim());
            }
            else if(token.contains("'") && token.length()>1){
                noPuncTokens.add(token.replace("'", ""));
            }
            else if(!token.contains("'"))
                noPuncTokens.add(token.toLowerCase());
        }
        return noPuncTokens;
    }

    private static String[] getQueryFile(String file) throws FileNotFoundException {
        LinkedHashSet<String> queries= new LinkedHashSet<String>();
        Scanner readFile= new Scanner(new File(file));
        StringBuilder sb= new StringBuilder();
        while(readFile.hasNextLine()){
            String line=readFile.nextLine();
  //          System.out.println(line);
            if(line.length()>0)
            {
                sb.append(line).append("");
 //               System.out.println(sb);
            }

//            if(!line.matches("Q[0-9:]+")){
 //               queries.add(line);
 //           }
        }
        return sb.toString().split("Q[0-9:]+");
    }
    public static TreeMap<String, String> getStopWords(String file) throws FileNotFoundException {
        TreeMap<String, String> stopwords= new TreeMap<String, String>();
        Scanner readFile= new Scanner(new File(file));
        while(readFile.hasNext()){
            String line= readFile.nextLine();
            stopwords.put(line, line);
        }
        return stopwords;
    }

}
