main: update repackage

update:
	@echo "@Updating files...@"
	@cp mkproj-java/mkproj.jar mkproj-deb/opt/mkproj/mkproj.jar
	@cp mkproj-bash/mkproj mkproj-deb/usr/local/bin/mkproj
	@echo "@Files updated!@"

repackage:
	@echo "@Repackaging...@"
	@dpkg-deb --build mkproj-deb mkproj-install > /dev/null
	@echo "@Repackaged!@"
