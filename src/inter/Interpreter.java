package inter;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Interpreter {

	static ArrayList<Variable> variable = new ArrayList<Variable>();
	public static void read(String fileName) throws IOException{
		//FileInputStream fstream = new FileInputStream("src/code.txt");
		//Variable obj = new Variable();
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String delim = " ";
		String line;
		String operator = null;
		int first = 0;
		int second = 0;
		int count = 0;
		int index = 0;
		boolean flag1;
		boolean flag2;
		boolean flag3;
		boolean flag4;
		boolean tuk;
		while((line = br.readLine()) != null){
			flag1 = flag2 = flag3 = flag4 = tuk = false;
			//	System.out.println(line);
			StringTokenizer tok = new StringTokenizer(line,delim);

			//boolean exDelim = false;
			String token = tok.nextToken();
			if(token.equals ("LET")){

				//if(line.startsWith("LET")){
				while(tok.hasMoreElements()){


					/*System.out.println("Token: "+ tok.nextElement());
					String token = (String) tok.nextElement();*/
					String name;
					int value;


					//System.out.println("4");
					name = tok.nextToken();

					//System.out.println(token);
					//System.out.println("4");
					if(tok.nextToken().equals("=")){
						//System.out.println("4");
						value = Integer.parseInt(tok.nextToken());
						variable.add(new Variable(name,value));
						//System.out.println(obj.name);
						//System.out.print(obj.value);
					}

					//}
					//System.out.println(token);

				}

			}



			else if(token.equals("Print")){
				boolean flag = false;
				token = tok.nextToken();
				for(int i = 0;i<variable.size();i++){
					if(token.equals(variable.get(i).name)){
						System.out.print(variable.get(i).value+ "\n");
						flag = true;
						break;

					}

				}


				if(flag == false){

					System.out.println(token+ " Variable not present");

				}

			}


			else{
				if(!(isInteger(token))){

					while(tok.hasMoreElements()){
						if(tuk  == false){
							tuk = true;

						}
						else{
							token = tok.nextToken();
						}
						if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){

							operator = token;

						}
						else{
							//int answer;

							//int a[] = new int [5];


							for( int i = 0;i<variable.size();i++){
								if(token.equals(variable.get(i).name)){
									//System.out.print(variable.get(i).value);
									flag1 = true;
									if(flag2 == false){
										index = i;
										flag2 = true;
										//token = tok.nextToken();
									}
									else if(flag3 == false){
										first = variable.get(i).value;
										flag3 = true;
										//token = tok.nextToken();
									}
									else if(flag4 == false){
										second = variable.get(i).value;
										flag4 = true;
										//token = tok.nextToken();
									} 

									if(flag2 == true && flag3 == true && flag4 == true){

										//call funtion for operator.
										first = calculate(operator,first,second,index);
										//variable.get(index).value = first;
										flag4 = false;

									}
									break;

								}
								//System.out.print(variable.get(i).value);

							}
							if(flag1 == false){

								System.out.println(token +" Variable not present");

							}

						}
					}
					
					variable.get(index).value = first;

				}
				
				else{
					
					System.out.println("The line starting with "+ token+" is wrong input");

				}
				


			}
		}
	}

	public static int calculate(String operator, int first, int second, int indexOfAns){
		int ans = 0;
		switch(operator){
		case "+":
			ans = first + second;
			break;
		case "-":
			ans = first - second;
			break;
		case "*":
			ans = first * second;
			break;
		case "/":
			ans = first / second;
			break;
		}

		return ans;

	}


	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String file = "src/code.txt";
		read(file);
	}

}
