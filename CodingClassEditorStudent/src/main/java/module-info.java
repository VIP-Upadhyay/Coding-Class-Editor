module com.vip.CodingClassEditorStudent {
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
	requires javafx.web;
	requires jdk.jsobject;
	requires pty4j;
	requires jna;
	requires log4j;
	requires com.google.common;
	requires terminalfx;

    opens com.vip.CodingClassEditorStudent to javafx.fxml;
    exports com.vip.CodingClassEditorStudent;
    exports com.vip.CodingClassEditorStudent.model;
}
