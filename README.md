# Coding Class Editor (CCE)

A modern cloud-based programming education platform that revolutionizes traditional coding practical lab sessions through real-time collaboration and instant feedback mechanisms.

## 🚀 Overview

We are living in an era where technology transforms every aspect of our lives. However, coding education in practical labs still relies on outdated methodologies. The Coding Class Editor addresses this gap by providing a cloud-based solution that enables real-time collaboration between instructors and students, making programming education more interactive and efficient.

## 🎯 Problem Statement

Traditional coding lab sessions face several challenges:
- **Limited Individual Attention**: Instructors cannot review every student's work personally
- **Inefficient Doubt Resolution**: Students' questions often go unaddressed
- **Lack of Real-time Feedback**: No instant code review and guidance
- **Communication Barriers**: Difficulty in providing immediate assistance to multiple students

## 💡 Solution

CCE provides a comprehensive cloud-based platform with:
- **Real-time Code Collaboration**: Similar to pair programming but optimized for classroom settings
- **Instant Communication**: Built-in messenger for quick doubt resolution
- **Assignment Management**: Seamless assignment distribution and submission
- **Live Code Review**: Instructors can review and provide feedback in real-time
- **Multi-user Support**: Simultaneous collaboration between multiple users

## 🏗️ Architecture

The project consists of three main components:

### 1. **CodingClassEditor** (Backend Server)
- **Technology**: Spring Boot application
- **Database**: Hibernate ORM with repository pattern
- **Security**: Basic authentication with OTP verification
- **Communication**: WebSocket integration for real-time messaging
- **API**: RESTful endpoints for all operations

### 2. **CodingClassEditorStudent** (Student Desktop Application)
- **Technology**: JavaFX desktop application
- **Editor**: ACE editor integration for syntax highlighting
- **Features**: File management, real-time collaboration, messaging
- **Platform Support**: Cross-platform with native libraries

### 3. **CodingClassEditorTeacher** (Instructor Desktop Application)
- **Technology**: JavaFX desktop application
- **Management**: Student management, assignment creation, practical sessions
- **Monitoring**: Real-time code review and student progress tracking
- **Communication**: Direct messaging and broadcast capabilities

## 🛠️ Technology Stack

### Backend
- **Framework**: Spring Boot
- **Security**: Spring Security with JWT
- **Database**: JPA/Hibernate
- **WebSocket**: Real-time communication
- **Email**: SMTP integration for notifications

### Desktop Applications
- **UI Framework**: JavaFX
- **Code Editor**: ACE Editor (Web-based editor embedded)
- **HTTP Client**: Retrofit for API communication
- **Terminal**: Integrated terminal support with PTY4J
- **Build Tool**: Maven

### Supported Languages
- Java
- Python
- JavaScript
- C/C++
- And many more through ACE editor modes

## 📋 Features

### For Students
- ✅ **Real-time Code Editor** with syntax highlighting
- ✅ **Assignment Submission** system
- ✅ **Practical Exercise** management
- ✅ **Instant Messaging** with instructors
- ✅ **File Management** (create, edit, delete, rename)
- ✅ **Multiple Language Support**
- ✅ **Integrated Terminal**

### For Instructors
- ✅ **Student Management** system
- ✅ **Assignment Creation** and distribution
- ✅ **Real-time Code Monitoring**
- ✅ **Practical Session Management**
- ✅ **Group Messaging** capabilities
- ✅ **Progress Tracking**
- ✅ **Code Review** tools

### System Features
- ✅ **Cloud-based Architecture**
- ✅ **WebSocket Communication**
- ✅ **OTP Authentication**
- ✅ **Cross-platform Support**
- ✅ **Real-time Collaboration**

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- MySQL/PostgreSQL database (for backend)

### Backend Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/VIP-Upadhyay/Coding-Class-Editor.git
   cd Coding-Class-Editor/CodingClassEditor
   ```

2. Configure database in `application.properties`

3. Run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

### Student Application Setup
1. Navigate to student application:
   ```bash
   cd CodingClassEditorStudent
   ```

2. Build and run:
   ```bash
   mvn javafx:run
   ```

### Teacher Application Setup
1. Navigate to teacher application:
   ```bash
   cd CodingClassEditorTeacher
   ```

2. Build and run:
   ```bash
   mvn javafx:run
   ```

## 📁 Project Structure

```
Coding-Class-Editor/
├── CodingClassEditor/          # Backend Spring Boot Application
│   ├── src/main/java/
│   │   └── com/vip/codingclasseditor/
│   │       ├── controller/     # REST Controllers
│   │       ├── model/          # Data Models
│   │       ├── repository/     # Data Access Layer
│   │       ├── service/        # Business Logic
│   │       └── websocket/      # WebSocket Configuration
│   └── src/main/resources/     # Configuration Files
├── CodingClassEditorStudent/   # Student Desktop Application
│   ├── src/main/java/
│   │   └── com/vip/CodingClassEditorStudent/
│   └── src/main/resources/     # JavaFX FXML, CSS, Images
└── CodingClassEditorTeacher/   # Teacher Desktop Application
    ├── src/main/java/
    │   └── com/vip/CodingClassEditorTeacher/
    └── src/main/resources/     # JavaFX FXML, CSS, Images
```

## 🔧 Configuration

### Database Configuration
Update `application.properties` in the backend:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cce_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### WebSocket Configuration
The application uses WebSocket for real-time communication. Ensure your firewall allows WebSocket connections.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Authors

- **VIP Upadhyay** - *Initial work* - [@VIP-Upadhyay](https://github.com/VIP-Upadhyay)

## 🙏 Acknowledgments

- ACE Editor for the powerful web-based code editor
- Spring Boot community for the excellent framework
- JavaFX community for desktop application capabilities

## 📞 Support

For support, email us at:
- vaibhavupadhyay0077@gmail.com
- vipupadhyay0077@gmail.com

Or open an issue on GitHub.

## 🔮 Future Enhancements

- [ ] Video call integration
- [ ] AI-powered code suggestions
- [ ] Mobile application support
- [ ] Advanced analytics dashboard
- [ ] Plugin system for custom languages
- [ ] Cloud IDE integration

---

**⭐ If you find this project helpful, please consider giving it a star!**