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
 		
 		System.out.println("1�?"); 
 		StuList class1 = new StuList("C:\\student.txt"); 
 		class1.showList(); 
 		 
 		System.out.println("2�?"); 
 		StuList class2 = new StuList("C:\\student2.txt"); 
 		class2.showList(); 
 		
 		System.out.println("?��?��?��?��"); 
 		class1.sortList(); 
 		class2.sortList(); 
 		System.out.println("1�?"); 
 		class1.showList(); 
 		System.out.println("2�?"); 
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
 	//abstract = ?��?��?��?�� ?��?�� 불�? 
} 
 
 
class DomeStudent extends Student{//�??��?��?�� 
 	private String resiId;//주�?�번?�� 
 
 	public DomeStudent(String name, String stuId, String resiId, int score){ 
 		super(name, stuId, score);  		 		
 		this.resiId=resiId; 
 	} 
 	public void showInfo(){ 
 		System.out.println("?���??�� name => "+ getName() + " stuId => "+getStuId()+" resiId => "+resiId+" score = "+super.getScore()+" ranking = "+ super.getRank()); 
 	} 
} 
 
 
class ForeStudent extends Student{//�??��?��?�� 
 	private String foreignId;//주�?�번?�� 
 	public ForeStudent(String name, String stuId, String foreignId, int score){ 
 		super(name, stuId, score); 
 		this.foreignId=foreignId; 
 	} 
 	public void showInfo(){ 
 		System.out.println("?���??�� name => "+ getName() + " stuId => "+getStuId()+" resiId => "+foreignId+" score = "+super.getScore()+" ranking = "+ super.getRank()); 
 	} 
} 


 
 class StuList{ 
	
 	List<Student> stuList = new ArrayList<Student>(); 
 	
 	public StuList(String dir){ 
 		initStudent(dir); 
 		setRanking(); 
 	} 
 	
 	public void initStudent(String dir){ 
 		// ?��?�� �??�� file?�� 만든?�� 
 		File file = new File(dir); 
     	try { 
     		// BufferedReader �??��?�� file?�� ?��?��?�� 
     		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"euc-kr")); 
  
 
         	// ?��?��?�� ?��줄씩 ?��?�� ?���? ?��?�� �??�� line 
         	String line = null; 
 
 
         	// ?��?��?�� line?�� split ?��?�� ?��?�� 배열 splitedStr 
         	String[] splitedStr = null; 
         	int score=0; 
 
 
         	// ?�� 줄씩 ?��?��?�� line?�� ?��?? ?�� null?�� ?��?���? ?��?�� 
         	while( (line = reader.readLine()) != null ) { 
 
 
             	// 초기?�� 
             	splitedStr = null; 
 
 
             	// ?��?�� 기�??���? ?��?��?�� splitedStr ?�� ?��?��?�� 
             	splitedStr = line.split("\t"); 
             	
 
             	// 배열?�� ?��?���? 길이 만큼 반복?��?�� 
             	for (int i = 0; i < splitedStr.length; i++) { 
             		if(i==3){ 
             			score = Integer.valueOf(splitedStr[3]);//?��?��?�� int�? 
             		}else{ 
             			// ?��쪽의 공백?�� ?��거하�? ?��?�� ?��?��?��?�� 
             			splitedStr[i] = splitedStr[i].trim(); 
             		} 
                     // splitedStr ?�� List<Class�?>?�� ?��?��?��?�� ?�� ?��?���??��?��?�� 
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
             for (int j = 0; j < stuList.size(); j++) { //기�??��?��?��?? ?��머�??��?��?��비교                              
                 if(  stuList.get(i).getScore()<stuList.get(j).getScore()        ){   //기�??��?��?���? ?��머�??��?��?��?�� 비교?��?��?�� ?��?���? 카운?�� 
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


