package com.vip.CodingClassEditorStudent.services;

public class LanguageGetter {
	public static String getHelloCodeForLanguage(String fileName) {
		String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
		String extension = "";
		String beforeExten = "";
		if (tokens.length==2) {
			beforeExten= tokens[0];
			extension=tokens[1];
		}
		String CODE="";	
		switch (extension) {
		case "java":{
			CODE = "public class "+beforeExten+" {\r\n"
					+ "    public static void main(String[] args) {\r\n"
					+ "        System.out.println(\"Hello, Welcome To Coding Class Editor!\"); \r\n"
					+ "    }\r\n"
					+ "}";
			break;
		}
		case "cpp":{
			CODE = "#include <iostream>\r\n"
					+ "using namespace std;\r\n"
					+ " \r\n"
					+ "int main()\r\n"
					+ "{\r\n"
					+ "    // prints hello world\r\n"
					+ "    cout << \"Hello, Welcome To Coding Class Editor!\";\r\n"
					+ " \r\n"
					+ "    return 0;\r\n"
					+ "}";
			break;
		}
		case "c":{
			CODE = "#include <stdio.h>\r\n"
					+ "int main() {\r\n"
					+ "   // printf() displays the string inside quotation\r\n"
					+ "   printf(\"Hello, Welcome To Coding Class Editor!\");\r\n"
					+ "   return 0;\r\n"
					+ "}";
			break;
		}
		case "py":{
			CODE = "print('Hello, Welcome To Coding Class Editor!')";
			break;
		}
		case "xml":{
			CODE = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			break;
		}
		case "html":{
			CODE = "<!DOCTYPE html>\r\n"
					+ " \r\n"
					+ "<html>\r\n"
					+ "    <head>\r\n"
					+ "        <title>\r\n"
					+ "            Coding Class Editor Web Page\r\n"
					+ "        </title>\r\n"
					+ "    </head>\r\n"
					+ " \r\n"
					+ "    <body>\r\n"
					+ "        Hello, Welcome To Coding Class Editor!\r\n"
					+ "    </body>\r\n"
					+ "</html>";
			break;
		}
		case "htm":{
			CODE = "<!DOCTYPE html>\r\n"
					+ " \r\n"
					+ "<html>\r\n"
					+ "    <head>\r\n"
					+ "        <title>\r\n"
					+ "           Coding Class Editor Web Page\r\n"
					+ "        </title>\r\n"
					+ "    </head>\r\n"
					+ " \r\n"
					+ "    <body>\r\n"
					+ "        Hello, Welcome To Coding Class Editor!\r\n"
					+ "    </body>\r\n"
					+ "</html>";
			break;
		}
		case "css":{
			CODE = "";
			break;
		}
		case "php":{
			CODE = "<?php echo 'Hello, Welcome To Coding Class Editor!'; ?>";
			break;
		}
		case "js":{
			CODE = "console.log('Hello, Welcome To Coding Class Editor!');";
			break;
		}
		
		default:{
			CODE="";
			break;
		}
		}
		return CODE;
	}
	public static String getLanguage(String fileName) {
		String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
		if (tokens.length==2) {
			return tokens[1];
		}else {
			return null;
		}
	}
}
