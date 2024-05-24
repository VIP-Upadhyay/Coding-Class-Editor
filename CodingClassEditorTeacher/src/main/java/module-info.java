module com.vip.CodingClassEditorTeacher {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.desktop;
	requires retrofit2;
	requires okhttp3;
	requires retrofit2.converter.jackson;
	requires retrofit2.adapter.rxjava;
	requires com.fasterxml.jackson.databind;
	requires javafx.graphics;
	requires java.logging;
	requires javafx.base;
	requires jdk.jsobject;
	requires okio;
	requires javafx.web;
	requires java.base;
	requires jdk.compiler;

    opens com.vip.CodingClassEditorTeacher to javafx.fxml;
    exports com.vip.CodingClassEditorTeacher;
    exports com.vip.CodingClassEditorTeacher.model;
}
