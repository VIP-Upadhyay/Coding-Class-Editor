package com.vip.CodingClassEditorStudent.services;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateDirectory {
	
	public static String dirPath = "CCE/";
	
	
	public static void createMainDirectory(String username) {
		String dirPathObj = dirPath+"/"+username;
		Path path = Paths.get(dirPathObj);
		boolean dirExists = Files.exists(path);
        if(dirExists) {
            System.out.println("! Directory Already Exists !");
        } else {
            try {
                // Creating The New Directory Structure
                Files.createDirectories(path);
                System.out.println("! New Directory Successfully Created !");
            } catch (IOException ioExceptionObj) {
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }
	}
	
	public static String createPracticalDirectoryWithFile(String username,String subjectName,long pracId,String fileName) {
		String dirPathObj = dirPath+"/"+username+"/"+subjectName+"/"+subjectName+pracId+"/"+fileName;
		Path path = Paths.get(dirPathObj);
		boolean dirExists = Files.exists(path);
        if(dirExists) {
            System.out.println("! Directory Already Exists !");
            return dirPathObj;
        } else {
            try {
                // Creating The New Directory Structure
            	Files.createFile(path);
                System.out.println("! New Directory Successfully Created !");
                return dirPathObj;
            } catch (IOException ioExceptionObj) {
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }
		return null;
	}
	
	public static String createPracticalDirectory(String username,String subjectName,long pracId) {
		String dirPathObj = dirPath+"/"+username+"/"+subjectName+"/Practical/"+subjectName+pracId;
		Path path = Paths.get(dirPathObj);
		boolean dirExists = Files.exists(path);
        if(dirExists) {
            System.out.println("! Directory Already Exists !");
            return dirPathObj;
        } else {
            try {
                // Creating The New Directory Structure
            	 Files.createDirectories(path);
                System.out.println("! New Directory Successfully Created !");
                return dirPathObj;
            } catch (IOException ioExceptionObj) {
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }
		return null;
	}
	public static String createAssignmentDirectory(String username,String subjectName,long pracId) {
		String dirPathObj = dirPath+"/"+username+"/"+subjectName+"/Assignment/"+subjectName+pracId;
		Path path = Paths.get(dirPathObj);
		boolean dirExists = Files.exists(path);
        if(dirExists) {
            System.out.println("! Directory Already Exists !");
            return dirPathObj;
        } else {
            try {
                // Creating The New Directory Structure
            	 Files.createDirectories(path);
                System.out.println("! New Directory Successfully Created !");
                return dirPathObj;
            } catch (IOException ioExceptionObj) {
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }
		return null;
	}
	
	public static String createFileForTreeFolder(String directory,String fileName) {
		String dirPathObj = directory+"/"+fileName;
		Path path = Paths.get(dirPathObj);
		boolean dirExists = Files.exists(path);
        if(dirExists) {
            System.out.println("!File Already Exists !");
            return "File Already Exists !";
        } else {
            try {
                // Creating The New Directory Structure
            	Files.createFile(path);
                System.out.println("! New File Successfully Created !");
                return dirPathObj;
            } catch (IOException ioExceptionObj) {
            	System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            	return "Some thing went wrong";
            }
        }
	}
	public static String createFolderForTreeFolder(String directory,String folderName) {
		String dirPathObj = directory+"/"+folderName;
		Path path = Paths.get(dirPathObj);
		boolean dirExists = Files.exists(path);
        if(dirExists) {
            System.out.println("! Folder Already Exists !");
            return "Folder Already Exists !";
        } else {
            try {
                // Creating The New Directory Structure
                Files.createDirectories(path);
                System.out.println("! New Folder Successfully Created !");
                return dirPathObj;
            } catch (IOException ioExceptionObj) {
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
                return "Some thing went wrong";
            }
        }
	}
	
	public static String renameFileForTreeFolder(String directory,String fileName) {
		String dirPathObj = directory;
		Path path = Paths.get(dirPathObj);
		 try {
             // Creating The New Directory Structure
         	 Path tPath = Files.move(path, path.resolveSibling(fileName));
             System.out.println("! Rename File Successfully Created !");
             return tPath.toString();
         } catch (IOException ioExceptionObj) {
         	System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
         	if (ioExceptionObj instanceof FileAlreadyExistsException) {
				return "File Already Exists !";
			}else {
				return "Some thing went wrong";
			}
         	
         }
	}
	
	public static String renameFolderForTreeFolder(String directory,String folderName) {
		String dirPathObj = directory;
		Path path = Paths.get(dirPathObj);
		 try {
             // Creating The New Directory Structure
         	 Path tPath = Files.move(path, path.resolveSibling(folderName));
             System.out.println("! Rename Folder Successfully Created !");
             return tPath.toString();
         } catch (IOException ioExceptionObj) {
         	System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
         	if (ioExceptionObj instanceof FileAlreadyExistsException) {
				return "Folder Already Exists !";
			}else {
				return "Some thing went wrong";
			}
         	
         }
	}
	
	public static boolean deleteFileForTreeFolder(String directory) {
		String dirPathObj = directory;
		Path path = Paths.get(dirPathObj);
		 try {
             // Creating The New Directory Structure
         	 boolean isDelete = Files.deleteIfExists(path);
         	 if (isDelete) {
         		System.out.println("! Delete File Successfully Created !");
                return true;
			}else {
				return false;
			}
             
         } catch (IOException ioExceptionObj) {
         	System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
         	ioExceptionObj.printStackTrace();
         	return false;
         	
         }
	}
	public static boolean deleteFolderForTreeFolder(String directory) {
		String dirPathObj = directory;
		Path path = Paths.get(dirPathObj);
		 try {
             // Creating The New Directory Structure
			 if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
				 try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
				      for (Path entry : entries) {
				    	  deleteFolderForTreeFolder(entry.toString());
				      }
				 }
			}
         	 boolean isDelete = Files.deleteIfExists(path);
         	 if (isDelete) {
         		System.out.println("! Delete File Successfully Created !");
                return true;
			}else {
				return false;
			}
             
         } catch (IOException ioExceptionObj) {
         	System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
         	ioExceptionObj.printStackTrace();
         	return false;
         	
         }
	}
	
}
