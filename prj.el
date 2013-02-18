(jde-project-file-version "1.0")
(jde-set-variables
 '(jde-project-name "z-xml")
 ;; classpath
 '(jde-global-classpath 
   (quote 
    ("/home/lizhi/workspace/2013/z-xml/lib/*"
     "/home/lizhi/workspace/2013/z-xml/bin/"
     "$ANT_HOME/lib/*")
    ))
 ;; 在编译时用到
 '(jde-compile-option-sourcepath 
   (quote ("/home/lizhi/workspace/2013/z-xml/src" 
	   "/home/lizhi/workspace/2013/z-xml/test")))
 '(jde-compile-option-classpath 
   (quote ("/home/lizhi/workspace/2013/z-xml/lib/*" 
	   "/home/lizhi/workspace/2013/z-xml/bin/")))
 ;; Junit
 '(jde-junit-working-directory "/home/lizhi/workspace/2013/z-xml/")
 '(jde-run-working-directory "/home/lizhi/workspace/2013/z-xml/")
 '(jde-sourcepath 
   (quote ("/home/lizhi/workspace/2013/z-xml/src/" 
	   "/home/lizhi/workspace/2013/z-xml/test")))
 ;;'(jde-run-application-class "/home/lizhi/workspace/2013/z-xml/bin")
 ;;'(jde-run-working-directory "/home/lizhi/workspace/2013/z-xml")
 '(jde-compile-option-directory "/home/lizhi/workspace/2013/z-xml/bin/")
 '(jde-compile-option-encoding "utf-8")
 '(jde-build-function (quote (jde-ant-build)))
 '(jde-ant-enable-find t)
 '(jde-ant-read-target t)
 '(jde-ant-home "$ANT_HOME")
 '(jde-ant-invocation-method (quote ("Ant Server")))
 ;;'(jde-ant-user-jar-files (quote ("")) ; 这里对应eclipse中add build里的jars
 ;;'(jde-vm-path "/Java/jdk1.6/bin/java")
 ;;'(jde-run-application-class "/home/lizhi/workspace/2013/z-xml/bin")
 )
