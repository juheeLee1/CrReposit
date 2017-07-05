package paran.team;

import java.io.BufferedReader; 
 import java.io.File; 
 import java.io.FileInputStream; 
 import java.io.FileNotFoundException; 
 import java.io.IOException; 
 import java.io.InputStreamReader; 
 import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List; 
 
 
public class ClassInfo2{ 
	 
 	public static void main(String[] args){ 
 		
 		System.out.println("1ë°?"); 
 		StuList class1 = new StuList("C:\\student.txt"); 
 		class1.showList(); 
 		 
 		System.out.println("2ë°?"); 
 		StuList class2 = new StuList("C:\\student2.txt"); 
 		class2.showList(); 
 		
 		System.out.println("?­?‚¹? •? ¬"); 
 		class1.sortList(); 
 		class2.sortList(); 
 		System.out.println("1ë°?"); 
 		class1.showList(); 
 		System.out.println("2ë°?"); 
 		class2.showList(); 
 		
 	} 
 	
} 
 
 
 
abstract class Student{ 
 	
	private String name; 
 	private String stuId; 
 	private int score; 
 	private int rank=1; 
 	
 	
 	public Student(String name, String stuId, int score){ 
 		this.name = name; 
 		this.stuId = stuId; 
 		this.score = score; 
 	} 
 	
 	public String getName() { 
 		return name; 
 	} 
 	public void setName(String name) { 
 		this.name = name; 
 	} 
 	public String getStuId() { 
 		return stuId; 
 	} 
 	public void setStuId(String stuId) { 
 		this.stuId = stuId; 
 	} 
 	public int getScore() { 
 		return score; 
 	} 
 	public void setScore(int score) { 
 		this.score = score; 
 	} 
 	public int getRank() { 
 		return rank; 
 	} 
 	public void setRank(int rank) { 
 		this.rank = rank; 
 	} 
 	public abstract void showInfo(); 
 	//abstract = ?¸?Š¤?„´?Š¤ ?ƒ?„± ë¶ˆê? 
} 
 
 
class DomeStudent extends Student{//êµ??‚´?•™?ƒ 
 	private String resiId;//ì£¼ë?¼ë²ˆ?˜¸ 
 
 	public DomeStudent(String name, String stuId, String resiId, int score){ 
 		super(name, stuId, score);  		 		
 		this.resiId=resiId; 
 	} 
 	public void showInfo(){ 
 		System.out.println("?‚´êµ??¸ name => "+ getName() + " stuId => "+getStuId()+" resiId => "+resiId+" score = "+super.getScore()+" ranking = "+ super.getRank()); 
 	} 
} 
 
 
class ForeStudent extends Student{//êµ??™¸?•™?ƒ 
 	private String foreignId;//ì£¼ë?¼ë²ˆ?˜¸ 
 	public ForeStudent(String name, String stuId, String foreignId, int score){ 
 		super(name, stuId, score); 
 		this.foreignId=foreignId; 
 	} 
 	public void showInfo(){ 
 		System.out.println("?™¸êµ??¸ name => "+ getName() + " stuId => "+getStuId()+" resiId => "+foreignId+" score = "+super.getScore()+" ranking = "+ super.getRank()); 
 	} 
} 


 
 class StuList{ 
	
 	List<Student> stuList = new ArrayList<Student>(); 
 	
 	public StuList(String dir){ 
 		initStudent(dir); 
 		setRanking(); 
 	} 
 	
 	public void initStudent(String dir){ 
 		// ?ŒŒ?¼ ë³??ˆ˜ file?„ ë§Œë“ ?‹¤ 
 		File file = new File(dir); 
     	try { 
     		// BufferedReader ë³??ˆ˜?— file?„ ?„£?Š”?‹¤ 
     		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"euc-kr")); 
  
 
         	// ?ŒŒ?¼?„ ?•œì¤„ì”© ?½?–´ ?„£ê¸? ?œ„?•œ ë³??ˆ˜ line 
         	String line = null; 
 
 
         	// ?•˜?‚˜?˜ line?„ split ?•˜?—¬ ?„£?„ ë°°ì—´ splitedStr 
         	String[] splitedStr = null; 
         	int score=0; 
 
 
         	// ?•œ ì¤„ì”© ?½?–´?„œ line?— ?„£?? ?›„ null?´ ?•„?‹ˆë©? ?‹¤?–‰ 
         	while( (line = reader.readLine()) != null ) { 
 
 
             	// ì´ˆê¸°?™” 
             	splitedStr = null; 
 
 
             	// ?ƒ­?„ ê¸°ì??œ¼ë¡? ?˜?¼?„œ splitedStr ?— ?„£?Š”?‹¤ 
             	splitedStr = line.split("\t"); 
             	
 
             	// ë°°ì—´?— ?“¤?–´ê°? ê¸¸ì´ ë§Œí¼ ë°˜ë³µ?•œ?‹¤ 
             	for (int i = 0; i < splitedStr.length; i++) { 
             		if(i==3){ 
             			score = Integer.valueOf(splitedStr[3]);//? ?ˆ˜?Š” intë¡? 
             		}else{ 
             			// ?–‘ìª½ì˜ ê³µë°±?„ ? œê±°í•˜ê³? ?‹¤?‹œ ?…? ¥?•œ?‹¤ 
             			splitedStr[i] = splitedStr[i].trim(); 
             		} 
                     // splitedStr ?„ List<Classëª?>?— ?…? ¥?•˜?Š” ?“± ?´?š©ê°??Š¥?•˜?‹¤ 
             	} 
             	if(splitedStr[2].substring(0,1).equals("1")){ 
             		stuList.add(new DomeStudent(splitedStr[0],splitedStr[1],splitedStr[2],score)); 
             	}else 
             		stuList.add(new ForeStudent(splitedStr[0],splitedStr[1],splitedStr[2],score)); 
         	} 			                             //name,          stuId,    ForignNum,   Score
         	reader.close(); 
 
         
     	} catch (FileNotFoundException fnf) { 
     		fnf.printStackTrace(); 
     	} catch( IOException e) { 
         	e.printStackTrace(); 
     	} 
 	} 
 	public void setRanking(){ 
 		
 		for(int i=0; i<stuList.size(); i++){             
             for (int j = 0; j < stuList.size(); j++) { //ê¸°ì??°?´?„°?? ?‚˜ë¨¸ì??°?´?„°ë¹„êµ                              
                 if(  stuList.get(i).getScore()<stuList.get(j).getScore()        ){   //ê¸°ì??°?´?„°ê°? ?‚˜ë¨¸ì??°?´?„°?¼ ë¹„êµ?–ˆ?„?•Œ ? ?œ¼ë©? ì¹´ìš´?Š¸ 
                     stuList.get(i).setRank(stuList.get(i).getRank()+1); //COUNT                  
                 }               
             }           
         }
 		
 	} 
 	public void showList(){ 
 		for(Student list : stuList){ 
 			list.showInfo(); 
 		} 
 	}
 	public void sortList(){ 
 		Collections.sort(this.stuList, new Comparator<Student>(){ 
 			public int compare(Student obj1, Student obj2) 
 			{ 
 				return (obj1.getRank() < obj2.getRank()) ? -1: (obj1.getRank() > obj2.getRank()) ? 1:0 ; 
 			} 
 		});  
 	} 
 } 


