;;; prj.el --- Z-XML

;;; Commentary:
;; JDEE Project
;; Author: Zhong Lizhi <zlz.3907 <at> gmail.com>
;; Version: 1.0

;;; Code:
(jde-project-file-version "1.0")
(defvar z-project-name "z-xml" "The project name.")
(defvar z-base-dir (concat "~/projects/" z-project-name "/")
  "The project base path.")
(jde-set-variables
 '(jde-project-name 'z-project-name)
 ;; 运行时使用
 '(jde-global-classpath
   (quote
    ((concat 'z-base-dir "lib/*")
     (concat 'z-base-dir "build/main")
     (concat 'z-base-dir "build/test")
     "~/apache/ant/lib/*"
     "~/ivyrepository/junit-4.8.2.jar"
     "~/ivyrepository/hamcrest-1.1.0.jar")
    ))
 ;; 在编译时用到
 '(jde-compile-option-sourcepath
   (quote ((concat 'z-base-dir "src")
           (concat 'z-base-dir "test/unit")
           (concat 'z-base-dir "test/verify")
           (concat 'z-base-dir "test/integration"))))
 '(jde-compile-option-classpath
   (quote ((concat 'z-base-dir "lib/*")
           (concat 'z-base-dir "build/main")
           (concat 'z-base-dir "build/test")
           "~/ivyrepository/junit-4.8.2.jar"
           "~/ivyrepository/hamcrest-1.1.0.jar")))
 ;; Junit
 '(jde-junit-working-directory 'z-base-dir)
 '(jde-run-working-directory 'z-base-dir)
 '(jde-sourcepath
   (quote ((concat 'z-base-dir "src")
           (concat 'z-base-dir "test/unit")
           (concat 'z-base-dir "test/verify")
           (concat 'z-base-dir "test/integration"))))
 ;;'(jde-run-application-class "~/projects/robot/bin")
 ;;'(jde-run-working-directory "e:/home/lizhi/workspace/2012/ztools")
 '(jde-compile-option-directory (concat "build/main/"))
 '(jde-compile-option-encoding "utf-8")
 '(jde-build-function (quote (jde-ant-build)))
 '(jde-ant-enable-find t)
 '(jde-ant-read-target t)
 '(jde-ant-home "~/apache/ant")
 '(jde-ant-invocation-method (quote ("Ant Server")))
 )

;;; prj.el ends here
