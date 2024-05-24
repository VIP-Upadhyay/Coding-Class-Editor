package com.vip.CodingClassEditorStudent;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.Timer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vip.CodingClassEditorStudent.model.OTPmodel;
import com.vip.CodingClassEditorStudent.model.Roles;
import com.vip.CodingClassEditorStudent.model.Student;
import com.vip.CodingClassEditorStudent.services.InputValidation;
import com.vip.CodingClassEditorStudent.services.StudentService;
import com.vip.CodingClassEditorStudent.services.utils.RestApiUtils;
import com.vip.CodingClassEditorStudent.services.utils.StudentHolder;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginSignupController implements Initializable {
	@FXML
	private Button loginButton,loginPasswordEye,signupPasswordEye,confirmPasswordEye;
	
	@FXML
	private Button signupSwitch;
	
	@FXML 
	private AnchorPane centerPane;
	
	@FXML
	private Pane loginPane;  
	
	@FXML
	private Pane signupPane,otppane;
	
	@FXML
	private AnchorPane parentPane;
	
	@FXML
	private Pane imgSliderPane,studentDetailsPane;
	
	@FXML 
	private ImageView imgView;
	
	@FXML
	private TextField emailOrUsername, loginPassword,passwordtextlogin;
	
	@FXML
	private TextField firstname,lastname,email,username,signupPassword,paswordtextsignup,confirmPassword,confirmPasswordText,otptext;
	
	@FXML
	private Label loginerror,signuperror,fnameError,lnameError,emailerror,usernameerror,signuppassworderror,confirmpassworderror,otperror;
	
	@FXML
	private ProgressBar progressBar;
	
	@FXML
	private Label addClassError,rollNoError,semestererror,brancherror;
	
	@FXML
	private TextField rollNo;
	
	@FXML
	private ComboBox<String> semester,branch;
	
	int count;
	boolean isLoginPasswordVisible = false,isSignupPasswordVisible=false,isConfirmPasswordVisible=false;
	private StudentService studentService;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		logingPasswordTextChanged();
		signupPasswordTextChanged();
		confirmPasswordTextChanged();
		addClassSetting();
		slideShow();
		studentService = RestApiUtils.getUserService();
		 
	}
	
	private void addClassSetting() {
		// TODO Auto-generated method stub
		ObservableList<String> branchlist = FXCollections.observableArrayList("Computer Science Engineering","Computer Engineering",
				"Information Technology","Artificial Intelligence");
		ObservableList<String> semesterlist = FXCollections.observableArrayList("1st sem","2nd sem","3rd sem","4th sem","5th sem","6th sem","7th sem","8th sem");
		semester.setItems(semesterlist);
		branch.setItems(branchlist);
	}

	private void logingPasswordTextChanged() {
		// TODO Auto-generated method stub
		loginPassword.textProperty().addListener((observable, oldValue, newValue) -> {
		    passwordtextlogin.setText(newValue);
		});
		passwordtextlogin.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginPassword.setText(newValue);
		});
	}
	
	private void signupPasswordTextChanged() {
		// TODO Auto-generated method stub
		signupPassword.textProperty().addListener((observable, oldValue, newValue) -> {
		    paswordtextsignup.setText(newValue);
		});
		paswordtextsignup.textProperty().addListener((observable, oldValue, newValue) -> {
			signupPassword.setText(newValue);
		});
	}
	private void confirmPasswordTextChanged() {
		// TODO Auto-generated method stub
		confirmPassword.textProperty().addListener((observable, oldValue, newValue) -> {
		    confirmPasswordText.setText(newValue);
		});
		confirmPasswordText.textProperty().addListener((observable, oldValue, newValue) -> {
			confirmPassword.setText(newValue);
		});
	}
	@FXML
	private void loginPasswordShow() {
		if (isLoginPasswordVisible) {
			loginPassword.setVisible(isLoginPasswordVisible);
			loginPassword.isFocused();
			passwordtextlogin.setVisible(!isLoginPasswordVisible);
			isLoginPasswordVisible = false;
			loginPasswordEye.setText("show");
		}else {
			loginPassword.setVisible(isLoginPasswordVisible);
			passwordtextlogin.setVisible(!isLoginPasswordVisible);
			passwordtextlogin.isFocused();
			isLoginPasswordVisible = true;
			loginPasswordEye.setText("hide");
		}
	}
	@FXML
	private void signupPasswordShow() {
		if (isSignupPasswordVisible) {
			signupPassword.setVisible(isSignupPasswordVisible);
			signupPassword.isFocused();
			paswordtextsignup.setVisible(!isSignupPasswordVisible);
			isSignupPasswordVisible = false;
			signupPasswordEye.setText("show");
		}else {
			signupPassword.setVisible(isSignupPasswordVisible);
			paswordtextsignup.setVisible(!isSignupPasswordVisible);
			paswordtextsignup.isFocused();
			isSignupPasswordVisible = true;
			signupPasswordEye.setText("hide");
		}
	}
	@FXML
	private void confirmPasswordShow() {
		if (isConfirmPasswordVisible) {
			confirmPassword.setVisible(isConfirmPasswordVisible);
			confirmPassword.isFocused();
			confirmPasswordText.setVisible(!isConfirmPasswordVisible);
			isConfirmPasswordVisible = false;
			confirmPasswordEye.setText("show");
		}else {
			confirmPassword.setVisible(isConfirmPasswordVisible);
			confirmPasswordText.setVisible(!isConfirmPasswordVisible);
			confirmPasswordText.isFocused();
			isConfirmPasswordVisible = true;
			confirmPasswordEye.setText("hide");
		}
	}
	 private void slideShow() {
		imgView.setImage(App.loadImage("img/codeimg1.jpg"));
		ArrayList<Image> images = new ArrayList<>();
		images.add(App.loadImage("img/codeimg1.jpg"));
		images.add(App.loadImage("img/codeimg2.jpg"));
		images.add(App.loadImage("img/codeimg3.jpg"));
		images.add(App.loadImage("img/codeimg4.png"));
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2),event ->{
			imgView.setImage(images.get(count));
			count++;
			if (count==4) {
				count=0;
			}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	@FXML
	 protected void loginButtonAction() {
		
		if (InputValidation.validateLogin(emailOrUsername, loginPassword,passwordtextlogin, loginerror)) {
			String emailOrUsernameString = emailOrUsername.getText().trim();
			String passwordString = loginPassword.getText().trim();
			String passwordTextString = passwordtextlogin.getText().trim();
			if (isLoginPasswordVisible) {
				login(emailOrUsernameString, passwordTextString);
			}else {
				login(emailOrUsernameString,passwordString);
			}
		}
	 }
	
	@FXML
	 protected void signupButtonAction() {
		System.out.println("Sing up Clicked");
		if (InputValidation.validateSignUp(firstname,fnameError,lastname,lnameError,
				email,emailerror,username,usernameerror,
				signupPassword,paswordtextsignup,signuppassworderror,
				confirmPassword,confirmPasswordText,confirmpassworderror)) {
			Student master = new Student();
			master.setFirstName(firstname.getText().trim());
			master.setLastName(lastname.getText().trim());
			master.setEmail(email.getText().trim());
			master.setUserName(username.getText().trim());
			master.setPassword(signupPassword.getText().trim());
			master.setRole(Roles.MASTER);
			signup(master);
		}
	 }
	
	@FXML
	protected void addDetailsButtonAction() {
		if (InputValidation.validateAddClass(rollNo, rollNoError, semester, semestererror, branch, brancherror)) {
			Student subjectClass = new Student();
			subjectClass.setRollNo(rollNo.getText().trim().toUpperCase());
			subjectClass.setSemester(semester.getValue().toString().trim());
			subjectClass.setBranch(branch.getValue().toString().trim());
			addDetails(subjectClass);
		}
	}
	
	
	 private void addDetails(Student subjectClass) {
		// TODO Auto-generated method stub
		 progressBar.setProgress(0.25);
		 Call<Object> call = studentService.addDetails(subjectClass);
		 progressBar.setProgress(0.75);
		 call.enqueue(new Callback<Object>() {

			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				// TODO Auto-generated method stub
				if (response.isSuccessful()) {
					progressBar.setProgress(1.0);
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							if (response.body().toString().contains("otp successfully sendend")) {
								addClassError.setVisible(false);
								studentDetailsPane.setVisible(false);
								otppane.setVisible(true); 
							}else {
								if (response.body().toString().contains("this Roll No is already signin")) {
									addClassError.setText("this Roll No is already signin");
									addClassError.setVisible(true);
								}else {
									System.out.println(response.body().toString());
									addClassError.setText("something went wrong. try againg later");
									addClassError.setVisible(true);
								}
							}
							progressBar.setProgress(0);
						}
					});
					
				}else {
					progressBar.setProgress(1.0);
						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								try {
									addClassError.setText(response.errorBody().string());
									addClassError.setVisible(true);
									System.out.println(response.errorBody().string());
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								progressBar.setProgress(0);
							}
						});
				}
			}

			@Override
			public void onFailure(Call<Object> call, Throwable t) {
				// TODO Auto-generated method stub
				progressBar.setProgress(100);
				progressBar.setProgress(0);
				t.printStackTrace();
			}
		});
	}

	private void signup(Student master) { 
		// TODO Auto-generated method stub
		progressBar.setProgress(0.25);
		Call<ArrayList<String>> call = studentService.registerStudent(master);
		progressBar.setProgress(0.75);
		call.enqueue(new Callback<ArrayList<String>>() {

			@Override
			public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
				// TODO Auto-generated method stub
				if (response.isSuccessful()) {
					progressBar.setProgress(1.0);
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							if (response.body().toString().contains("email is exists")) {
								emailerror.setText("email is exists");
								emailerror.setVisible(true);
							}else {
								if (response.body().toString().contains("username is exists")) {
									usernameerror.setText("username is exists");
									usernameerror.setVisible(true);
								}else {
									System.out.println("user added"); 
									signupPane.setVisible(false);
									studentDetailsPane.setVisible(true);  
								}
							}
							progressBar.setProgress(0);
						}
					});
				}else {
					progressBar.setProgress(1.0);
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								System.out.println(response.errorBody().string());
								signuperror.setText(response.errorBody().string());
								signuperror.setVisible(true);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							progressBar.setProgress(0);
						}
					});
				}
			}

			@Override
			public void onFailure(Call<ArrayList<String>> call, Throwable t) {
				// TODO Auto-generated method stub
				progressBar.setProgress(100);
				progressBar.setProgress(0);
				t.printStackTrace();
			}
		});
	}

	@FXML
	 protected void signUpSwitchAction() {
		 rotatePane();
		 Timer timer = new Timer(750, new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				// TODO Auto-generated method stub
				loginPane.setVisible(false);
				signupPane.setVisible(true);
			}
		});
		timer.setRepeats(false);
		timer.start(); 
	 }
	 
	 @FXML
	 protected void loginSwitchAction() {
		 rotatePane();
		 Timer timer = new Timer(750, new ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO Auto-generated method stub
					loginPane.setVisible(true);
					signupPane.setVisible(false);
				}
			});
			timer.setRepeats(false);
			timer.start();
	 }

	 @FXML
	 protected void verifotpaction() {
		 System.out.println("otp verify clicked");
		 if(otptext.getText()!="") {
			 OTPmodel otpmodel = new OTPmodel();
			 otpmodel.setOTP(Integer.parseInt(otptext.getText().trim()));
			 createAccount(otpmodel);
		 }
	 }
	
	
	private void createAccount(OTPmodel otpmodel) {
		// TODO Auto-generated method stub
		progressBar.setProgress(0.25);
		Call<Object> call = studentService.verifyOTP(otpmodel);
		progressBar.setProgress(0.75); 
		call.enqueue(new Callback<Object>() {

			@Override
			public void onResponse(Call<Object> call, Response<Object> response) {
				// TODO Auto-generated method stub
				if (response.isSuccessful()) {
					progressBar.setProgress(1.0);
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							System.out.println(response.body().toString());
							if (response.body().toString().contains("user or otp is not set")) {
								otppane.setVisible(false);
								signupPane.setVisible(true);
								signuperror.setVisible(true);
								otperror.setVisible(false);
								signuperror.setText("Session expired");
								progressBar.setProgress(0);
							}else {
								if (response.body().toString().contains("otp not matched")) {
									otperror.setText("OTP Not Matched");
									otperror.setVisible(true);
									progressBar.setProgress(0);
								}else {
									try {
										StudentHolder masterHolder = StudentHolder.getInstanceHolder();
										ObjectMapper mapper = new ObjectMapper();
										masterHolder.setStudent((Student) mapper.convertValue(response.body(), Student.class));
										otppane.setVisible(false);
										App.setRootWithCss("home","home"); 
									} catch (Exception e) {
										// TODO: handle exception
										e.printStackTrace();
									}
									progressBar.setProgress(0);
								}
							}
						}
					});
				}else {
					progressBar.setProgress(1.0);
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								System.out.println(response.errorBody().string());
								progressBar.setProgress(0);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								progressBar.setProgress(0);
							}
						}
					});
				}
			}

			@Override
			public void onFailure(Call<Object> call, Throwable t) {
				// TODO Auto-generated method stub
				progressBar.setProgress(100);
				progressBar.setProgress(0);
				t.printStackTrace();
			}
		});
	}

	private void rotatePane() {
		 RotateTransition rt = new RotateTransition(Duration.millis(1000), centerPane);
		 rt.setAxis(Rotate.Y_AXIS);
	     rt.setFromAngle(0);
		 rt.setByAngle(360);
		 rt.setCycleCount(1);
		 rt.play();
	}
	
	private void login(String userName,String password) {
		progressBar.setProgress(0.25);
		Call<Student> call = studentService.welcome(Credentials.basic(userName,password));
		progressBar.setProgress(0.75);
		call.enqueue(new Callback<Student>() {

			@Override
			public void onResponse(Call<Student> call, Response<Student> response) {
				if (response.isSuccessful()) {
					progressBar.setProgress(1.0);
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								StudentHolder studentHolder = StudentHolder.getInstanceHolder();
								studentHolder.setStudent((Student) response.body());
								App.setRootWithCss("home","home"); 
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							progressBar.setProgress(0);
						}
					});
				}else {
					progressBar.setProgress(1.0);
					Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								if (response.errorBody().string().contains("Bad credentials")) {
									loginerror.setText("Invalid Username and Password");
									loginerror.setVisible(true);
									progressBar.setProgress(0);
								}else {
									if (response.code()==123) {
										loginerror.setText("Only student can login here");
										loginerror.setVisible(true);
										progressBar.setProgress(0);
									}
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								progressBar.setProgress(0);
							}
						}
					});
				}
				
			}

			@Override
			public void onFailure(Call<Student> call, Throwable t) {
				progressBar.setProgress(100);
				progressBar.setProgress(0);
				t.printStackTrace();
			}

		});
	}
}
