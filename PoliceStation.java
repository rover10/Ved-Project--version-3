import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement;  
import java.util.Scanner;

public class PoliceStation {  
    public static void main(String[] args) {  
		Data d=new Data();
		//d.newFIR();
		//d.criminalRecord();
		//d.updateCriminalProfile();
		d.searchRecord();
	}
} 


 class Data{
 boolean searchRecord(){ // search record with the provided detail 
	   try {  
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());  
            Connection connection = DriverManager.getConnection(  
                    "jdbc:oracle:thin:@localhost:1521:XE", "system", "tiger");  
		
			System.out.println("------------------------------\n\tWelcome to Record Search Engine\n\t------------------------------");
            Statement statement = connection.createStatement();  
			ResultSet resultSet;
			Scanner sc=new Scanner(System.in);
			System.out.println("What information do you have ??\n");
			System.out.println(" CRIMINAL NAME: 1\n CRIME TYPE:2 \n FIR NUMBER: 3 \n CRIMINAL ID: 4\n CRIME PLACE OR AREA : 5\n");
            System.out.print("\nEnter Choice: ");
			int choice=0;
			long  searchNumber;
			String searchStr="";
			choice=sc.nextInt();
			
			switch (choice){
			case 1:
				System.out.println(" NAME TYPE\n ----------------");
				System.out.print(" FIRST NAME: 1 \n MIDDLE NAME 2\n LAST NAME: 3 \n Please Enter your choice ");
				choice=sc.nextInt();
				System.out.print("\n Enter NAME: ");
				searchStr=sc.next();
				resultSet=null;
				if(choice==1)
					resultSet= statement.executeQuery("SELECT * from CRIMINAL_RECORD WHERE INSTR(FNAME,'"+searchStr+"')>0");
				else if(choice==2)
					resultSet= statement.executeQuery("SELECT *FROM CRIMINAL_RECORD WHERE INSTR(MNAME,'"+searchStr+"')>"+0);
				else if(choice==3)
					resultSet= statement.executeQuery("SELECT *FROM CRIMINAL_RECORD WHERE INSTR(LNAME,'"+searchStr+"')>"+0);
				else
					System.out.println("Invalid choice ! " +choice);
				while(resultSet.next()){
					System.out.print("\n CRIMINAL ID:" + " " + resultSet.getString("CRIMINAL_ID"));
					System.out.print(" NAME: " + resultSet.getString("fname")+" " +resultSet.getString("mname")+ " " + resultSet.getString("lname"));
					
					Statement statementCrime = connection.createStatement();  
					ResultSet resultSetCrime;
					resultSetCrime=statementCrime.executeQuery("SELECT *FROM CRIME_RECORD WHERE CRIMINAL_ID="+ resultSet.getString("CRIMINAL_ID"));
					while(resultSetCrime.next()){
						System.out.print(" FIR NUMBER: " + resultSetCrime.getString("FIR_NUMBER")+" CRIME: " + resultSetCrime.getString("CRIME") );
					}
					
						
				}
				break;
			case 2:
				System.out.println(" CRIME\n ----------------");
				searchStr=sc.next();
				resultSet=null;
				resultSet= statement.executeQuery("SELECT *FROM CRIME_RECORD WHERE INSTR(CRIME,'"+searchStr+"')>"+0);
				while(resultSet.next()){
					System.out.print("\n CRIMINAL ID:" + " " + resultSet.getString("CRIMINAL_ID"));
					Statement statementCrime = connection.createStatement();  
					ResultSet resultSetCrime;
					resultSetCrime=statementCrime.executeQuery("SELECT *FROM CRIMINAL_RECORD WHERE CRIMINAL_ID="+ resultSet.getString("CRIMINAL_ID"));
					while(resultSetCrime.next()){
					    System.out.print(" NAME: " + resultSetCrime.getString("fname")+" " +resultSetCrime.getString("mname")+ " " + resultSetCrime.getString("lname"));
					
					}
				}
				break;
			case 3:
				resultSet= statement.executeQuery("SELECT first FROM EMPLOYEE");
				break;
			case 4:
				resultSet= statement.executeQuery("SELECT first FROM EMPLOYEE");
				break;
			case 5:
				resultSet= statement.executeQuery("SELECT first FROM EMPLOYEE");
				break;
			default:
				break;
			}	
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	return true;
 }
 
boolean updateCriminalProfile(){
 	    try {  
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());  
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "tiger");  
		    Scanner sc=new Scanner(System.in);
			PreparedStatement pstmt = connection.prepareStatement ("insert into crime_record (criminal_id, fir_number,record_date,crime)values(?,?,?,?)");
			
			java.util.Date date = new java.util.Date();
			long t = date.getTime();
			java.sql.Date record_Date = new java.sql.Date(t);
			int  criminal_id;
			int  fir_number;
			String crime;			
			
			System.out.println("Enter criminal ID");
			criminal_id=sc.nextInt();
			System.out.println("Enter FIR Number");
			fir_number =sc.nextInt();
			//criminal_id=sc.nextInt();
			crime= System.console().readLine("Enter criminal Detail or Type"); // Reading a complete line;  //sc.next();
			
			pstmt.setInt (1, criminal_id);   
			pstmt.setInt(2,fir_number);
			pstmt.setDate(3,record_Date);
			pstmt.setString(4,crime);
			
			pstmt.execute ();
			pstmt.close();  
			connection.close();
			System.out.println("\t--------------------------------\n\tCrime Record Updated !\n\t--------------------------------");
		return true;
    }
	catch(Exception e){
		System.out.println("Exception Occured !"+ e);
		return false;
    }


}

 boolean criminalRecord(){
 // Store criminal record 
 	    try {  
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());  
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "tiger");  
		    Scanner sc=new Scanner(System.in);
			PreparedStatement pstmt = connection.prepareStatement ("insert into criminal_record (criminal_id,fname,lname,mname,father_name,mother_name,paddress,caddress,age,sex,height,weight,colour,contact,staffid) values (?, ?,?,?,?, ?,?,?,?,?,?,?,?, ?,?)");
			
			String sex,fname,lname,mname,father_name,mother_name,paddress,caddress,contact,colour;
			int criminal_id;
			int id,age,staffid;
			float height,weight;
			java.util.Date date = new java.util.Date();
			long t = date.getTime();
			java.sql.Date firDate = new java.sql.Date(t);

			System.out.println("Enter criminal ID");
			criminal_id=sc.nextInt();
			System.out.println("Enter first name:\t\t");
			fname=sc.next();
			System.out.println("Enter last name:\t\t");
			lname=sc.next();
			System.out.println("Enter middle name:\t\t");
			mname=sc.next();
			father_name=System.console().readLine("Enter Father's name:\t");
			mother_name=System.console().readLine("Enter Mother's name:\t");
			paddress= System.console().readLine("Enter Permanent Address: "); // Reading a complete line;  //sc.next();
			caddress= System.console().readLine("Enter Correspondence Address: "); // Reading a complete line;//sc.next();
			System.out.println("Enter Age:\t\t");
			age=sc.nextInt();
			System.out.println("Enter Sex:\t\t");
			sex=sc.next();
			System.out.println("Enter Height:\t\t");
			height=sc.nextFloat();
			System.out.println("Enter Weight:\t\t");
			weight=sc.nextFloat();
			System.out.println("Enter Body Colour:\t\t");
			colour=sc.next();
			System.out.println("Enter Contact Number:\t\t");
			contact=sc.next();
			System.out.println("Enter Staff ID:\t\t");
			staffid=sc.nextInt();
	
			pstmt.setInt (1, criminal_id);          
			pstmt.setString (2, fname);          
			pstmt.setString (3, lname);   
			pstmt.setString (4, mname);
			pstmt.setString (5, father_name);   
			pstmt.setString (6, mother_name);
			pstmt.setString (7, paddress);
			pstmt.setString (8, caddress);
			pstmt.setInt(9,age);
			pstmt.setString (10,sex );
			pstmt.setFloat(11,height);
			pstmt.setFloat (12,weight );			
			pstmt.setString (13, colour);
			pstmt.setString (14, contact);
			pstmt.setInt (15, staffid);
			pstmt.execute ();
			pstmt.close();  
			connection.close();
			System.out.println("\t--------------------------------\n\tNew Criminal Record Addded !\n\t--------------------------------");
		return true;
    }
	catch(Exception e){
		System.out.println("Exception Occured !"+ e);
		return false;
    }

 }
 
 
 boolean newFIR(){
//DATA stored in fir table
	    try {  
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());  
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "tiger");  
		    Scanner sc=new Scanner(System.in);

PreparedStatement pstmt = connection.prepareStatement ("insert into FIR (fir_number, fname,lname,mname,paddress,caddress,age,sex,contact, fir_date,firtext,staffid) values (?, ?,?,?,?, ?,?,?,?,?,?,?)");
      String sex,fname,lname,mname,paddress,caddress,desig,contact,firtext;
      int id,age,staffid;
      java.util.Date date = new java.util.Date();
      long t = date.getTime();
      java.sql.Date firDate = new java.sql.Date(t);

	System.out.println("Enter Fir Number:\t\t");
		id=sc.nextInt();
	System.out.println("Enter first name:\t\t");
		fname=sc.next();
	System.out.println("Enter last name:\t\t");
		lname=sc.next();
	System.out.println("Enter middle name:\t\t");
		mname=sc.next();
	paddress= System.console().readLine("Enter your Permanent Address: "); // Reading a complete line;  //sc.next();
	caddress= System.console().readLine("Enter your Correspondence Address: "); // Reading a complete line;//sc.next();
	System.out.println("Enter Age:\t\t");
		age=sc.nextInt();
	System.out.println("Enter Sex:\t\t");
		sex=sc.next();
	System.out.println("Enter Contact Number:\t\t");
		contact=sc.next();
	System.out.println("Enter FIR");
		firtext=System.console().readLine("Enter FIR: ");
	System.out.println("Enter Staff ID");
		staffid=sc.nextInt();
	
    pstmt.setInt (1, id);          
    pstmt.setString (2, fname);          
    pstmt.setString (3, lname);   
    pstmt.setString (4, mname);
    pstmt.setString (5, paddress);
    pstmt.setString (6, caddress);
    pstmt.setInt(7,age);
    pstmt.setString (8,sex );
    pstmt.setString (9, contact);
	pstmt.setDate (10, firDate);
	pstmt.setString (11, firtext);
	pstmt.setInt (12, staffid);
	
    pstmt.execute ();
    pstmt.close();  
    connection.close();
	System.out.println("\t\tFIR Added !");
	return true;
 }
catch(Exception e){
	System.out.println("Exception Occured !"+ e);
	return false;
    }
	
}	
	
	
 boolean insertStaff(){
        try {  
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());  
            Connection connection = DriverManager.getConnection(  
                    "jdbc:oracle:thin:@localhost:1521:XE", "system", "tiger");  
			Scanner sc=new Scanner(System.in);

PreparedStatement pstmt = 
      connection.prepareStatement ("insert into STAFF (id, fname,lname,mname,paddress,caddress,doj,desig,contact) values (?, ?,?,?,?, ?,?,?,?)");

      String fname,lname,mname,paddress,caddress,desig,contact;
      int id;
      java.util.Date date = new java.util.Date();
      long t = date.getTime();
      java.sql.Date sqlDate = new java.sql.Date(t);

	System.out.println("Enter Id:\t\t");
	id=sc.nextInt();
	System.out.println("Enter first name:\t\t");
	fname=sc.next();
	System.out.println("Enter last name:\t\t");
	lname=sc.next();
	System.out.println("Enter middle name:\t\t");
	mname=sc.next();
	paddress= System.console().readLine("Enter your Permanent Address: "); // Reading a complete line;  //sc.next();
	caddress= System.console().readLine("Enter your Correspondence Address: "); // Reading a complete line;//sc.next();
	System.out.println("Enter Designation:\t\t");
	desig=sc.next();
	System.out.println("Enter Contact number:\t\t");
	contact=sc.next();
    pstmt.setInt (1, id);          
    pstmt.setString (2, fname);          
    pstmt.setString (3, lname);   
    pstmt.setString (4, mname);
    pstmt.setString (5, paddress);
    pstmt.setString (6, caddress);
    pstmt.setDate(7,sqlDate);
    pstmt.setString (8,desig );
    pstmt.setString (9, contact);
	System.out.println("\t\tNew Record Added !");

    pstmt.execute ();
    pstmt.close();  
    connection.close();
	return true;
 }
catch(Exception e){
		System.out.println("Exception Occured !"+ e);
		return false;
    }
 }

}
