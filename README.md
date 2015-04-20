# Information-Retrieval-Ranked-Retrieval
Program to implement a simple statistical retrieval system, using the compressed index built for the Cranfield database
collection.

Part of **CS 6322 Information Retrieval** coursework.

###IR3.java
Includes code to read and parse a query, discard stopwords, generate lemmas and determine scores for documents against
the queries by summing the weights for every matching query-document term pair. The location of the documents, stopwords
file,  query file, uncompressed index file, document info file are to be passed to the program.

###Lemmatizer.java
Includes code to process the Cranfield documents into lemmas. A file is passed to Lemmatizer and using Stanford CoreNLP
methods, lemmas are returned to the calling program, which in this case is IR3.java.

###hw3.queries
File containing queries. Each query is run against the data present in the index.

###stopwords
Contains list of stopwords. These words are considered are irrelevant compared to other words in the document.

###Index_Version1.uncompressed
Uncompressed index made from Cranfield documents. Consists of lemmas, document frequency and other related metrics.

###documentsInfo2
Contains data about each document, used for calculating ranks.

###compile
Includes statements to compile the main program.

###run
Includes statements to run the main program. The location of Cranfield documents is passed to the program.

###Formulas used:
![W1](http://www.sciweavers.org/download/Tex2Img_1429490046.jpg)<br><br>
![W2](http://www.sciweavers.org/download/Tex2Img_1429490842.jpg)

###Relevant and Non-Relevant Queries:
![Q1](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q1.PNG)<br>
**Q1**: Original query: what similarity laws must be obeyed when constructing aeroelastic models of heated high speed aircraft.
Relevant Documents:<br>
1.	DOC ID - 51<br>
2.	DOC ID - 486<br>
3.	DOC ID - 573<br>
Non-relevant Documents<br>
1.	DOC ID - 329<br>
2.	DOC ID - 13<br>
The top ranked documents such as DOC ID 329, 13 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q2](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q2.PNG)<br>
**Q2**: what are the structural and aeroelastic problems associated with flight of high speed aircraft
Relevant Documents<br>
1.	DOC ID - 12<br>
2.	DOC ID - 746<br>
3.	DOC ID - 875<br>
Non-relevant Documents<br>
1.	DOC ID - 51<br>
2.	DOC ID - 14<br>
3.	DOC ID - 884<br>
The top ranked documents such as DOC ID 14, 51, 884 are ranked higher though they are non-relevant because these
documents contain higher Term Frequency (TF) of the terms present in the query.

![Q3](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q3.PNG)<br>
**Q3**: what problems of heat conduction in composite slabs have been solved so far.
Relevant Documents<br>
1.	DOC ID - 144<br>
2.	DOC ID - 485<br>
3.	DOC ID - 5<br>
Non-relevant Documents<br>
1.	DOC ID - 181<br>
2.	DOC ID - 91<br>
The top ranked documents such as DOC ID 181, 91 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q4](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q4.PNG)<br>
**Q4**: can a criterion be developed to show empirically the validity of flow solutions for chemically reacting gas mixtures
based on the simplifying assumption of instantaneous local chemical equilibrium
Relevant Documents<br>
1.	DOC ID - 166<br>
2.	DOC ID - 236<br>
3.	DOC ID - 488<br>
Non-relevant Documents<br>
1.	DOC ID - 1061<br>
2.	DOC ID - 185<br>
3.	DOC ID - 1275<br>
The top ranked documents such as DOC ID 1061, 185, 1275 are ranked higher though they are non-relevant because these
documents contain higher Term Frequency (TF) of the terms present in the query.

![Q5](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q5.PNG)<br>
**Q5**: what chemical kinetic system is applicable to hypersonic aerodynamic problems
Relevant Documents<br>
1.	DOC ID - 401<br>
2.	DOC ID - 625<br>
3.	DOC ID - 1296<br>
Non-relevant Documents<br>
1.	DOC ID - 103<br>
2.	DOC ID - 1032<br>
The top ranked documents such as DOC ID 103, 1032 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q6](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q6.PNG)<br>
**Q6**: what theoretical and experimental guides do we have as to turbulent couette flow behaviour
Relevant Documents<br>
1.	DOC ID - 798<br>
2.	DOC ID - 315<br>
3.	DOC ID - 491<br>
Non-relevant Documents<br>
1.	DOC ID - 130<br>
2.	DOC ID - 914<br>
The top ranked documents such as DOC ID 130, 914 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q7](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q7.PNG)<br>
**Q7**: is it possible to relate the available pressure distributions for an ogive forebody at zero angle of attack to the
lower surface pressures of an equivalent ogive forebody at angle of attack
Relevant Documents<br>
1.	DOC ID - 492<br>
2.	DOC ID - 122<br>
Non-relevant Documents<br>
1.	DOC ID - 57<br>
2.	DOC ID - 56<br>
The top ranked documents such as DOC ID 492, 122 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q8](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q8.PNG)<br>
**Q8**: what methods -dash exact or approximate -dash are presently available for predicting body pressures at angle of attack
Relevant Documents<br>
1.	DOC ID - 688<br>
2.	DOC ID - 122<br>
3.	DOC ID - 947<br>
4.	DOC ID - 69<br>
Non-relevant Documents<br>
1.	DOC ID - 433<br>
2.	DOC ID - 234<br>
The top ranked documents such as DOC ID 433, 234 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q9](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q9.PNG)<br>
**Q9**: papers on internal /slip flow/ heat transfer studies
Relevant Documents<br>
1.	DOC ID - 270<br>
2.	DOC ID - 21<br>
3.	DOC ID - 22<br>
Non-relevant Documents<br>
1.	DOC ID - 571<br>
2.	DOC ID - 306<br>
The top ranked documents such as DOC ID 571, 306 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q10](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q10.PNG)<br>
**Q10**: are real-gas transport properties for air available over a wide range of enthalpies and densities
Relevant Documents<br>
1.	DOC ID - 302<br>
2.	DOC ID - 949<br>
3.	DOC ID - 405<br>
Non-relevant Documents<br>
1.	DOC ID - 493<br>
2.	DOC ID - 1199<br>
The top ranked documents such as DOC ID 493, 1199 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q11](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q11.PNG)<br>
**Q11**: is it possible to find an analytical, similar solution of the strong blast wave problem in the newtonian approximation
Relevant Documents<br>
1.	DOC ID - 495<br>
2.	DOC ID - 1327<br>
3.	DOC ID - 72<br>
Non-relevant Documents<br>
1.	DOC ID - 1356<br>
2.	DOC ID - 556<br>
The top ranked documents such as DOC ID 1356, 556 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q12](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q12.PNG)<br>
**Q12**: how can the aerodynamic performance of channel flow ground effect machines be calculated
Relevant Documents<br>
1.	DOC ID - 624<br>
2.	DOC ID - 966<br>
3.	DOC ID - 650<br>
Non-relevant Documents<br>
1.	DOC ID - 1209<br>
2.	DOC ID - 650<br>
The top ranked documents such as DOC ID 1209, 650 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q13](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q13.PNG)<br>
**Q13**: what is the basic mechanism of the transonic aileron buzz
Relevant Documents<br>
1.	DOC ID - 496<br>
2.	DOC ID - 313<br>
Non-relevant Documents<br>
1.	DOC ID - 520<br>
2.	DOC ID - 38<br>
The top ranked documents such as DOC ID 520, 38 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q14](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q14.PNG)<br>
**Q14**: papers on shock-sound wave interaction
Relevant Documents<br>
1.	DOC ID - 64<br>
2.	DOC ID - 256<br>
Non-relevant Documents<br>
1.	DOC ID - 132<br>
2.	DOC ID - 402<br>
The top ranked documents such as DOC ID 132, 402 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q15](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q15.PNG)<br>
**Q15**: material properties of photoelastic materials
Relevant Documents<br>
1.	DOC ID - 462<br>
2.	DOC ID - 463<br>
Non-relevant Documents<br>
1.	DOC ID - 1025<br>
2.	DOC ID - 1099<br>
The top ranked documents such as DOC ID 1025, 1099 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q16](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q16.PNG)<br>
**Q16**: can the transverse potential flow about a body of revolution be calculated efficiently by an electronic computer
Relevant Documents<br>
1.	DOC ID - 498<br>
2.	DOC ID - 927<br>
Non-relevant Documents<br>
1.	DOC ID - 1006<br>
2.	DOC ID - 1255<br>
The top ranked documents such as DOC ID 1006, 1255 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q17](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q17.PNG)<br>
**Q17**: can the three-dimensional problem of a transverse potential flow about a body of revolution be reduced to a two-dimensional problem
Relevant Documents<br>
1.	DOC ID - 1108<br>
2.	DOC ID - 927<br>
Non-relevant Documents<br>
1.	DOC ID - 266<br>
2.	DOC ID - 1036<br>
The top ranked documents such as DOC ID 266, 1036 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q18](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q18.PNG)<br>
**Q18**: are experimental pressure distributions on bodies of revolution at angle of attack available
Relevant Documents<br>
1.	DOC ID - 197<br>
2.	DOC ID - 234<br>
3.	DOC ID - 248<br>
4.	DOC ID - 56<br>
Non-relevant Documents<br>
1.	DOC ID - 1352<br>
2.	DOC ID - 106<br>
The top ranked documents such as DOC ID 1355, 106 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q19](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q19.PNG)<br>
**Q19**: does there exist a good basic treatment of the dynamics of re-entry combining consideration of realistic effects with relative simplicity of results
Relevant Documents<br>
1.	DOC ID - 1348<br>
2.	DOC ID - 927<br>
Non-relevant Documents<br>
1.	DOC ID - 706<br>
2.	DOC ID - 1313<br>
The top ranked documents such as DOC ID 706, 1313 are ranked higher though they are non-relevant because these documents
contain higher Term Frequency (TF) of the terms present in the query.

![Q20](https://github.com/tannamiren/Weighted-Retrieval/blob/master/output/Q20.PNG)<br>
**Q20**: has anyone formally determined the influence of joule heating, produced by the induced current, in magneto hydrodynamic free convection flows under general conditions
Relevant Documents<br>
1.	DOC ID - 500<br>
2.	DOC ID - 270<br>
Non-relevant Documents<br>
1.	DOC ID - 1201<br>
2.	DOC ID - 450<br>
3.	DOC ID - 104<br>
The top ranked documents such as DOC ID 1201, 450, 104 are ranked higher though they are non-relevant because these
documents contain higher Term Frequency (TF) of the terms present in the query.

####How are the two weighting schemes different from each other?
W1 focuses more on the max_tf so if a term is present many times in the document it increases the weight of the document
drastically. In contrast, W2 has focuses more on the document length and average doc length. It increases if the document
length is less and decreases if the document length is more by considering that average doc length is constant trough the
collection. It also depends of tf to an extent. So chance of finding the relevant document is high if doc length is low.


###Program Description:
