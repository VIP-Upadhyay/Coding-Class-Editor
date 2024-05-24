package com.vip.CodingClassEditorStudent.services;

import java.util.regex.Pattern;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InputValidation  {
	public static boolean validateLogin(TextField emailOrUsernameField,TextField passwordField,TextField passwordtextField,Label loginerror) {
		if (isValidEmailOrUsername(emailOrUsernameField, loginerror)&&isValidPassword(passwordField,passwordtextField, loginerror)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isValidEmailOrUsername(TextField emailOrUsernameField,Label errorLabel) {
		if (emailOrUsernameField.getText().trim().equals("")) {
			addErrorBorder(emailOrUsernameField);
			errorLabel.setText("Enter Email or Username");
			errorLabel.setVisible(true);
			return false;
		}else {
			if (isValidEmail(emailOrUsernameField, errorLabel)||isValidUsername(emailOrUsernameField, errorLabel)) {
				removeErrorBorder(emailOrUsernameField);
				errorLabel.setVisible(false);
				return true;
			}else {
				errorLabel.setVisible(true);
				return false;
			}
		}
	}
	public static boolean isValidEmail(TextField emailField,Label errorLabel){
		String email = emailField.getText().trim();
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email.equals("")) {
        	addErrorBorder(emailField);
        	errorLabel.setText("Enter a email");
        	errorLabel.setVisible(true);
        	return false;
        }
        boolean valid = pat.matcher(email).matches();
        if (valid) {
        	removeErrorBorder(emailField);
        	errorLabel.setVisible(false);
			return valid;
		}else {
			addErrorBorder(emailField);
			errorLabel.setText("Enter a valid Email");
			errorLabel.setVisible(true);
			return valid;
		}
    }
	
	private static void addErrorBorder(TextField field) {
		if (!field.getStyleClass().contains("error-input-fields")) {
			field.getStyleClass().add("error-input-fields");
		}
	}
	private static void removeErrorBorder(TextField field) {
		if (field.getStyleClass().contains("error-input-fields")) {
			field.getStyleClass().remove("error-input-fields");
		}
	}
	
	public static boolean isValidUsername(TextField usernameField,Label errorLabel){
		String name = usernameField.getText().trim();
        String regex = "^[A-Za-z]\\w{2,29}$";
  
        Pattern pat = Pattern.compile(regex);
  
        if (name.equals("")) {
        	addErrorBorder(usernameField);
        	errorLabel.setText("Enter a username");
        	errorLabel.setVisible(true);
        	return false;
        }
  
        boolean valid = pat.matcher(name).matches();
        
        if (valid) {
        	removeErrorBorder(usernameField);
        	errorLabel.setVisible(false);
			return valid;
		}else {
			addErrorBorder(usernameField);
			errorLabel.setText("Enter a valid username");
			errorLabel.setVisible(true);
			return valid;
		}
    }
	
	public static boolean isValidPassword(TextField passwordfiField,TextField passwordtextField,Label errorLabel){
		String password = passwordfiField.getText().trim();
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-zA-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$";

        Pattern pat = Pattern.compile(regex);

        if (password.equals("")) {
        	addErrorBorder(passwordfiField);
        	addErrorBorder(passwordtextField);
        	errorLabel.setText("Enter a password");
        	errorLabel.setVisible(true);
            return false;
        }

        boolean valid = pat.matcher(password).matches();
        
        if (valid) {
        	removeErrorBorder(passwordfiField);
        	removeErrorBorder(passwordtextField);
        	errorLabel.setVisible(false);
			return valid;
		}else {
			addErrorBorder(passwordfiField);
			addErrorBorder(passwordtextField);
			errorLabel.setText("Password must contain letters, symbol & no.");
			errorLabel.setVisible(true);
			return valid;
		}
        
    }
	
	public static boolean checkConfimPassword(TextField passwordField,TextField passwordTextField,TextField confirmPasswordField,TextField confirmPasswordText,Label perrorLabel,Label cperrorLabel) {
		String password = passwordField.getText().trim();
		String confirmPassword = confirmPasswordField.getText().trim();
		if (!password.equals("") && !confirmPassword.equals("")) {
			if (password.equals(confirmPassword)) {
				removeErrorBorder(confirmPasswordField);
				removeErrorBorder(confirmPasswordText);
				cperrorLabel.setVisible(false);
				return true;
			}else {
				addErrorBorder(confirmPasswordField);
				addErrorBorder(confirmPasswordText);
				cperrorLabel.setText("Password not match");
				cperrorLabel.setVisible(true);
				return false;
			}
		}else {
			addErrorBorder(confirmPasswordField);
			addErrorBorder(confirmPasswordText);
			cperrorLabel.setText("Please confirm the password");
			cperrorLabel.setVisible(true);
			return false;
		}
	}
	
	public static boolean validateName(TextField nameTextField,Label errorLabel,String Type) {
		String name = nameTextField.getText().trim();
		boolean valid =name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){1,30}$");
		
		if (name.equals("")) {
			addErrorBorder(nameTextField);
        	errorLabel.setText("Enter a "+Type+" name");
        	errorLabel.setVisible(true);
            return false;
        }
		
		if (valid) {
			removeErrorBorder(nameTextField);
			errorLabel.setVisible(false);
			return valid;
		}else {
			addErrorBorder(nameTextField);
			errorLabel.setText("Enter a valid name");
			errorLabel.setVisible(true);
			return valid;
		}
	}

	public static boolean validateSignUp(TextField firstname, Label fnameError, TextField lastname, Label lnameError,
			TextField email, Label emailerror, TextField username, Label usernameerror, TextField signupPassword,
			TextField paswordtextsignup, Label signuppassworderror, TextField confirmPassword, TextField confirmPasswordText,
			Label confirmpassworderror) {
		// TODO Auto-generated method stub
		if (validateName(firstname, fnameError, "first") && validateName(lastname, lnameError, "last")
				&& isValidEmail(email, emailerror) && isValidUsername(username, usernameerror)
				&& isValidPassword(signupPassword, paswordtextsignup, signuppassworderror)
				&& checkConfimPassword(signupPassword, paswordtextsignup, confirmPassword, confirmPasswordText, signuppassworderror, confirmpassworderror)) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean validateComboBox(ComboBox<String> comboBox,Label error,String type) {
		if (comboBox.getValue()!=null) {
			removeErrorBorderCB(comboBox);
			error.setVisible(false);;
			return true;
		}else {
			addErrorBorderCB(comboBox);
			error.setText("please select a "+type);
			error.setVisible(true);
			return false;
		}
	}
	private static void addErrorBorderCB(ComboBox<String> field) {
		if (!field.getStyleClass().contains("error-input-fields")) {
			field.getStyleClass().add("error-input-fields");
		}
	}
	private static void removeErrorBorderCB(ComboBox<String> field) {
		if (field.getStyleClass().contains("error-input-fields")) {
			field.getStyleClass().remove("error-input-fields");
		}
	}
	
	public static boolean validateSubject(TextField nameTextField,Label errorLabel,String Type) {
		String name = nameTextField.getText().trim();
		
		if (name.equals("")) {
			addErrorBorder(nameTextField);
        	errorLabel.setText("Enter a "+Type+" name");
        	errorLabel.setVisible(true);
            return false;
        }else {
        	removeErrorBorder(nameTextField);
			errorLabel.setVisible(false);
			return true;
		}
		
	}
	
	public static boolean validateAddClass(TextField subject,Label subjecterror,
			ComboBox<String> semester,Label semestererror,
			ComboBox<String> branch,Label brancherror) {
		if (validateSubject(subject, subjecterror, "subject") && 
				validateComboBox(branch, brancherror, "branch") &&
				validateComboBox(semester, semestererror, "semester")) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static boolean validateFileName(TextField filename,Label fileNameError) {
		String name = filename.getText().trim();
		if (!name.equals("")) {
			if (name.matches("^[a-zA-Z0-9._ -]+\\.(java|cpp|c|xls|csv|py|sql|js|xml|html|css|php|jsp|htm|txt|json)$")) {
				fileNameError.setVisible(false);
				removeErrorBorder(filename);
				return true;
			}else {
				fileNameError.setText("Enter a valid file name");
				fileNameError.setVisible(true);
				addErrorBorder(filename);
				return false;
			}
		}else {
			fileNameError.setText("Enter a file name");
			fileNameError.setVisible(true);
			addErrorBorder(filename);
			return false;
		}
	}
	public static boolean validateFolderName(TextField filename,Label fileNameError) {
		String name = filename.getText().trim();
		if (!name.equals("")) {
			fileNameError.setVisible(false);
			removeErrorBorder(filename);
			return true;
		}else {
			fileNameError.setText("Enter a folder name");
			fileNameError.setVisible(true);
			addErrorBorder(filename);
			return false;
		}
	}
	public static boolean validateFileNameForCell(String name) {
		if (name.matches("^[a-zA-Z0-9._ -]+\\.(class|exe)$")) {
			return false;
		}else {
			return true;
		}
	}
}
